package contact

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

import shared.BaseController
import actiune.Actiune
import actiune.Arhiva


@Secured('ROLE_USER')
class ContactController extends BaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "cautare", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		[instanceList: Contact.list(params), fieldNameListForList:getFieldNameListForList(), fieldListForList: getFieldListForList()]
    }

	protected String[] getFieldNameListForList() {
		def fieldNameList = []
		fieldNameList.add("Nume")
		fieldNameList.add("Firma")
		fieldNameList.add("Departament")
		fieldNameList.add("Functie")
		fieldNameList.add("Telefon Mobil")
		fieldNameList.add("Email")
		fieldNameList.add("Data Creare")
		fieldNameList.add("Activ")
		fieldNameList.add("Observatii")
		
		return fieldNameList
	}

	protected String[] getFieldListForList() {
		def fieldsList = []
		fieldsList.add("nume")
		fieldsList.add("firma")
		fieldsList.add("departament")
		fieldsList.add("functie")
		fieldsList.add("telefonMobil")
		fieldsList.add("email")
		fieldsList.add("dataCreare")
		fieldsList.add("enabled")
		fieldsList.add("observatii")
		
		return fieldsList
	}
	
	protected String[] getFieldNameListForShow() {
		def fieldNameList = []
		fieldNameList.add("Nume")
		fieldNameList.add("Firma")
		fieldNameList.add("Departament")
		fieldNameList.add("Functie")
		fieldNameList.add("Telefon Mobil")
		fieldNameList.add("Telefon Fix")
		fieldNameList.add("Email")
		fieldNameList.add("Data Creare")
		fieldNameList.add("Activ")
		fieldNameList.add("Observatii")
		
		return fieldNameList
	}

	protected String[] getFieldListForShow() {
		def fieldsList = []
		fieldsList.add("nume")
		fieldsList.add("firma")
		fieldsList.add("departament")
		fieldsList.add("functie")
		fieldsList.add("telefonMobil")
		fieldsList.add("telefonFix")
		fieldsList.add("email")
		fieldsList.add("dataCreare")
		fieldsList.add("enabled")
		fieldsList.add("observatii")
		
		return fieldsList
	}
	
	
   def create() {

		[instance: new Contact(params), fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()]
    }

    def save() {
        def contactInstance = new Contact(params)

        if (!contactInstance.save(flush: true)) {
            render(view: "create", model: [instance: contactInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
            return
        }

        flash.successMessage = message(code: 'default.created.message', args: [message(code: 'contact.label', default: 'Contact'), contactInstance.id])
        redirect(action: "show", id: contactInstance.id)
    }

    def show(Long id) {
        def contactInstance = Contact.get(id)
        if (!contactInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'contact.label', default: 'Contact'), id])
            redirect(action: "cautare")
            return
        }
		
        [instance: contactInstance, fieldNameList:getFieldNameListForShow(), fieldList: getFieldListForShow()]
    }

    def edit(Long id) {
        def contactInstance = Contact.get(id)
		
        if (!contactInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'contact.label', default: 'Contact'), id])
            redirect(action: "cautare")
            return
        }

		def fieldNameList = getFieldNameListForList()
		def fieldList = getFieldListForList()

		[instance: contactInstance, fieldNameList: fieldNameList, fieldList: fieldList]
    }

    def update(Long id, Long version) {
        def contactInstance = Contact.get(id)
        if (!contactInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'contact.label', default: 'Contact'), id])
            redirect(action: "cautare")
            return
        }

        if (version != null) {
            if (contactInstance.version > version) {
                contactInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'contact.label', default: 'Contact')] as Object[],
                          "Another user has updated this Contact while you were editing")
                render(view: "edit", model: [instance: contactInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
                return
            }
        }

        contactInstance.properties = params

        if (!contactInstance.save(flush: true)) {
            render(view: "edit", model: [instance: contactInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
            return
        }

        flash.successMessage = message(code: 'default.updated.message', args: [message(code: 'contact.label', default: 'Contact'), contactInstance.id])
        redirect(action: "show", id: contactInstance.id)
    }

    def delete(Long id) {
        def contactInstance = Contact.get(id)
        if (!contactInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'contact.label', default: 'Contact'), id])
            redirect(action: "cautare")
            return
        }

		Actiune.findAllByContact(contactInstance, [lock: true]).each {
			it.contact = null
			it.save()
		}

		Arhiva.findAllByContact(contactInstance, [lock: true]).each {
			it.contact = null
			it.save()
		}
		
        try {
            contactInstance.delete(flush: true)
            flash.successMessage = message(code: 'default.deleted.message', args: [message(code: 'contact.label', default: 'Contact'), id])
            redirect(action: "cautare")
        }
        catch (DataIntegrityViolationException e) {
            flash.errorMessage = message(code: 'default.not.deleted.message', args: [message(code: 'contact.label', default: 'Contact'), id])
            redirect(action: "show", id: id)
        }
    }

	protected getControllerDomain(){
		return Contact
	}
}
