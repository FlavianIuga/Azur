package produs

import grails.plugins.springsecurity.Secured
import groovy.time.*

import org.springframework.dao.DataIntegrityViolationException

import produs.Eveniment
import shared.BaseController


@Secured('ROLE_USER')
class ProdusController extends BaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "cautare", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		def fieldNameListForList = getFieldNameListForListAddOns()
		def fieldListForList = getFieldListForListAddOns()

// 		Incerc link la SN in campul de observatii la list

//		def obsLink = []
//		def obsLinkId = []
//		def produsList = Produs.list(params)
//		def produsList2 = Produs.list(params)
//
//		for (i in 1..produsList.size()){
//			produsList2.each{
//
//				if (produsList[i].observatii.contains("${it.SN}") && !("${it.SN}".replace(' ', '').equals("")) && "${it.SN}"!=null ){
//					obsLink[it.id] = "${it.SN}"
//					obsLinkId[it.id] = "${it.id}"
//				}
//			}
//		}
		
		def garantieFurnizorRamasa = getGarantieFurnizorRamasa()
		def garantieClientRamasa = getGarantieClientRamasa()

		[instanceList: Produs.list(params), fieldNameListForList: fieldNameListForList, fieldListForList: fieldListForList, garantieFurnizorRamasa: garantieFurnizorRamasa, garantieClientRamasa: garantieClientRamasa]
    }

	protected getGarantieFurnizorRamasa(){
		def garantieFurnizorRamasa = []
		def now = new Date()

		if (Produs.list(params).size() > 0){
			for (i in 0..Produs.list(params).size()-1){
				
				def ite = Produs.list(params)[i]
				
				if (ite!=null){
				def dataFacturaFurnizor = ite.dataFacturaFurnizor
				def garantieFurnizor = ite.garantieFurnizor
				
				use (TimeCategory){
					
					if (dataFacturaFurnizor !=null && garantieFurnizor!=null) {
						garantieFurnizorRamasa[i] = (dataFacturaFurnizor + garantieFurnizor?.months - now).days + " zile"
						if ((dataFacturaFurnizor + garantieFurnizor?.months < now) )
							garantieFurnizorRamasa[i] += " (expirat)"
					}
				}
				}
		}
		}
		return garantieFurnizorRamasa
	}
	
	protected getGarantieClientRamasa(){
		def garantieClientRamasa = []
		def now = new Date()

		if (Produs.list(params).size() > 0){
			for (i in 0..Produs.list(params).size()-1){
				
				def ite = Produs.list(params)[i]
				if (ite!=null){
					def dataCertificatGarantie = ite.dataCertificatGarantie
					def garantieClient = ite.garantieClient
					
					use (TimeCategory){

						if (dataCertificatGarantie!=null && garantieClient != null){
							garantieClientRamasa[i] = (dataCertificatGarantie + garantieClient?.months - now).days + " zile"
							if ((dataCertificatGarantie + garantieClient?.months < now) )
								garantieClientRamasa[i] += " (expirat)"
						}
				}
				}
			}
		}
		return garantieClientRamasa
	}
	
	protected String[] getFieldNameList() {
		def fieldNameList = []
		fieldNameList.add("Denumire")
		fieldNameList.add("Cod Produs")
		fieldNameList.add("SN")
		fieldNameList.add("Revizie")
		fieldNameList.add("HMI")
		fieldNameList.add("Data Productie")
		fieldNameList.add("Versiune")
		
		fieldNameList.add("Comanda GE IP")
		fieldNameList.add("Data Comanda GE IP")
		fieldNameList.add("Factura GE IP")
		fieldNameList.add("Data Factura GE IP")
		fieldNameList.add("Garantie GE IP")
		
		fieldNameList.add("Licenta End User")
		fieldNameList.add("Firma")
		fieldNameList.add("Comanda Client")
		fieldNameList.add("Data Comanda Client")
		fieldNameList.add("Factura Client")
		fieldNameList.add("Data Factura Client")
		fieldNameList.add("Garantie Client")
		fieldNameList.add("Certificat Garantie")
		fieldNameList.add("Data Certificat Garantie")
		
		fieldNameList.add("Key Type")
		fieldNameList.add("CSN")
		fieldNameList.add("Data Expirare GCC")
		fieldNameList.add("GEF Order")
		
		fieldNameList.add("Observatii")
		return fieldNameList
	}

	protected String[] getFieldList() {
		def fieldsList = []
		fieldsList.add("denumire")
		fieldsList.add("codProdus")
		fieldsList.add("SN")
		fieldsList.add("revizie")
		fieldsList.add("HMI")
		fieldsList.add("dataProductie")
		fieldsList.add("versiune")
	
		fieldsList.add("nrComandaFurnizor")
		fieldsList.add("dataComandaFurnizor")
		fieldsList.add("nrFacturaFurnizor")
		fieldsList.add("dataFacturaFurnizor")
		fieldsList.add("garantieFurnizor")

		fieldsList.add("licentaEndUser")
		fieldsList.add("firma")
		fieldsList.add("nrComandaClient")
		fieldsList.add("dataComandaClient")
		fieldsList.add("nrFacturaClient")
		fieldsList.add("dataFacturaClient")
		fieldsList.add("garantieClient")
		fieldsList.add("nrCertificatGarantie")
		fieldsList.add("dataCertificatGarantie")
	
		fieldsList.add("keyType")
		fieldsList.add("CSN")
		fieldsList.add("dataExpirareGCC")
		fieldsList.add("GEFOrder")
		
		fieldsList.add("observatii")
		return fieldsList
	}

	protected String[] getFieldNameListForList() {
		def fieldNameList = []
		fieldNameList.add("Denumire")
		fieldNameList.add("Cod Produs")
		fieldNameList.add("SN")
		fieldNameList.add("Revizie")
		fieldNameList.add("Data Productie")
//		fieldNameList.add("Versiune")
		
//		fieldNameList.add("Nr Comanda Furnizor")
//		fieldNameList.add("Data Comanda Furnizor")
//		fieldNameList.add("Nr Factura Furnizor")
//		fieldNameList.add("Data Factura Furnizor")
//		fieldNameList.add("Garantie Furnizor")
		
		fieldNameList.add("Firma")
//		fieldNameList.add("Nr Comanda Client")
//		fieldNameList.add("Data Comanda Client")
//		fieldNameList.add("Nr Factura Client")
//		fieldNameList.add("Data Factura Client")
//		fieldNameList.add("Garantie Client")
//		fieldNameList.add("Nr Certificat Garantie")
		fieldNameList.add("Data Certificat Garantie")
		
		fieldNameList.add("Observatii")
		return fieldNameList
	}

	protected String[] getFieldListForList() {
		def fieldsList = []
		fieldsList.add("denumire")
		fieldsList.add("codProdus")
		fieldsList.add("SN")
		fieldsList.add("revizie")
		fieldsList.add("dataProductie")
//		fieldsList.add("versiune")
	
//		fieldsList.add("nrComandaFurnizor")
//		fieldsList.add("dataComandaFurnizor")
//		fieldsList.add("nrFacturaFurnizor")
//		fieldsList.add("dataFacturaFurnizor")
//		fieldsList.add("garantieFurnizor")

		fieldsList.add("firma")
//		fieldsList.add("nrComandaClient")
//		fieldsList.add("dataComandaClient")
//		fieldsList.add("nrFacturaClient")
//		fieldsList.add("dataFacturaClient")
//		fieldsList.add("garantieClient")
//		fieldsList.add("nrCertificatGarantie")
		fieldsList.add("dataCertificatGarantie")
	
		fieldsList.add("observatii")
		return fieldsList
	}
	
	protected String[] getFieldNameListForListAddOns() {
		def fieldNameList = []
		fieldNameList.add("Denumire")
		fieldNameList.add("Cod Produs")
		fieldNameList.add("SN")
		fieldNameList.add("Revizie")
		fieldNameList.add("Data Productie")
//		fieldNameList.add("Versiune")
		
//		fieldNameList.add("Nr Comanda Furnizor")
//		fieldNameList.add("Data Comanda Furnizor")
//		fieldNameList.add("Nr Factura Furnizor")
//		fieldNameList.add("Data Factura Furnizor")
//		fieldNameList.add("Garantie Furnizor")
		
		fieldNameList.add("Firma")
//		fieldNameList.add("Nr Comanda Client")
		//fieldNameList.add("Data Comanda Client")
//		fieldNameList.add("Nr Factura Client")
//		fieldNameList.add("Data Factura Client")
//		fieldNameList.add("Garantie Client")
//		fieldNameList.add("Nr Certificat Garantie")
		fieldNameList.add("Data Certificat Garantie")
		fieldNameList.add("Gr. GE IP")
		fieldNameList.add("Gr. Client")
		
		fieldNameList.add("Observatii")
		return fieldNameList
	}

	protected String[] getFieldListForListAddOns() {
		def fieldsList = []
		fieldsList.add("denumire")
		fieldsList.add("codProdus")
		fieldsList.add("SN")
		fieldsList.add("revizie")
		fieldsList.add("dataProductie")
//		fieldsList.add("versiune")
	
//		fieldsList.add("nrComandaFurnizor")
//		fieldsList.add("dataComandaFurnizor")
//		fieldsList.add("nrFacturaFurnizor")
//		fieldsList.add("dataFacturaFurnizor")
//		fieldsList.add("garantieFurnizor")

		fieldsList.add("firma")
//		fieldsList.add("nrComandaClient")
		//fieldsList.add("dataComandaClient")
//		fieldsList.add("nrFacturaClient")
//		fieldsList.add("dataFacturaClient")
//		fieldsList.add("garantieClient")
//		fieldsList.add("nrCertificatGarantie")
		fieldsList.add("dataCertificatGarantie")
		fieldsList.add("garantieFurnizorRamasa")
		fieldsList.add("garantieClientRamasa")
		
		fieldsList.add("observatii")
		return fieldsList
	}
	
    def create() {
		
		[instance: new Produs(params), fieldNameList: getFieldNameList(), fieldList: getFieldList()]
    }

    def save() {
		def produsInstance = new Produs(params)

		produsInstance.SN = produsInstance.SN.replace(" " ,"")
		produsInstance.codProdus = produsInstance.codProdus.replace(" " ,"")
		
		boolean unique = true
		Produs.list().each {
			if (it.codificareIdentica(produsInstance) && it.id != produsInstance.id)
				unique = false
		}

		if (!unique){
			flash.errorMessage = "Codificarea nu este unica"
			render(view: "edit", model: [instance: produsInstance, fieldNameList: getFieldNameList(), fieldList: getFieldList()])
			return
		} else {
			print "SAVING! in SAVE"
			if (!produsInstance.save(flush: true)) {
				render(view: "create", model: [instance: produsInstance, fieldNameList: getFieldNameList(), fieldList: getFieldList()])
				return
			}
		}

        flash.successMessage = message(code: 'default.created.message', args: [message(code: 'produs.label', default: 'Produs'), produsInstance.id])
        redirect(action: "show", id: produsInstance.id)
    }

    def show(Long id) {
		def produsInstance = Produs.get(id)
        if (!produsInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'produs.label', default: 'Produs'), id])
            redirect(action: "cautare")
            return
        }

		def obsLink
		def obsLinkId
		def produsList = Produs.list()
		produsList.each{

			if (produsInstance.observatii?.contains("${it.SN}") && !("${it.SN}".replace(' ', '').equals("")) && "${it.SN}"!=null ){
				obsLink = "${it.SN}"
				obsLinkId = "${it.id}"
			}
		}
		
		def expirareGarantieFurnizor
		def garantieRamasaFurnizor
		def expirareGarantieClient
		def garantieRamasaClient
		def now = new Date()
		use (TimeCategory){
			if (produsInstance.garantieFurnizor!=null){
				expirareGarantieFurnizor =  (produsInstance.dataFacturaFurnizor + (produsInstance.garantieFurnizor).months).format("MMMMMMM dd, yyyy")
				garantieRamasaFurnizor = (produsInstance.dataFacturaFurnizor + produsInstance.garantieFurnizor.months - now).days + " zile"
				if ((produsInstance.dataFacturaFurnizor + produsInstance.garantieFurnizor.months < now) )
					garantieRamasaFurnizor += " (expirat)"
				
				if (produsInstance.dataCertificatGarantie!=null){
					expirareGarantieClient =  (produsInstance.dataCertificatGarantie + (produsInstance.garantieClient).months).format("MMMMMMM dd, yyyy")
					garantieRamasaClient = (produsInstance.dataCertificatGarantie + produsInstance.garantieClient.months - now).days + " zile"
					if (produsInstance.dataCertificatGarantie + produsInstance.garantieClient.months < now)
						garantieRamasaClient += " (expirat)"
				}
			}
		}
		
		def fieldNameList = getFieldNameList()
		def fieldList = getFieldList()
		
		EvenimentController e = new EvenimentController()
		
		def fieldNameListEveniment = e.getFieldNameListForShow()
		def fieldListEveniment = e.getFieldListForShow()
		
		def evenimentInstance
				
		if (params.edit){
			evenimentInstance = Eveniment.get(params.edit)
		} else {
			evenimentInstance = new Eveniment()
		}
		
		evenimentInstance.produs = Produs.get(params.id)
		
		if (params.removee){
			Eveniment.get(params.removee)?.delete()
			evenimentInstance = new Eveniment()
			redirect(action: "show", id: produsInstance.id)
			return
		}
		
        [instance: produsInstance, evenimentInstanceList: Eveniment.findAllWhere(produs: Produs.get(params.id)), fieldNameListEveniment: fieldNameListEveniment, evenimentInstance: evenimentInstance, fieldListEveniment: fieldListEveniment, fieldNameList: fieldNameList, fieldList: fieldList, expirareGarantieFurnizor: expirareGarantieFurnizor, garantieRamasaFurnizor: garantieRamasaFurnizor, expirareGarantieClient: expirareGarantieClient, garantieRamasaClient: garantieRamasaClient, obsLink: obsLink, obsLinkId: obsLinkId]
    }

    def edit(Long id) {
        def produsInstance = Produs.get(id)
        if (!produsInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'produs.label', default: 'Produs'), id])
            redirect(action: "cautare")
            return
        }

        [instance: produsInstance, fieldNameList: getFieldNameList(), fieldList: getFieldList()]
    }

    def update(Long id, Long version) {
		def produsInstance = Produs.get(id)
		
        if (!produsInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'produs.label', default: 'Produs'), id])
            redirect(action: "cautare")
            return
        }

        if (version != null) {
            if (produsInstance.version > version) {
                produsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'produs.label', default: 'Produs')] as Object[],
                          "Another user has updated this Produs while you were editing")
                render(view: "edit", model: [instance: produsInstance, fieldNameList: getFieldNameList(), fieldList: getFieldList()])
                return
            }
        }

        produsInstance.properties = params

		boolean unique = true
		Produs.list().each {
			if (it.codificareIdentica(produsInstance) && it.id != produsInstance.id)
				unique = false
		}
		
		
		if (!unique){
			flash.errorMessage = "Codificarea nu este unica"
			produsInstance.SN = ""
			produsInstance.codProdus = ""
			render(view: "edit", model: [instance: produsInstance, fieldNameList: getFieldNameList(), fieldList: getFieldList()])
			return
		} else {
			print "SAVING"
	        if (!produsInstance.save(flush: true)) {
				
	            render(view: "edit", model: [instance: produsInstance, fieldNameList: getFieldNameList(), fieldList: getFieldList()])
				return
	        }
		}

        flash.sucessMessage = message(code: 'default.updated.message', args: [message(code: 'produs.label', default: 'Produs'), produsInstance.id])
        redirect(action: "show", id: produsInstance.id)
    }

    def delete(Long id) {
		
		Eveniment.list().each{
			if (Produs.findById(it.produs.id).id == id )
				it.delete()
		}
		
        def produsInstance = Produs.get(id)
        if (!produsInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'produs.label', default: 'Produs'), id])
            redirect(action: "cautare")
            return
        }

        try {
            produsInstance.delete(flush: true)
            flash.sucessMessage = message(code: 'default.deleted.message', args: [message(code: 'produs.label', default: 'Produs'), id])
            redirect(action: "cautare")
        }
        catch (DataIntegrityViolationException e) {
            flash.errorMessage = message(code: 'default.not.deleted.message', args: [message(code: 'produs.label', default: 'Produs'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def stoc(){
		
		def codProdusList = []
		def stocListInStoc = []
		def stocListVandute = []
		def stocListPerioadaTimp = []
		def stocTermenInceput = params.stocTermenInceput
		def stocTermenFinal = params.stocTermenFinal
		
		Produs previous = new Produs()
		
		int j = -1
		if (Produs.list().size>0)
			for (i in 0..Produs.list().size()-1){
				def ite = Produs.list()[i]
				
				if (ite.codProdus != previous.codProdus)
					codProdusList.add(ite)

				previous = ite
			}
			
		if (codProdusList.size > 0)
			for (i in 0..codProdusList.size()-1){
				stocListInStoc[i] = new Integer(0)
				stocListVandute[i] = new Integer(0)
				stocListPerioadaTimp[i] = new Integer(0)
				
				Produs.list().each{
					if (codProdusList[i].codProdus == it.codProdus){
						if (it.firma == null)
							stocListInStoc[i]++
						else
							stocListVandute[i]++
						
						if (it.firma!=null && it.dataComandaClient > params.stocTermenInceput && it.dataComandaClient < params.stocTermenFinal)
							stocListPerioadaTimp[i]++
							
					}
				}
			}
		
		codProdusList = getSortedList(codProdusList, codProdusList)
			
		[codProdusList: codProdusList, stocListSum: stocListInStoc, stocListVandute: stocListVandute, stocListPerioadaTimp: stocListPerioadaTimp, stocTermenInceput: stocTermenInceput, stocTermenFinal: stocTermenFinal]
	}
	
	protected getControllerDomain(){
		return Produs
	}
}

