package actiune

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

import shared.BaseController


@Secured('ROLE_ADMIN')
class EtapaController extends BaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "cautare", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		params.activa=true
		
        [instanceList: Etapa.list(params), fieldNameListForList: getFieldNameListForList(), fieldListForList: getFieldListForList()]
    }

	protected String[] getFieldNameListForList() {
		def fieldNameList = []
		fieldNameList.add("Nume")
		fieldNameList.add("Termen Limita Implicit")
		fieldNameList.add("Activ")
		
		return fieldNameList
	}

	protected String[] getFieldListForList() {
		def fieldsList = []
		fieldsList.add("nume")
		fieldsList.add("termenLimitaImplicit")
		fieldsList.add("enabled")
		
		return fieldsList
	}
	
    def create() {

		[instance: new Etapa(params), fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()]
    }

    def save() {
		int termenLimitaInt
		if (params.termenLimitaImplicit=="")
			termenLimitaInt = 0
		else if (params.termenLimitaImplicit.isNumber())
			termenLimitaInt = Integer.parseInt(params.termenLimitaImplicit)
			else {
				termenLimitaInt = 0
				params.termenLimita = new Date()
				flash.errorMessage = "Termenul limita implcit nu este numar!"
				render(view: "create", model: [instance: new Etapa(params), fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
				return
			}
		
		params.termenLimitaImplicit = termenLimitaInt

		def etapaInstance = new Etapa(params)
		
        if (!etapaInstance.save(flush: true)) {
            render(view: "create", model: [instance: etapaInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
            return
        }

        flash.successMessage = message(code: 'default.created.message', args: [message(code: 'etapa.label', default: 'Etapa'), etapaInstance.id])
        redirect(action: "cautare")
    }

    def show(Long id) {
        def etapaInstance = Etapa.get(id)
        if (!etapaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'etapa.label', default: 'Etapa'), id])
            redirect(action: "cautare")
            return
        }
		
		def fieldNameList = getFieldNameListForList()
		def fieldList = getFieldListForList()

        [instance: etapaInstance, fieldNameList:fieldNameList, fieldList: fieldList]
    }

    def edit(Long id) {
        def etapaInstance = Etapa.get(id)
        if (!etapaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'etapa.label', default: 'Etapa'), id])
            redirect(action: "cautare")
            return
        }

		def fieldNameList = getFieldNameListForList()
		def fieldList = getFieldListForList()

		[instance: etapaInstance, fieldNameList: fieldNameList, fieldList: fieldList]
    }

    def update(Long id, Long version) {
		
		int termenLimitaInt
		if (params.termenLimitaImplicit=="")
			termenLimitaInt = 0
		else if (params.termenLimitaImplicit.isNumber())
			termenLimitaInt = Integer.parseInt(params.termenLimitaImplicit)
			else {
				termenLimitaInt = 0
				params.termenLimita = new Date()
				flash.errorMessage = "Termenul limita implcit nu este numar!"
				render(view: "create", model: [instance: new Etapa(params), fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
				return
			}
		
		params.termenLimitaImplicit = termenLimitaInt
		
        def etapaInstance = Etapa.get(id)
        if (!etapaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'etapa.label', default: 'Etapa'), id])
            redirect(action: "cautare")
            return
        }

        if (version != null) {
            if (etapaInstance.version > version) {
                etapaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'etapa.label', default: 'Etapa')] as Object[],
                          "Another user has updated this Etapa while you were editing")
                render(view: "edit", model: [instance: etapaInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
                return
            }
        }

        etapaInstance.properties = params

        if (!etapaInstance.save(flush: true)) {
			print "FAIL ON SAVE " + etapaInstance.properties
            render(view: "edit", model: [instance: etapaInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
            return
        }

        flash.successMessage = message(code: 'default.updated.message', args: [message(code: 'etapa.label', default: 'Etapa'), etapaInstance.id])
        redirect(action: "cautare", id: etapaInstance.id)
    }

    def delete(Long id) {
        def etapaInstance = Etapa.get(id)
        if (!etapaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'etapa.label', default: 'Etapa'), id])
            redirect(action: "cautare")
            return
        }

        try {
            etapaInstance.delete(flush: true)
            flash.successMessage = message(code: 'default.deleted.message', args: [message(code: 'etapa.label', default: 'Etapa'), id])
            redirect(action: "cautare")
        }
        catch (DataIntegrityViolationException e) {
            flash.errorMessage = message(code: 'default.not.deleted.message', args: [message(code: 'etapa.label', default: 'Etapa'), id])
            redirect(action: "show", id: id)
        }
    }

	protected getControllerDomain(){
		return Etapa
	}
}