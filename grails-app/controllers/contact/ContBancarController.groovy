package contact

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

import shared.BaseController


@Secured('ROLE_USER')
class ContBancarController extends BaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "cautare", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)

		[instanceList: ContBancar.list(params), firmaInstanceTotal: ContBancar.count(), fieldNameListForList: getFieldNameListForList(), fieldListForList: getFieldListForList()]
    }
	
	protected String[] getFieldNameListForList() {
		def fieldNameList = []
		fieldNameList.add("Cont Bancar")
		fieldNameList.add("Banca")
		
		return fieldNameList
	}

	protected String[] getFieldListForList() {
		def fieldsList = []
		fieldsList.add("contBancar")
		fieldsList.add("banca")
		
		return fieldsList
	}

   def create() {

		[instance: new ContBancar(params), fieldNameList:getFieldNameListForList(), fieldList: getFieldListForList()]
    }

    def save() {
        def contBancarInstance = new ContBancar(params)
        if (!contBancarInstance.save(flush: true)) {
            render(view: "create", model: [instance: contBancarInstance, fieldNameList:getFieldNameListForList(), fieldList: getFieldListForList()])
            return
        }

        flash.successMessage = message(code: 'default.created.message', args: [message(code: 'contBancar.label', default: 'ContBancar'), contBancarInstance.id])
        redirect(action: "show", id: contBancarInstance.id)
    }

    def show(Long id) {
        def contBancarInstance = ContBancar.get(id)
        if (!contBancarInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'contBancar.label', default: 'ContBancar'), id])
            redirect(action: "cautare")
            return
        }
		def fieldNameList = getFieldNameListForList()
		def fieldList = getFieldListForList()

        [instance: contBancarInstance, fieldNameList:fieldNameList, fieldList: fieldList]
    }

    def edit(Long id) {
        def contBancarInstance = ContBancar.get(id)
        if (!contBancarInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'contBancar.label', default: 'ContBancar'), id])
            redirect(action: "cautare")
            return
        }

		def fieldNameList = getFieldNameListForList()
		def fieldList = getFieldListForList()

		[instance: contBancarInstance, fieldNameList: fieldNameList, fieldList: fieldList]
    }

    def update(Long id, Long version) {
        def contBancarInstance = ContBancar.get(id)
        if (!contBancarInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'contBancar.label', default: 'ContBancar'), id])
            redirect(action: "cautare")
            return
        }

        if (version != null) {
            if (contBancarInstance.version > version) {
                contBancarInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'contBancar.label', default: 'ContBancar')] as Object[],
                          "Another user has updated this ContBancar while you were editing")
                render(view: "edit", model: [instance: contBancarInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
                return
            }
        }

        contBancarInstance.properties = params

        if (!contBancarInstance.save(flush: true)) {
            render(view: "edit", model: [instance: contBancarInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
            return
        }

        flash.successMessage = message(code: 'default.updated.message', args: [message(code: 'contBancar.label', default: 'ContBancar'), contBancarInstance.id])
        redirect(action: "show", id: contBancarInstance.id)
    }

    def delete(Long id) {
        def contBancarInstance = ContBancar.get(id)
        if (!contBancarInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'contBancar.label', default: 'ContBancar'), id])
            redirect(action: "cautare")
            return
        }

        try {
            contBancarInstance.delete(flush: true)
            flash.successMessage = message(code: 'default.deleted.message', args: [message(code: 'contBancar.label', default: 'ContBancar'), id])
            redirect(action: "cautare")
        }
        catch (DataIntegrityViolationException e) {
            flash.errorMessage = message(code: 'default.not.deleted.message', args: [message(code: 'contBancar.label', default: 'ContBancar'), id])
            redirect(action: "show", id: id)
        }
    }
	
	protected getControllerDomain(){
		return ContBancar
	}
}