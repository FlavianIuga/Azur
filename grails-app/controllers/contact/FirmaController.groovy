package contact

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

import produs.Produs
import shared.BaseController
import actiune.Actiune
import actiune.Arhiva


@Secured('ROLE_USER')
class FirmaController extends BaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "cautare", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        [instanceList: Firma.list(params), fieldNameListForList: getFieldNameListForList(), fieldListForList: getFieldListForList()]
    }

    def create() {
		
		[instance: new Firma(params),tipClientList: TipClient.list(), fieldNameList: getFieldNameListForForm(), fieldList: getFieldListForForm()]
    }

    def save() {
        def firmaInstance = new Firma(params)

		firmaInstance.cod = firmaInstance.cod.replace(" " ,"")
		
		boolean unique = true
		Firma.list().each {
			if (it.cod.replace(" " ,"").equals(firmaInstance.cod) && it.id != firmaInstance.id)
				unique = false
		}
		
		if (!unique){
			flash.errorMessage = "Codificarea nu este unica."
			render(view: "edit", model: [instance: firmaInstance, fieldNameList: getFieldNameListForForm(), fieldList: getFieldListForForm()])
			return
		} else {
			if (!firmaInstance.save(flush: true)) {
	            render(view: "create", model: [instance: firmaInstance, fieldNameList: getFieldNameListForForm(), fieldList: getFieldListForForm()])
	            return
	        }
		}

        flash.successMessage = message(code: 'default.created.message', args: [message(code: 'firma.label', default: 'Firma'), firmaInstance.id])
        redirect(action: "show", id: firmaInstance.id)
    }

	protected String[] getFieldNameListForForm() {
		def fieldNameList = []
		fieldNameList.add("Nume")
		fieldNameList.add("Cod")
		fieldNameList.add("Tip Client")
		fieldNameList.add("Adresa")
		fieldNameList.add("Localitate")
		fieldNameList.add("Judet")
		fieldNameList.add("Cod Postal")
		fieldNameList.add("Tara")
		fieldNameList.add("Telefon Fix")
		fieldNameList.add("Fax")
		fieldNameList.add("Industrie")
		fieldNameList.add("CUI")
		fieldNameList.add("J")
		fieldNameList.add("Data Creare")
		fieldNameList.add("Observatii")
		
		return fieldNameList
	}

	protected String[] getFieldListForForm() {
		def fieldsList = []
		fieldsList.add("nume")
		fieldsList.add("cod")
		fieldsList.add("tipClient")
		fieldsList.add("adresa")
		fieldsList.add("localitate")
		fieldsList.add("judet")
		fieldsList.add("codPostal")
		fieldsList.add("tara")
		fieldsList.add("telefonFix")
		fieldsList.add("fax")
		fieldsList.add("industrie")
		fieldsList.add("CUI")
		fieldsList.add("j")
		fieldsList.add("dataCreare")
		fieldsList.add("observatii")
		
		return fieldsList
	}
	
	protected String[] getFieldNameListForList() {
		def fieldNameList = []
		fieldNameList.add("Nume")
		fieldNameList.add("Cod")
		fieldNameList.add("Tip Client")
		fieldNameList.add("Localitate")
		fieldNameList.add("Tara")
		fieldNameList.add("Telefon Fix")
		fieldNameList.add("Industrie")
		fieldNameList.add("Data Creare")
		fieldNameList.add("Observatii")
		
		return fieldNameList
	}

	protected String[] getFieldListForList() {
		def fieldsList = []
		fieldsList.add("nume")
		fieldsList.add("cod")
		fieldsList.add("tipClient")
		fieldsList.add("localitate")
		fieldsList.add("tara")
		fieldsList.add("telefonFix")
		fieldsList.add("industrie")
		fieldsList.add("dataCreare")
		fieldsList.add("observatii")
		
		return fieldsList
	}
	
    def show(Long id) {
        def firmaInstance = Firma.get(id)
        if (!firmaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'firma.label', default: 'Firma'), id])
            redirect(action: "cautare")
            return
        }

		def fieldNameList = getFieldNameListForForm()
		def fieldList = getFieldListForForm()
		
        [instance: firmaInstance, contactInstanceList: Contact.findAllByFirma(firmaInstance), inactiveContactsList: Contact.findAllByFirmaAndEnabled(firmaInstance,false), fieldNameList: fieldNameList, fieldList: fieldList]
    }

    def edit(Long id) {
        def firmaInstance = Firma.get(id)
        if (!firmaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'firma.label', default: 'Firma'), id])
            redirect(action: "cautare")
            return
        }

        def fieldNameList = getFieldNameListForForm()
		def fieldList = getFieldListForForm()
		
		[instance: firmaInstance, fieldNameList: fieldNameList, fieldList: fieldList]
    }

    def update(Long id, Long version) {
        def firmaInstance = Firma.get(id)

		if (!firmaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'firma.label', default: 'Firma'), id])
            redirect(action: "cautare")
            return
        }

        if (version != null) {
            if (firmaInstance.version > version) {
                firmaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'firma.label', default: 'Firma')] as Object[],
                          "Another user has updated this Firma while you were editing")
                render(view: "edit", model: [instance: firmaInstance, , fieldNameList: getFieldNameListForForm(), fieldList: getFieldListForForm()])
                return
            }
        }

        firmaInstance.properties = params

        if (!firmaInstance.save(flush: true)) {
            render(view: "edit", model: [instance: firmaInstance, , fieldNameList: getFieldNameListForForm(), fieldList: getFieldListForForm()])
            return
        }

        flash.successMessage = message(code: 'default.updated.message', args: [message(code: 'firma.label', default: 'Firma'), firmaInstance.id])
        redirect(action: "show", id: firmaInstance.id)
    }

    def delete(Long id) {
        def firmaInstance = Firma.get(id)
        if (!firmaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'firma.label', default: 'Firma'), id])
            redirect(action: "cautare")
            return
        }

		Contact.findAllByFirma(firmaInstance, [lock: true]).each {
			it.firma = null
			it.save()
		}

		Produs.findAllByFirma(firmaInstance, [lock: true]).each {
			it.firma = null
			it.save()
		}

		Actiune.findAllByFirma(firmaInstance, [lock: true]).each {
			it.firma = null
			it.save()
		}

		Arhiva.findAllByFirma(firmaInstance, [lock: true]).each {
			it.firma = null
			it.save()
		}
		
		try {
            firmaInstance.delete(flush: true)
            flash.successMessage = message(code: 'default.deleted.message', args: [message(code: 'firma.label', default: 'Firma'), id])
            redirect(action: "cautare")
        }
        catch (DataIntegrityViolationException e) {
            flash.errorMessage = message(code: 'default.not.deleted.message', args: [message(code: 'firma.label', default: 'Firma'), id])
            redirect(action: "show", id: id)
        }
    }
	
	protected getControllerDomain(){
		return Firma
	}
}