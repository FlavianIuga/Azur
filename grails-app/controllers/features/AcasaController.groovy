package features

import actiune.Actiune;
import actiune.ActiuneController;
import grails.plugins.springsecurity.Secured

@Secured('ROLE_USER')
class AcasaController {

	def acasa(){
		
//		ActiuneController a = new ActiuneController()
//		def fieldNameListForList = a.getFieldNameList()
//		def fieldListForList = a.getFieldList()
//
//		def actiuneInstanceListComplet =[]
//		
//		def c = Actiune.createCriteria()
//		def actiuneInstanceLista = c {
//			and {
//				order("idActiune", "desc")
//				order("termenLimita", "asc")
//			}
//		}
//		def idd = ""
//		def actiunea
//		
//		actiuneInstanceLista.each {
//			
//			if (idd != it.idActiune){
//				actiunea = getMaxDate(actiuneInstanceLista, it.idActiune)
//				actiuneInstanceListComplet.add(actiunea)
//				idd = it.idActiune
//			} 
//		}
//
//		try {
//		int i=0
//		while (actiuneInstanceListComplet.get(i)!=null){
//			if (!(actiuneInstanceListComplet.get(i).idActiune).contains(params.searchTerm)){
//				actiuneInstanceListComplet.remove(i)
//			} else
//				i++
//		}
//		}catch (Exception e){
//		
//		}
//		
//		[instanceList: actiuneInstanceListComplet, fieldNameListForList:fieldNameListForList, fieldListForList: fieldListForList]
	}
	
//	private Actiune getMaxDate(List firInstanceList, String idFir){
//		def Date maxDate
//		def idu
//		
//		firInstanceList.each{
//			if (it.idActiune == idFir){
//				if (it.termenPornire > maxDate)
//					maxDate = it.termenPornire
//					idu = it.id
//			}
//		}
//		
//		def c = Actiune.createCriteria()
//		def firr = c {
//			eq("id", idu)
//		}
//		
//		return firr[0]
//	}

	def blabla(){
		
	}
		
}