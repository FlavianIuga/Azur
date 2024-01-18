package actiune

import security.User;
import contact.Contact;
import contact.Firma;

class Arhiva {

	int idActiune
	String subiect
	User user
	Firma firma
	Contact contact
	Tema tema
	Etapa etapa
	Date termenPornire
	Date termenLimita
	String observatii
	String documentPath
	
	String toString(){
		return idActiune + " " + firma + " " + termenPornire
	}

	static constraints = {
		idActiune(blank: false)
		user(nullable: true)
		firma(nullable: true)
		contact(nullable: true)
		tema(nullable: true)
		etapa()
		termenPornire()
		termenLimita()
		observatii()
	}

	static mapping = {
		user ignoreNotFound: true
		firma ignoreNotFound: true
		contact ignoreNotFound: true
		tema ignoreNotFound: true
		etapa ignoreNotFound: true
	}
}