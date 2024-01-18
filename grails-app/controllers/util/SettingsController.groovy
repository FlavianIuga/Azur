package util

import grails.plugins.springsecurity.Secured

@Secured('ROLE_ADMIN')
class SettingsController {

    def index() {
		
		Settings rootFilesPath = Settings.findWhere(cheie: 'rootFilesPath') 
		
		[rootFilesPath: rootFilesPath]
	}
	
	def edit(){
		def rootFilesPath = Settings.findWhere(cheie: 'rootFilesPath')
		
		[rootFilesPath: rootFilesPath]
	}
	
	def save(){
		Settings path = Settings.findWhere(cheie: 'rootFilesPath')
		path.valoare = params.valoare
		
		if (!path.save(flush: true)) {
			print "FAIL TO SAVE USER" + path
			render(view: "create", model: [instance: path])
			return
		}
		
		flash.successMessage = message(code: 'default.created.message', args: [message(code: 'settings.label', default: 'Settings'), path.id])
		redirect(action: "index")
	}
}
