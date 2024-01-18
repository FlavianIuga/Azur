package shared

import groovy.time.*
import security.SecRole
import security.SecUser
import util.Settings
import actiune.Actiune
import actiune.Arhiva

class BaseController {

	def cautare(){

		def today = new Date()

		def adminList = new ArrayList<String>()
		
		def adminRole = SecRole.findByAuthority('ROLE_ADMIN')
		
		if (SecUser.list().size() > 0){
			for (i in 0..SecUser.list().size()-1){
				
				def ite = SecUser.list()[i]
				if (ite!=null){
					
					boolean isAdmin = false
					ite.authorities.each  {
						
						if (it.authority.equals(adminRole.authority)){
							isAdmin = true
						}
					}
					if (isAdmin)
						adminList.add("Da")
					else
						adminList.add("Nu")
				}
			}
		}
		
		ArrayList<String> fieldNameListForList = getFieldNameListForList()
		ArrayList<String> fieldListForList = getFieldListForList()

		def activ = 1
		def inactiv = 1
		def enabledValue = true
		def all = true

		if (params.activCheckBox == null)
			activ = 0
			
		if (params.inactivCheckBox == null)
			inactiv = 0
		
		if (activ!=inactiv){
			all=false
			if (activ)
				enabledValue = true
			else
				enabledValue = false
		}
		
		def instanceList = getInstanceList(fieldListForList, all, enabledValue)
	

		def actiuneInstanceListComplet =[]
		def termenLimitaList = []
		def garantieFurnizorRamasa
		def garantieClientRamasa
		if (getControllerDomain().toString().equals("class produs.Produs")){
			garantieFurnizorRamasa = getGarantieFurnizorRamasa()
			garantieClientRamasa = getGarantieClientRamasa()
		}
		
		def toateOperatiile
		if (params.toateOperatiile==null)
			toateOperatiile=false	
		else 
			toateOperatiile = true
			
		if (getControllerDomain().toString().equals("class actiune.Actiune") || getControllerDomain().toString().equals("class actiune.Arhiva")){
			
			if (!toateOperatiile){

				def idd = ""
				instanceList.each {
					
					if (idd != it.idActiune){
						actiuneInstanceListComplet.add(getMaxDate(instanceList, it.idActiune))
						idd = it.idActiune
					}
				}

				instanceList = getSortedList(actiuneInstanceListComplet, actiuneInstanceListComplet)
				
				if (instanceList.size>0)
					for (i in 0..instanceList.size()-1)
						use (TimeCategory){
							termenLimitaList[i] = (instanceList[i].termenLimita - today).days
						}	
					
				
			}
		}

		if (getControllerDomain().toString().equals("class produs.Produs")){
				instanceList = getSortedList(instanceList, actiuneInstanceListComplet)
				fieldNameListForList = getFieldNameListForListAddOns()
				fieldListForList = getFieldListForListAddOns()
		}

		[instanceList: instanceList, fieldNameListForList: fieldNameListForList, fieldListForList: fieldListForList, termenLimitaList: termenLimitaList, garantieFurnizorRamasa: garantieFurnizorRamasa, garantieClientRamasa: garantieClientRamasa, dataCreareInceput: params.dataCreareInceput, dataCreareFinal: params.dataCreareFinal, dataComandaClientInceput: params.dataComandaClientInceput, dataComandaClientFinal: params.dataComandaClientFinal, dataProductieInceput: params.dataProductieInceput, dataProductieFinal: params.dataProductieFinal, termenPornireInceput: params.termenPornireInceput, termenPornireFinal: params.termenPornireFinal, activ: activ, inactiv: inactiv, toateOperatiile: toateOperatiile, total: getControllerDomain().list().size(), numberOfRows: numberOfRows, adminList: adminList]
	}

	String format(Date date){
		return date.format("dd.MM.yyyy")
	}
	
	String getSettingsValue(String cheie){

		String ret = null

		Settings.list().each{
			if (it.cheie==cheie)
				ret = it.valoare
		}

		return ret
	}

	void setSettingsValue(String cheie, String valoare){
		
		if (!getSettingsValue(cheie))
			new Settings(cheie: cheie, valoare: valoare).save(failOnError:true)
		else {
			Settings s = Settings.findWhere(cheie: cheie)
			s.valoare = valoare
			if (!s.save(flush: true)) {
				print "FAIL SAVE SETTING" + cheie
			}
        }

	}
		
	ArrayList<Object> getSortedList(ArrayList<Object> instanceList, ArrayList<Object> actiuneInstanceListComplet){
		
		def instanceLista

		if (instanceList.size() != 0 ){
		
			def ccce = getControllerDomain().createCriteria()
			
			instanceLista = ccce {
				or {
					for (i in 0..instanceList.size()-1){
						eq("id", instanceList.get(i)?.id)
					}
				}
				if (params.sort != 'stocListVandute' && params.sort!= 'stocListSum')
				if (params.sort!=null && params.order !=null){
				
					if (params.sort == 'firma.nume')
						firma {
							order("nume", "${params.order}")
						}
						
					if (params.sort == 'contact.nume')
						contact {
							order("nume", "${params.order}")
						}
					
					if (params.sort == 'etapa.nume')
						etapa {
							order("nume", "${params.order}")
						}
	
					if (params.sort == 'tema.nume')
						tema {
							order("nume", "${params.order}")
						}
						
					if (params.sort == 'user.nume')
						user {
							order("nume", "${params.order}")
						}
					
					if (params.sort == 'tipClient.tip')
						tipClient {
							order("tip", "${params.order}")
						}
					
					if (params.sort == 'tipEveniment.tip')
						tipEveniment {
							order("tipEv", "${params.order}")
						}
						
					if (params.sort != 'firma.nume' && params.sort != 'contact.nume' && params.sort != 'etapa.nume' && params.sort != 'tema.nume' && params.sort != 'user.nume' && params.sort != 'tipClient.tip' && params.sort != 'tipEveniment.tip')
						order("${params.sort}", "${params.order}")
				}
			}
		} else
			return actiuneInstanceListComplet
		
		return instanceLista
	}
	
	ArrayList<Object> getInstanceList(ArrayList<String> fieldListForList, boolean all, boolean enabledValue){
		
		def numberOfRows = getNumberOfRows()
		params.max=Math.min(params.max ? params.int('max') : numberOfRows,100)
		
		String fieldName
		String searchTerm
		
		def offset = params.offset?.toInteger() ?: 0
		
		def c = getControllerDomain().createCriteria()
			
		def instanceList = c {
			
			firstResult offset 
			
			fieldListForList.each {

				fieldName = "${it}"
				searchTerm = params."${it}"
				
				if ((fieldName !=null && !searchTerm.equals("") && searchTerm!=null) || (fieldName=='enabled')){

					if (fieldName.equals("tipClient")){
						tipClient {
							like ("tip", "%${searchTerm}%")
						}
					}
					
					if (fieldName.equals("tipEveniment")){
						tipEveniment {
							like ("tipEv", "%${searchTerm}%")
						}
					}
					
					else if (fieldName.equals("firma")){
						firma {
							like ("nume", "%${searchTerm}%")
						}
					}
					
					else if (fieldName.equals("contact")){
						contact {
							like ("nume", "%${searchTerm}%")
						}
					}
					
					else if (fieldName.equals("user")){
						user {
							like ("nume", "%${searchTerm}%")
						}
					}
					
					else if (fieldName.equals("tema")){
						tema {
							like ("nume", "%${searchTerm}%")
						}
					}
					
					else if (fieldName.equals("etapa")){
						etapa {
							like ("nume", "%${searchTerm}%")
						}
					}
					
					else if (fieldName.equals("enabled")) {
						if (!all)
							eq ("enabled", enabledValue)
					}
					
					else if (fieldName.equals("idActiune")) {
						if (searchTerm.isNumber())
							eq ("idActiune", "${searchTerm}".toInteger())
					}

					else {
						like("${it}", "%${searchTerm}%")
					}
					
				}

				fieldListForList.each {
					if ("${it}" == 'dataCreare'){
						if (params.dataCreareInceput!=null)
							gt("dataCreare", params.dataCreareInceput)
							
						if (params.dataCreareFinal != null)
							lt("dataCreare", params.dataCreareFinal)
					}
					
					if ("${it}" == 'dataComandaClient'){
						if (params.dataComandaClientInceput!=null)
							gt("dataComandaClient", params.dataComandaClientInceput)
							
						if (params.dataComandaClientFinal != null)
							lt("dataComandaClient", params.dataComandaClientFinal)
					}
					
					if ("${it}" == 'dataProductie'){
						if (params.dataProductieInceput!=null)
							gt("dataProductie", params.dataProductieInceput)
							
						if (params.dataProductieFinal != null)
							lt("dataProductie", params.dataProductieFinal)
					}
					
					if ("${it}" == 'termenPornire'){
						if (params.termenPornireInceput!=null)
							gt("termenPornire", params.termenPornireInceput)
							
						if (params.termenPornireFinal != null)
							lt("termenPornire", params.termenPornireFinal)
					}
				}
				
				if (getControllerDomain().toString().equals("class actiune.Actiune") || getControllerDomain().toString().equals("class actiune.Arhiva")){
					order("idActiune", "desc")
				}

				maxResults numberOfRows
			}
		}

		return instanceList
	}

	int getNumberOfRows(){
		
		String domain = "${getControllerDomain()}" 
		
		if (params.numberOfRows)
			setSettingsValue(domain, params.numberOfRows)
		
		if (getSettingsValue(domain))
			return getSettingsValue(domain).toInteger()
		else
			setSettingsValue(domain, '10')
		
		return getSettingsValue(domain).toInteger()
		
	}
	
	protected int getMaxIdActiune(){
		int max = 0
		Actiune.list().each{
			if (it.idActiune > max)
				max = it.idActiune
		}

		Arhiva.list().each{
			if (it.idActiune > max){
				max = it.idActiune
			}
		}

		return max
	}
	
}