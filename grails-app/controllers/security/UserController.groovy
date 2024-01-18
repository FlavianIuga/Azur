package security

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

import shared.BaseController
import actiune.Actiune
import actiune.Arhiva

@Secured('ROLE_ADMIN')
class UserController extends BaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "cautare", params: params)
    }
	
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
				
		[instanceList: SecUser.list(params), fieldNameListForList: getFieldNameListForList(), fieldListForList: getFieldListForList()]
    }
	
	protected String[] getFieldNameListForForm() {
		def fieldNameList = []
		fieldNameList.add("Nume")
		fieldNameList.add("Data Creare")
		fieldNameList.add("Username")
		fieldNameList.add("Password")
		fieldNameList.add("Activ")
		fieldNameList.add("Admin")

		return fieldNameList
	}

	protected String[] getFieldListForForm() {
		def fieldsList = []
		fieldsList.add("nume")
		fieldsList.add("dataCreare")
		fieldsList.add("username")
		fieldsList.add("password")
		fieldsList.add("enabled")
		fieldsList.add("admin")
		
		return fieldsList
	}
	
	protected String[] getFieldNameListForList() {
		def fieldNameList = []
		fieldNameList.add("Nume")
		fieldNameList.add("Data Creare")
		fieldNameList.add("Username")
		fieldNameList.add("Activ")
		fieldNameList.add("Admin")

		return fieldNameList
	}

	protected String[] getFieldListForList() {
		def fieldsList = []
		fieldsList.add("nume")
		fieldsList.add("dataCreare")
		fieldsList.add("username")
		fieldsList.add("enabled")
		fieldsList.add("admin")
		
		return fieldsList
	}

	def create() {
		def adminFlag = false
		
		[instance: new SecUser(params), fieldNameList: getFieldNameListForForm(), fieldList: getFieldListForForm(), adminFlag: adminFlag]
	}

    def save() {
		def adminFlag = false
		def userInstance = new SecUser(params)
		userInstance.dataCreare = new Date()
		
		if (params.enabled==null)
			userInstance.enabled = false
		
		if (params.enabled=='on')
			userInstance.enabled = true
		
		def userRole = SecRole.findByAuthority('ROLE_USER')
	
        if (!userInstance.save(flush: true)) {
			print "FAIL TO SAVE USER " + userInstance
            render(view: "create", model: [instance: new SecUser(params), fieldNameList: getFieldNameListForForm(), fieldList: getFieldListForForm(), adminFlag: adminFlag])
            return
        }

		SecUserSecRole.create userInstance, userRole, true
		
		if (params.admin){
			print "admin"
			def adminRole = SecRole.findByAuthority('ROLE_ADMIN')
			SecUserSecRole.create userInstance, adminRole, true
		}
		
        flash.successMessage = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "cautare", id: userInstance.id)
    }

    def show(Long id) {
        def userInstance = SecUser.get(id)
        if (!userInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "cautare")
            return
        }
		
		def fieldNameList = getFieldNameListForForm()
		def fieldList = getFieldListForForm()

		def adminFlag = false
		if (SecUser.get(id).authorities.any { it.authority == "ROLE_ADMIN" })
			adminFlag = true
		
        [instance: userInstance, fieldNameList:fieldNameList, fieldList: fieldList, adminFlag: adminFlag]
    }

    def edit(Long id) {
        def userInstance = SecUser.get(id)
        if (!userInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "cautare")
            return
        }

		def adminFlag = false
		if (SecUser.get(id).authorities.any { it.authority == "ROLE_ADMIN" })
			adminFlag = true
		
		[instance: userInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList(), adminFlag: adminFlag]
    }

    def update(Long id, Long version) {
        def userInstance = SecUser.get(id)
        if (!userInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "cautare")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [instance: userInstance, fieldNameList: getFieldNameListForForm(), fieldList: getFieldListForForm(), adminFlag: adminFlag])
                return
            }
        }

        userInstance.properties = params

		if (params.admin){
			def adminRole = SecRole.findByAuthority('ROLE_ADMIN')
			def isAdmin = false
			userInstance.authorities.each  {
				if (it.authority.equals(adminRole.authority))
					isAdmin = true
			}
			if (!isAdmin)
				SecUserSecRole.create userInstance, adminRole, true
		} else {
			SecUserSecRole.remove(userInstance, SecRole.findByAuthority('ROLE_ADMIN'))
		}
		
        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [instance: userInstance, fieldNameList: getFieldNameListForForm(), fieldList: getFieldListForForm(), adminFlag: adminFlag])
            return
        }

        flash.successMessage = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def delete(Long id) {
        def userInstance = SecUser.get(id)
        if (!userInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "cautare")
            return
        }
		
		Actiune.findAllByUser(userInstance, [lock: true]).each {
			it.user = null
			it.save()
		}

		Arhiva.findAllByUser(userInstance, [lock: true]).each {
			it.user = null
			it.save()
		}

        try {
            userInstance.delete(flush: true)
            flash.successMessage = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "cautare")
        }
        catch (DataIntegrityViolationException e) {
            flash.errorMessage = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id)
        }
    }
	
	protected getControllerDomain(){
		return User
	}
}