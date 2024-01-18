package contact

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

import shared.BaseController


@Secured('ROLE_ADMIN')
class TipClientController extends BaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "cautare", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)

		[instanceList: TipClient.list(params), fieldNameListForList: getFieldNameListForList(), fieldListForList: getFieldListForList()]
    }

	protected String[] getFieldNameListForList() {
		def fieldNameList = []
		fieldNameList.add("Tip")
		
		return fieldNameList
	}

	protected String[] getFieldListForList() {
		def fieldsList = []
		fieldsList.add("tip")
		
		return fieldsList
	}
	
    def create() {

		[instance: new TipClient(params), fieldNameList:getFieldNameListForList(), fieldList: getFieldListForList()]
    }

    def save() {
        def tipClientInstance = new TipClient(params)
        if (!tipClientInstance.save(flush: true)) {
            render(view: "create", model: [instance: tipClientInstance, fieldNameList:getFieldNameListForList(), fieldList: getFieldListForList()])
            return
        }

        flash.successMessage = message(code: 'default.created.message', args: [message(code: 'tipClient.label', default: 'TipClient'), tipClientInstance.id])
        redirect(action: "cautare", id: tipClientInstance.id)
    }

    def show(Long id) {
        def tipClientInstance = TipClient.get(id)
        if (!tipClientInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tipClient.label', default: 'TipClient'), id])
            redirect(action: "cautare")
            return
        }
		
		def fieldNameList = getFieldNameListForList()
		def fieldList = getFieldListForList()

        [instance: tipClientInstance, fieldNameList:fieldNameList, fieldList: fieldList] 
    }

    def edit(Long id) {
        def tipClientInstance = TipClient.get(id)
        if (!tipClientInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tipClient.label', default: 'TipClient'), id])
            redirect(action: "cautare")
            return
        }

		def fieldNameList = getFieldNameListForList()
		def fieldList = getFieldListForList()
		
		[instance: tipClientInstance, fieldNameList: fieldNameList, fieldList: fieldList]
    }

    def update(Long id, Long version) {
        def tipClientInstance = TipClient.get(id)
        if (!tipClientInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tipClient.label', default: 'TipClient'), id])
            redirect(action: "cautare")
            return
        }

        if (version != null) {
            if (tipClientInstance.version > version) {
                tipClientInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tipClient.label', default: 'TipClient')] as Object[],
                          "Another user has updated this TipClient while you were editing")
                render(view: "edit", model: [instance: tipClientInstance, fieldNameList:getFieldNameListForList(), fieldList: getFieldListForList()])
                return
            }
        }

        tipClientInstance.properties = params

        if (!tipClientInstance.save(flush: true)) {
            render(view: "edit", model: [instance: tipClientInstance, fieldNameList:getFieldNameListForList(), fieldList: getFieldListForList()])
            return
        }

        flash.successMessage = message(code: 'default.updated.message', args: [message(code: 'tipClient.label', default: 'TipClient'), tipClientInstance.id])
        redirect(action: "cautare", id: tipClientInstance.id)
    }

    def delete(Long id) {
        def tipClientInstance = TipClient.get(id)
        if (!tipClientInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tipClient.label', default: 'TipClient'), id])
            redirect(action: "cautare")
            return
        }

		Firma.findAllByTipClient(tipClientInstance, [lock: true]).each {
			it.tipClient = null
			it.save()
		}
		
        try {
            tipClientInstance.delete(flush: true)
            flash.successMessage = message(code: 'default.deleted.message', args: [message(code: 'tipClient.label', default: 'TipClient'), id])
            redirect(action: "cautare")
        }
        catch (DataIntegrityViolationException e) {
            flash.errorMessage = message(code: 'default.not.deleted.message', args: [message(code: 'tipClient.label', default: 'TipClient'), id])
            redirect(action: "show", id: id)
        }
    }
	
	protected getControllerDomain(){
		return TipClient
	}
}