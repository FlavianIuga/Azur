package produs


import org.springframework.dao.DataIntegrityViolationException

import shared.BaseController

class EvenimentController extends BaseController {

    def index() {
        redirect(action: "cautare", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)

		[instanceList: TipEveniment.list(params), fieldNameListForList: getFieldNameList(), fieldListForList: getFieldList()]
    }
	
	public String[] getFieldNameList() {
		def instancePropertiesNames = []
		instancePropertiesNames.add("Produs")
		instancePropertiesNames.add("Tip Eveniment")
		instancePropertiesNames.add("Data Eveniment")
		instancePropertiesNames.add("Observatie")
		
		return instancePropertiesNames
	}

	public String[] getFieldList() {
		def instancePropertiesList = []
		instancePropertiesList.add("produs")
		instancePropertiesList.add("tipEveniment")
		instancePropertiesList.add("dataEveniment")
		instancePropertiesList.add("observatie")
		
		return instancePropertiesList
	}
	
	public String[] getFieldNameListForShow() {
		def instancePropertiesNames = []
		instancePropertiesNames.add("Tip Eveniment")
		instancePropertiesNames.add("Data Eveniment")
		instancePropertiesNames.add("Observatie")
		
		return instancePropertiesNames
	}

	public String[] getFieldListForShow() {
		def instancePropertiesList = []
		instancePropertiesList.add("tipEveniment")
		instancePropertiesList.add("dataEveniment")
		instancePropertiesList.add("observatie")
		
		return instancePropertiesList
	}
	
	def create() {

		[instance: new TipEveniment(params), fieldNameList: getFieldNameList(), fieldList: getFieldList()]
	 }
	
	def save(Long id) {
		
		def instance
		if (params.edit!=null && params.edit != ""){
			instance = Eveniment.get(params.edit)
			instance.properties = params
		} else
			instance = new Eveniment(params)
			
		instance.produs = Produs.get(params.id)
		
		if (!instance.save(flush: true)) {
			render(controller: "produs", view: "create", model: [instance: instance, fieldNameList: getFieldNameList(), fieldList: getFieldList()])
			return
		}

		flash.successMessage = message(code: 'default.created.message', args: [message(code: 'Eveniment.label', default: 'Eveniment'), instance.id])
		redirect(controller: "produs", action: "show", id: params.id)
	}

	def show(Long id) {
		def instance = TipEveniment.get(id)
		if (!instance) {
			flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tipEveniment.label', default: 'TipEveniment'), id])
			redirect(action: "cautare")
			return
		}
		
		def fieldNameList = getFieldNameList()
		def fieldList = getFieldList()

		[instance: instance, fieldNameList:fieldNameList, fieldList: fieldList]
	}

	def edit(Long id) {
		def instance = TipEveniment.get(id)
		
		print "id: " + id
		
		if (!instance) {
			flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tipEveniment.label', default: 'TipEveniment'), id])
			redirect(action: "cautare")
			return
		}

		def fieldNameList = getFieldNameList()
		def fieldList = getFieldList()

		[instance: instance, fieldNameList: fieldNameList, fieldList: fieldList]
	}

	def update(Long id, Long version) {
		def instance = TipEveniment.get(id)
		if (!instance) {
			flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tipEveniment.label', default: 'TipEveniment'), id])
			redirect(action: "cautare")
			return
		}

		if (version != null) {
			if (instance.version > version) {
				instance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'tipEveniment.label', default: 'TipEveniment')] as Object[],
						  "Another user has updated this TipEveniment while you were editing")
				render(view: "edit", model: [instance: instance, fieldNameList: getFieldNameList(), fieldList: getFieldList()])
				return
			}
		}

		instance.properties = params

		if (!instance.save(flush: true)) {
			render(view: "edit", model: [instance: instance, fieldNameList: getFieldNameList(), fieldList: getFieldList()])
			return
		}

		flash.successMessage = message(code: 'default.updated.message', args: [message(code: 'tipEveniment.label', default: 'TipEveniment'), id])
		redirect(action: "show", id: instance.id)
	}

	def delete(Long id) {
		def instance = TipEveniment.get(id)
		if (!instance) {
			flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tipEveniment.label', default: 'TipEveniment'), id])
			redirect(action: "cautare")
			return
		}

		try {
			instance.delete(flush: true)
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
