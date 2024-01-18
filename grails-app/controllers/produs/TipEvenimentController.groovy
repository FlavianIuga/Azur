package produs

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

import shared.BaseController

@Secured('ROLE_ADMIN')
class TipEvenimentController extends BaseController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "cautare", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)

		[instanceList: TipEveniment.list(params), fieldNameListForList: getFieldNameListForList(), fieldListForList: getFieldListForList()]
	}

	protected String[] getFieldNameListForList() {
		def fieldNameList = []
		fieldNameList.add("Tip Eveniment")
		
		return fieldNameList
	}

	protected String[] getFieldListForList() {
		def fieldsList = []
		fieldsList.add("tipEv")
		
		return fieldsList
	}
	
	def create() {

		[instance: new TipEveniment(params), fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()]
	}

	def save() {
		def tipEvenimentInstance = new TipEveniment(params)
		if (!tipEvenimentInstance.save(flush: true)) {
			render(view: "create", model: [instance: tipEvenimentInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
			return
		}

		flash.successMessage = message(code: 'default.created.message', args: [message(code: 'tipEveniment.label', default: 'TipEveniment'), tipEvenimentInstance.id])
		redirect(action: "cautare", id: tipEvenimentInstance.id)
	}

	def show(Long id) {
		def tipEvenimentInstance = TipEveniment.get(id)
		if (!tipEvenimentInstance) {
			flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tipEveniment.label', default: 'TipEveniment'), id])
			redirect(action: "cautare")
			return
		}
		
		def fieldNameList = getFieldNameListForList()
		def fieldList = getFieldListForList()

		[instance: tipEvenimentInstance, fieldNameList:fieldNameList, fieldList: fieldList]
	}

	def edit(Long id) {
		def tipEvenimentInstance = TipEveniment.get(id)
		if (!tipEvenimentInstance) {
			flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tipEveniment.label', default: 'TipEveniment'), id])
			redirect(action: "cautare")
			return
		}

		def fieldNameList = getFieldNameListForList()
		def fieldList = getFieldListForList()
		
		[instance: tipEvenimentInstance, fieldNameList: fieldNameList, fieldList: fieldList]
	}

	def update(Long id, Long version) {
		def tipEvenimentInstance = TipEveniment.get(id)
		if (!tipEvenimentInstance) {
			flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tipEveniment.label', default: 'TipEveniment'), id])
			redirect(action: "cautare")
			return
		}

		if (version != null) {
			if (tipEvenimentInstance.version > version) {
				tipEvenimentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'tipEveniment.label', default: 'TipEveniment')] as Object[],
						  "Another user has updated this TipEveniment while you were editing")
				render(view: "edit", model: [instance: tipEvenimentInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
				return
			}
		}

		tipEvenimentInstance.properties = params

		if (!tipEvenimentInstance.save(flush: true)) {
			render(view: "edit", model: [instance: tipEvenimentInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
			return
		}

		flash.successMessage = message(code: 'default.updated.message', args: [message(code: 'tipEveniment.label', default: 'TipEveniment'), tipEvenimentInstance.id])
		redirect(action: "cautare", id: tipEvenimentInstance.id)
	}

	def delete(Long id) {
		def tipEvenimentInstance = TipEveniment.get(id)
		if (!tipEvenimentInstance) {
			flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tipEveniment.label', default: 'TipEveniment'), id])
			redirect(action: "cautare")
			return
		}
		
		Eveniment.findAllByTipEveniment(tipEvenimentInstance, [lock: true]).each {
			it.tipEveniment = null
			it.save()
		}

		try {
			tipEvenimentInstance.delete(flush: true)
			flash.successMessage = message(code: 'default.deleted.message', args: [message(code: 'tipEveniment.label', default: 'TipEveniment'), id])
			redirect(action: "cautare")
		}
		catch (DataIntegrityViolationException e) {
			flash.errorMessage = message(code: 'default.not.deleted.message', args: [message(code: 'tipEveniment.label', default: 'TipEveniment'), id])
			redirect(action: "show", id: id)
		}
	}
	
	protected getControllerDomain(){
		return TipEveniment
	}
}