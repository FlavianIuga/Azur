package actiune

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService
import groovy.time.*

import org.springframework.dao.DataIntegrityViolationException

import security.User
import shared.BaseController


@Secured('ROLE_USER')
class ActiuneController extends BaseController {

	SpringSecurityService springSecurityService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "cautare", params: params)
    }

//    def list(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
//		
//		def fieldNameListForList = getFieldNameListForList()
//		def fieldListForList = getFieldListForList()
//
//		[instanceList: Actiune.list(params), fieldNameListForList: fieldNameListForList, fieldListForList: fieldListForList]
//    }

	def listComplete(Integer max) {
		params.max = Math.min(max ?: 10, 100)

		def fieldNameListForList = getFieldNameList()
		def fieldListForList = getFieldList()
				
		def actiuneInstanceListComplet =[]
		
		def c = Actiune.createCriteria()
		def actiuneInstanceLista = c {
			order("idActiune", "asc")
		}
		def idd = ""
		def actiunea
		
		actiuneInstanceLista.each {
			
			if (idd != it.idActiune){
				actiunea = getMaxDate(actiuneInstanceLista, it.idActiune)
				actiuneInstanceListComplet.add(actiunea)
				idd = it.idActiune
			}
		}
		
		def instanceList
		if (actiuneInstanceListComplet.size()!=0){
			def cc = Actiune.createCriteria()
			instanceList = cc {
				or {
					if (actiuneInstanceListComplet.size()>0){
						for (i in 0..actiuneInstanceListComplet.size()-1){
							eq("id", actiuneInstanceListComplet.get(i).id)
						}
					}
				}
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
						
					if (params.sort != 'firma.nume' && params.sort != 'contact.nume' && params.sort != 'etapa.nume' && params.sort != 'tema.nume' && params.sort != 'user.nume')
						order("${params.sort}", "${params.order}")
				}
			}
		} else {
			instanceList = actiuneInstanceListComplet
		}

		[instanceList: instanceList, fieldNameListForList: fieldNameListForList, fieldListForList: fieldListForList, termenLimitaList: termenLimitaList]
	}

	protected String[] getFieldNameList() {
		def instancePropertiesNames = []
		instancePropertiesNames.add("Id Actiune")
		instancePropertiesNames.add("Subiect")
		instancePropertiesNames.add("Firma")
		instancePropertiesNames.add("Etapa")
		instancePropertiesNames.add("Termen Pornire")
		instancePropertiesNames.add("Contact")
		instancePropertiesNames.add("Termen Limita")
		instancePropertiesNames.add("Tema")
		instancePropertiesNames.add("Document")
		instancePropertiesNames.add("User")
		instancePropertiesNames.add("Nota")
		
		return instancePropertiesNames
	}

	protected String[] getFieldList() {
		def instancePropertiesList = []
		instancePropertiesList.add("idActiune")
		instancePropertiesList.add("subiect")
		instancePropertiesList.add("firma")
		instancePropertiesList.add("etapa")
		instancePropertiesList.add("termenPornire")
		instancePropertiesList.add("contact")
		instancePropertiesList.add("termenLimita")
		instancePropertiesList.add("tema")
		instancePropertiesList.add("documentPath")
		instancePropertiesList.add("user")
		instancePropertiesList.add("observatii")
		
		return instancePropertiesList
	}
	
	protected String[] getFieldNameListForList() {
		def instancePropertiesNames = []
		instancePropertiesNames.add("Id")
		instancePropertiesNames.add("Subiect")
		instancePropertiesNames.add("Etapa")
		instancePropertiesNames.add("Contact")
		instancePropertiesNames.add("Firma")
		instancePropertiesNames.add("Document")
		instancePropertiesNames.add("Termen Pornire")
		instancePropertiesNames.add("Termen Limita")
		instancePropertiesNames.add("Tema")
		instancePropertiesNames.add("User")
		instancePropertiesNames.add("Nota")
		
		return instancePropertiesNames
	}
	
	protected String[] getFieldListForList() {
		def instancePropertiesList = []
		instancePropertiesList.add("idActiune")
		instancePropertiesList.add("subiect")
		instancePropertiesList.add("etapa")
		instancePropertiesList.add("contact")
		instancePropertiesList.add("firma")
		instancePropertiesList.add("documentPath")
		instancePropertiesList.add("termenPornire")
		instancePropertiesList.add("termenLimita")
		instancePropertiesList.add("tema")
		instancePropertiesList.add("user")
		instancePropertiesList.add("observatii")
		
		return instancePropertiesList
	}
	
	protected String[] getFieldNameListForForm() {
		def instancePropertiesNames = []
		instancePropertiesNames.add("Id Actiune")
		instancePropertiesNames.add("Subiect")
		instancePropertiesNames.add("Firma")
		instancePropertiesNames.add("Etapa")
		instancePropertiesNames.add("Termen Pornire")
		instancePropertiesNames.add("Contact")
		instancePropertiesNames.add("Termen Limita")
		instancePropertiesNames.add("Tema")
		instancePropertiesNames.add("Document")
		instancePropertiesNames.add("Nota")
		
		return instancePropertiesNames
	}
	
	protected String[] getFieldListForForm() {
		def instancePropertiesList = []
		instancePropertiesList.add("idActiune")
		instancePropertiesList.add("subiect")
		instancePropertiesList.add("firma")
		instancePropertiesList.add("etapa")
		instancePropertiesList.add("termenPornire")
		instancePropertiesList.add("contact")
		instancePropertiesList.add("termenLimita")
		instancePropertiesList.add("tema")
		instancePropertiesList.add("documentPath")
		instancePropertiesList.add("observatii")
		
		return instancePropertiesList
	}
	
	protected String[] getFieldNameListForOperatii() {
		def instancePropertiesNames = []
		instancePropertiesNames.add("Etapa")
		instancePropertiesNames.add("Contact")
		instancePropertiesNames.add("Document")
		instancePropertiesNames.add("Termen Pornire")
		instancePropertiesNames.add("Termen Limita")
		instancePropertiesNames.add("User")
		instancePropertiesNames.add("Nota")
		
		return instancePropertiesNames
	}
	
	protected String[] getFieldListForOpetarii() {
		def instancePropertiesList = []
		instancePropertiesList.add("etapa")
		instancePropertiesList.add("contact")
		instancePropertiesList.add("documentPath")
		instancePropertiesList.add("termenPornire")
		instancePropertiesList.add("termenLimita")
		instancePropertiesList.add("user")
		instancePropertiesList.add("observatii")
		
		return instancePropertiesList
	}
	
	protected String[] getFieldNameListForEtapa() {
		def instancePropertiesNames = []
		instancePropertiesNames.add("Etapa")
		instancePropertiesNames.add("Termen Pornire")
		instancePropertiesNames.add("Contact")
		instancePropertiesNames.add("Termen Limita")
		instancePropertiesNames.add("Document")
		instancePropertiesNames.add("Nota")
		
		return instancePropertiesNames
	}
	
	protected String[] getFieldListForEtapa() {
		def instancePropertiesList = []
		instancePropertiesList.add("etapa")
		instancePropertiesList.add("termenPornire")
		instancePropertiesList.add("contact")
		instancePropertiesList.add("termenLimita")
		instancePropertiesList.add("documentPath")
		instancePropertiesList.add("observatii")
		
		return instancePropertiesList
	}
	
	def listCompleteSearch(Integer max){
		params.max = Math.min(max ?: 10, 100)
		
		def fieldNameList = getFieldNameList()
		def fieldList = getFieldList()
		
		def actiuneInstanceListComplet =[]
		
		def c = Actiune.createCriteria()
		def actiuneInstanceLista = c {
			order("idActiune", "asc")
		}
		def idd = ""
		def actiunea
		
		actiuneInstanceLista.each {
			
			if (idd != it.idActiune){
				actiunea = getMaxDate(actiuneInstanceLista, it.idActiune)
				actiuneInstanceListComplet.add(actiunea)
				idd = it.idActiune
			} 
		}

		try {
		int i=0
		while (actiuneInstanceListComplet.get(i)!=null){
			if (!(actiuneInstanceListComplet.get(i).idActiune).contains(params.searchTerm)){
				actiuneInstanceListComplet.remove(i)
			} else
				i++
		}
		}catch (Exception e){
		
		}

		[instanceList: actiuneInstanceListComplet, fieldNameList:fieldNameList, fieldList: fieldList]
	}
	
	private Actiune getMaxDate(List firInstanceList, int idFir){
		def Date maxDate
		def idu
		
		firInstanceList.each{
			if (it.idActiune == idFir){
				if (it.termenPornire > maxDate)
					maxDate = it.termenPornire
					idu = it.id
			}
		}
		
		def c = getControllerDomain().createCriteria()
		def firr = c {
			eq("id", idu)
		}
		
		return firr[0]
	}
	
	def create() {
	   params.idActiune = getMaxIdActiune() + 1

	   [instance: new Actiune(params), fieldNameList:getFieldNameListForForm(), fieldList: getFieldListForForm()]
	}
	
	def ajaxGetContacts(){
		def firma = Firma.get(params.id)
		
		def contacte = Contact.findAllByFirma(firma) as JSON
		print contacte
		
		render contacte
	}
	
	def ajaxGetDefaultTermenLimita(Long id){
		
	}
	
    def save() {
		
//		if (params.termenLimita.toInteger() < 0 ) {
//			print "FAIL SAVE ETAPA"
//			flash.errorMessage = "Termenul limita nu poate fi negativ"
//			//redirect(controller: "actiune", action: "create", id: params.id)
//			return
//		}
		
		Actiune lastActiuneLast = getMaxDate(Actiune.findAllByIdActiune(params.idActiune), params.idActiune.toInteger())
		if (lastActiuneLast!=null) {
			Actiune lastActiune = Actiune.findById(lastActiuneLast.id)
			lastActiune.termenLimita = new Date()
			if (!lastActiune.save(flush: true)) {
				print ("FAIL SAVE LAST ACTIUNE")
			}
		}

		int termenLimitaInt
		if (params.termenLimita=="")
			termenLimitaInt = 0
		else if (params.termenLimita.isNumber()){
			termenLimitaInt = Integer.parseInt(params.termenLimita)
			
			if (termenLimitaInt < 0){
				flash.errorMessage = "Termenul limita nu poate fi negativ"
				if (params.action=='save'){
					render(view: "create", model: [instance: new Actiune(params), fieldNameList: getFieldNameListForForm(), fieldList: getFieldListForForm()])
					return
				}
				if (params.action=='index'){
					redirect(controller: "actiune", action: "completeShow", id: params.id)
					return
				}
				return
			}
			
			} else {
				termenLimitaInt = 0
				params.termenLimita = new Date()
				flash.errorMessage = "Termenul limita nu este numar!"
				if (params.action=='save'){
					render(view: "create", model: [instance: new Actiune(params), fieldNameList: getFieldNameListForForm(), fieldList: getFieldListForForm()])
					return
				}
				if (params.action=='index'){
					redirect(controller: "actiune", action: "completeShow", id: params.id)
					return
				}
			}
			
		Date tLimita
		use (TimeCategory){
			tLimita = new Date() + termenLimitaInt.days
		}
		params.termenLimita = tLimita

    	def actiuneInstance
		if (params.edit!=null && params.edit != ""){
			actiuneInstance = Actiune.get(params.edit)
			actiuneInstance.properties = params
		} else {
			actiuneInstance = new Actiune(params)
			actiuneInstance.termenPornire = new Date()
				
			def actiuneBD = Actiune.findByIdActiune(params.idActiune)
			
			if (actiuneBD!=null){
				actiuneInstance.idActiune = actiuneBD.idActiune
				actiuneInstance.subiect = actiuneBD.subiect
				actiuneInstance.tema = actiuneBD.tema
				actiuneInstance.firma = actiuneBD.firma
			}
		}

		if (springSecurityService?.currentUser?.id == null)
			actiuneInstance.user = null
		else
			actiuneInstance.user = User.findById(springSecurityService?.currentUser?.id)
		
		def rootPath = ""
		def documentPath = ""
		def f = request.getFile('documentPath')
		if (f!=null && !f.empty){

			rootPath = getSettingsValue("rootFilesPath")
			print "getting " + rootPath
			
			if (rootPath==null || rootPath=="")
				rootPath = "C:"
				
			documentPath = rootPath + "/Actiuni/" + params.idActiune + "/" + actiuneInstance.etapa.toString() + " " + format(actiuneInstance.termenPornire).toString().replace(':','-') + " " + f.originalFilename
			
			def storagePathDirectory = new File(documentPath)
			if (!storagePathDirectory.exists()){
				print "Creating directory ${documentPath}: "
				if (storagePathDirectory.mkdirs()){
					println "SUCCES"
				} else {
					println "FAIL"
				}
			}		

			f.transferTo(new File(documentPath))
		} 

		def ides = actiuneInstance.id
		actiuneInstance.documentPath = documentPath
				
		
		if (!actiuneInstance.save(flush: true)) {
			print "FAIL SAVE ETAPA"
			render(view: "create", model: [instance: actiuneInstance, fieldNameList:getFieldNameListForForm(), fieldList: getFieldListForForm()])
            return
		}
		
        flash.successMessage = message(code: 'default.created.message', args: [message(code: 'actiune.label', default: 'Actiune'), actiuneInstance.idActiune])
        redirect(controller: "actiune", action: "completeShow", id: actiuneInstance.id)
    }

    def show(Long id) {
        def actiuneInstance = Actiune.get(id)
        if (!actiuneInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'actiune.label', default: 'Actiune'), id])
            redirect(action: "cautare")
            return
        }

		def fieldNameList = getFieldNameList()
		def fieldList = getFieldList()
		
        [instance: actiuneInstance, fieldNameList:fieldNameList, fieldList: fieldList]
    }
	
	def completeShow(Long id) {
		
		def fieldNameListForList = getFieldNameListForOperatii()
		def fieldListForList = getFieldListForOpetarii()
		
		def fieldNameListForEtapa = getFieldNameListForEtapa()
		def fieldListForEtapa = getFieldListForEtapa()
				
		def actiuneInstance = Actiune.get(id)
		if (!actiuneInstance) {
			flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'actiune.label', default: 'Actiune'), id])
			redirect(action: "cautare")
			return
		}

		def c = Actiune.createCriteria()
		def instanceList = c {
			eq("idActiune", actiuneInstance.idActiune)
			
			if (params.sort!=null && params.order!=null){
			
				if (params.sort == 'contact.nume')
					contact {
						order("nume", "${params.order}")
					}
				
				if (params.sort == 'etapa.nume')
					etapa {
						order("nume", "${params.order}")
					}
					
				if (params.sort == 'user.nume')
					user {
						order("nume", "${params.order}")
					}
					if (params.sort != 'contact.nume' && params.sort != 'etapa.nume' && params.sort != 'user.nume')
					order("${params.sort}", "${params.order}")

			} else {
				order("termenPornire", "asc")
			}
		}

		if (!instanceList) {
			flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'actiune.label', default: 'Actiune'), id])
			redirect(action: "cautare")
			return
		}
		
		def newEtapaInstance
		if (params.edit){
			newEtapaInstance = Actiune.get(params.edit)
		} else {
			newEtapaInstance = new Actiune()
		}
		def terLimita
		use (TimeCategory){
			terLimita = (actiuneInstance.termenLimita - new Date()).days
		}
		
		[instanceList: instanceList, actiuneInstance: actiuneInstance, fieldNameListForList: fieldNameListForList, fieldListForList: fieldListForList, newEtapaInstance: newEtapaInstance, fieldNameListForEtapa: fieldNameListForEtapa, fieldListForEtapa: fieldListForEtapa, terLimita: terLimita] 
	}

    def edit(Long id) {
        def actiuneInstance = Actiune.get(id)
        if (!actiuneInstance) {
            flash.infommessage = message(code: 'default.not.found.message', args: [message(code: 'actiune.label', default: 'Actiune'), id])
            redirect(action: "cautare")
            return
        }

		def fieldNameList = getFieldNameList()
		def fieldList = getFieldList()
		
        [instance: actiuneInstance, fieldNameList: fieldNameList, fieldList: fieldList]
    }

    def update(Long id, Long version) {
        def actiuneInstance = Actiune.get(id)
        if (!actiuneInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'actiune.label', default: 'Actiune'), id])
            redirect(action: "cautare")
            return
        }

        if (version != null) {
            if (actiuneInstance.version > version) {
                actiuneInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'fir.label', default: 'Fir')] as Object[],
                          "Another user has updated this Fir while you were editing")
                render(view: "edit", model: [instance: actiuneInstance, fieldNameList: getFieldNameListForForm(), fieldList: getFieldListForForm()])
                return
            }
        }

        actiuneInstance.properties = params

        if (!actiuneInstance.save(flush: true)) {
            render(view: "edit", model: [firInstance: actiuneInstance])
            return
        }

        flash.successMessage = message(code: 'default.updated.message', args: [message(code: 'actiune.label', default: 'Actiune'), actiuneInstance.idActiune])
        redirect(action: "show", id: actiuneInstance.idActiune)
    }

    def delete(Long id) {
        def firInstance = Actiune.get(id)
        if (!firInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'actiune.label', default: 'Actiune'), id])
            redirect(action: "cautare")
            return
        }

        try {
            firInstance.delete(flush: true)
            flash.successMessage = message(code: 'default.deleted.message', args: [message(code: 'actiune.label', default: 'Actiune'), id])
            redirect(action: "cautare")
        }
        catch (DataIntegrityViolationException e) {
            flash.errorMessage = message(code: 'default.not.deleted.message', args: [message(code: 'actiune.label', default: 'Actiune'), id])
            redirect(action: "show", id: id)
        }
    }
	
    def arhiveaza(Long id){
    	
    	Actiune act = Actiune.get(id)
    	def actiuni = Actiune.findAllWhere(idActiune: act.idActiune)

    	actiuni.each{
    		print "prop: " + it.properties
    		Arhiva arh = new Arhiva(it.properties)
    		if (!arh.save(flush: true))
    			print "FAIL SAVE IN ARHIVA"
    		it.delete()
    	}

    	redirect(action: 'cautare')
    }
    
	protected getControllerDomain(){
		return Actiune
	}
}