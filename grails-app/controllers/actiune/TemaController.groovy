package actiune

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


import shared.BaseController


@Secured('ROLE_USER')
class TemaController extends BaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "cautare", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)

		[instanceList: Tema.list(params), fieldNameListForList: getFieldNameListForList(), fieldListForList: getFieldListForList()]
    }
	
	protected String[] getFieldNameListForList() {
		def fieldNameList = []
		fieldNameList.add("Nume")
		fieldNameList.add("Descriere")
		fieldNameList.add("Data Creare")
		
		return fieldNameList
	}

	protected String[] getFieldListForList() {
		def fieldsList = []
		fieldsList.add("nume")
		fieldsList.add("descriere")
		fieldsList.add("dataCreare")
		
		return fieldsList
	}

   def create() {

		[instance: new Tema(params), fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()]
    }

    def save() {
        def temaInstance = new Tema(params)
        if (!temaInstance.save(flush: true)) {
            render(view: "create", model: [instance: temaInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
            return
        }

        flash.successMessage = message(code: 'default.created.message', args: [message(code: 'tema.label', default: 'Tema'), temaInstance.id])
        //print "TEMA link : " + "${createLink(controller: 'tema', action: 'show', id: temaInstance.id)}"
		redirect(action: "show", id: temaInstance.id)
    }

	def show(Long id){
		def instance = Tema.get(id)

		def instanceList3 =[]
		
		def c = Actiune.createCriteria()
		def actiuneInstanceLista = c {
			eq("tema", instance)
			order("idActiune", "desc")
		}
		def idd = ""
		def actiunea
		
		actiuneInstanceLista.each {
			
			if (idd != it.idActiune){
				actiunea = getMaxDate(actiuneInstanceLista, it.idActiune)
				instanceList3.add(actiunea)
				idd = it.idActiune
			}
		}

		def instanceList
		if (instanceList3.size()!=0){
			def cec = Actiune.createCriteria()
			instanceList = cec {
				or {
					if (instanceList3.size()>0){
						for (i in 0..instanceList3.size()-1){
							eq("id", instanceList3.get(i).id)
						}
					}
				}
				if (params.sort!=null && params.order !=null){
				
					if (params.sort == 'firma.nume')
						firma {
							order("nume", "${params.order}")
						}
						
					if (params.sort == 'contact.nume')
						contact {
							order("nume", "${params.order}")
						}
					
					if (params.sort == 'etapa.nume')
						etapa {
							order("nume", "${params.order}")
						}
	
					if (params.sort == 'tema.nume')
						tema {
							order("nume", "${params.order}")
						}
						
					if (params.sort == 'user.nume')
						user {
							order("nume", "${params.order}")
						}
						
					if (params.sort != 'firma.nume' && params.sort != 'contact.nume' && params.sort != 'etapa.nume' && params.sort != 'tema.nume' && params.sort != 'user.nume')
						order("${params.sort}", "${params.order}")
				}
			}
		} else {
			instanceList = instanceList3
		}
		
		ActiuneController a = new ActiuneController()
		def fieldNameListForList = a.getFieldNameListForList()
		def fieldListForList = a.getFieldListForList()
		
		def fieldNameList = getFieldNameListForList()
		def fieldList = getFieldListForList()
		
		[instance: instance, fieldNameList: fieldNameList, fieldList: fieldList,
			instanceList: instanceList, fieldNameListForList: fieldNameListForList, fieldListForList: fieldListForList]
	}
	
	private Actiune getMaxDate(List firInstanceList, int idActiune){
		def Date maxDate
		def idu
		
		firInstanceList.each{
			if (it.idActiune == idActiune){
				if (it.termenPornire > maxDate)
					maxDate = it.termenPornire
					idu = it.id
			}
		}
		
		def c = Actiune.createCriteria()
		def firr = c {
			eq("id", idu)
		}
		
		return firr[0]
	}

    def edit(Long id) {
        def temaInstance = Tema.get(id)
        if (!temaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tema.label', default: 'Tema'), id])
            redirect(action: "cautare")
            return
        }

		def fieldNameList = getFieldNameListForList()
		def fieldList = getFieldListForList()
		
		[instance: temaInstance, fieldNameList: fieldNameList, fieldList: fieldList]
    }

    def update(Long id, Long version) {
        def temaInstance = Tema.get(id)
        if (!temaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tema.label', default: 'Tema'), id])
            redirect(action: "cautare")
            return
        }

        if (version != null) {
            if (temaInstance.version > version) {
                temaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tema.label', default: 'Tema')] as Object[],
                          "Another user has updated this Tema while you were editing")
                render(view: "edit", model: [instance: temaInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
                return
            }
        }

        temaInstance.properties = params

        if (!temaInstance.save(flush: true)) {
            render(view: "edit", model: [instance: temaInstance, fieldNameList: getFieldNameListForList(), fieldList: getFieldListForList()])
            return
        }

        flash.successMessage = message(code: 'default.updated.message', args: [message(code: 'tema.label', default: 'Tema'), temaInstance.id])
        redirect(action: "show", id: temaInstance.id)
    }

    def delete(Long id) {
        def temaInstance = Tema.get(id)
        if (!temaInstance) {
            flash.infoMessage = message(code: 'default.not.found.message', args: [message(code: 'tema.label', default: 'Tema'), id])
            redirect(action: "cautare")
            return
        }

		Actiune.findAllByTema(temaInstance, [lock: true]).each {
			it.tema = null
			it.save()
		}

		Arhiva.findAllByTema(temaInstance, [lock: true]).each {
			it.tema = null
			it.save()
		}
		
        try {
            temaInstance.delete(flush: true)
            flash.successMessage = message(code: 'default.deleted.message', args: [message(code: 'tema.label', default: 'Tema'), id])
            redirect(action: "cautare")
        }
        catch (DataIntegrityViolationException e) {
            flash.errorMessage = message(code: 'default.not.deleted.message', args: [message(code: 'tema.label', default: 'Tema'), id])
            redirect(action: "show", id: id)
        }
    }
	
	protected getControllerDomain(){
		return Tema
	}
}