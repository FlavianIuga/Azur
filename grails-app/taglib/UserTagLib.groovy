
class UserTagLib {

	def springSecurityService

	def loggedInUser={
		out <<"${springSecurityService.currentUser.nume}"
	}
}
