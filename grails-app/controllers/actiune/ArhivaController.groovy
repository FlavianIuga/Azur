package actiune

import grails.plugins.springsecurity.Secured
import groovy.time.TimeCategory

import org.springframework.dao.DataIntegrityViolationException

import shared.BaseController


@Secured('ROLE_ADMIN')
class ArhivaController extends BaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "cautare", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		def fieldNameListForList = getFieldNameListForList()
		def fieldListForList = getFieldListForList()

        [instanceList: Arhiva.list(params), arhivaInstanceTotal: Arhiva.count(), fieldNameListForList:fieldNameListForList, fieldListForList: fieldListForList]
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
	
	protected String[] getFieldNameListForOperatii() {
		def instancePropertiesNames = []
		instancePropertiesNames.add("Etapa")
		instancePropertiesNames.add("Termen Pornire")
		instancePropertiesNames.add("Contact")
		instancePropertiesNames.add("Termen Limita")
		instancePropertiesNames.add("Document")
		instancePropertiesNames.add("User")
		instancePropertiesNames.add("Nota")
		
		return instancePropertiesNames
	}
	
	protected String[] getFieldListForOpetarii() {
		def instancePropertiesList = []
		instancePropertiesList.add("etapa")
		instancePropertiesList.add("termenPornire")
		instancePropertiesList.add("contact")
		instancePropertiesList.add("termenLimita")
		instancePropertiesList.add("documentPath")
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
	
   def create() {
	   params.idActiune = getMaxIdActiune() + 1

	   [instance: new Arhiva(params), fieldNameList:getFieldNameListForList(), fieldList: getFieldListForList()]
    }

    def save() {
        def arhivaInstance = new Arhiva(params)
        if (!arhivaInstance.save(flush: true)) {
            render(view: "create", model: [instance: arhivaInstance, fieldNameList:getFieldNameListForList(), fieldList: getFieldListForList()])
            return
        }

        flash.successMessage = message(code: 'default.created.message', args: [message(code: 'arhiva.label', default: 'Arhiva'), arhivaInstance.id])
        redirect(action: "cautare", id: arhivaInstance.id)
    }

    def show(Long id) {
        def arhivaInstance = Arhiva.get(id)
        if (!arhivaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'arhiva.label', default: 'Arhiva'), id])
            redirect(action: "cautare")
            return
        }

		def fieldNameList = getFieldNameListForList()
		def fieldList = getFieldListForList()
		
		[instance: arhivaInstance, fieldNameList:fieldNameList, fieldList: fieldList]
    }

    def edit(Long id) {
        def arhivaInstance = Arhiva.get(id)
        if (!arhivaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'arhiva.label', default: 'Arhiva'), id])
            redirect(action: "cautare")
            return
        }
		
		def fieldNameList = getFieldNameListForList()
		def fieldList = getFieldListForList()

        [instance: arhivaInstance, fieldNameList: fieldNameList, fieldList: fieldList]
    }

    def update(Long id, Long version) {
        def arhivaInstance = Arhiva.get(id)
        if (!arhivaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'arhiva.label', default: 'Arhiva'), id])
            redirect(action: "cautare")
            return
        }

        if (version != null) {
            if (arhivaInstance.version > version) {
                arhivaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'arhiva.label', default: 'Arhiva')] as Object[],
                          "Another user has updated this Arhiva while you were editing")
                render(view: "edit", model: [instance: arhivaInstance, fieldNameList:getFieldNameListForList(), fieldList: getFieldListForList()])
                return
            }
        }

        arhivaInstance.properties = params

        if (!arhivaInstance.save(flush: true)) {
            render(view: "edit", model: [instance: arhivaInstance, fieldNameList:getFieldNameListForList(), fieldList: getFieldListForList()])
            return
        }

        flash.successMessage = message(code: 'default.updated.message', args: [message(code: 'arhiva.label', default: 'Arhiva'), arhivaInstance.id])
        redirect(action: "cautare", id: arhivaInstance.id)
    }

    def delete(Long id) {
        def arhivaInstance = Arhiva.get(id)
        if (!arhivaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'arhiva.label', default: 'Arhiva'), id])
            redirect(action: "cautare")
            return
        }

        try {
            arhivaInstance.delete(flush: true)
            flash.successMessage = message(code: 'default.deleted.message', args: [message(code: 'arhiva.label', default: 'Arhiva'), id])
            redirect(action: "cautare")
        }
        catch (DataIntegrityViolationException e) {
            flash.errorMessage = message(code: 'default.not.deleted.message', args: [message(code: 'arhiva.label', default: 'Arhiva'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def completeShow(Long id) {
		
		def fieldNameListForList = getFieldNameListForOperatii()
		def fieldListForList = getFieldListForOpetarii()
		
		def fieldNameListForEtapa = getFieldNameListForEtapa()
		def fieldListForEtapa = getFieldListForEtapa()
				
		def arhivaInstance = Arhiva.get(id)
		if (!arhivaInstance) {
			flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'arhiva.label', default: 'Arhiva'), id])
			redirect(action: "cautare")
			return
		}

		def c = getControllerDomain().createCriteria()
		def instanceList = c {
			eq("idActiune", arhivaInstance.idActiune)
			
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
			flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'arhiva.label', default: 'Arhiva'), id])
			redirect(action: "cautare")
			return
		}
		
		def newEtapaInstance
		if (params.edit){
			newEtapaInstance = getControllerDomain().get(params.edit)
		} else {
			newEtapaInstance = new Arhiva()
		}
		def terLimita
		use (TimeCategory){
			terLimita = (arhivaInstance.termenLimita - new Date()).days
		}
		
		[instanceList: instanceList, arhivaInstance: arhivaInstance, fieldNameListForList: fieldNameListForList, fieldListForList: fieldListForList, newEtapaInstance: newEtapaInstance, fieldNameListForEtapa: fieldNameListForEtapa, fieldListForEtapa: fieldListForEtapa, terLimita: terLimita]
	}
	
	def dezarhiveaza(Long id){
		
		Arhiva act = Arhiva.get(id)
		def arhive = Arhiva.findAllWhere(idActiune: act.idActiune)

		arhive.each{
			Actiune acti = new Actiune(it.properties)
			if (!acti.save(flush: true))
				print "FAIL SAVE IN ARHIVA"
			it.delete()
		}

		redirect(action: 'cautare')
	}
	
	private Arhiva getMaxDate(List firInstanceList, int idFir){
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
	
	protected getControllerDomain(){
		return Arhiva
	}
}