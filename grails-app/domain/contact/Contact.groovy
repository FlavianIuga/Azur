package contact

import actiune.Actiune
import actiune.Arhiva


class Contact {

	String nume = null
	String departament = null
	String functie = null
	String telefonFix = null
	String telefonMobil = null
	String email = null
	boolean enabled = null
	//	boolean disponibil
	Date dataCreare = null
	Firma firma = null
	String observatii = null

	String toString(){
		return nume
	}

	static constraints = {
		nume required:true, blank: false, nullable: true
		departament nullable: true
		functie nullable: true
		telefonFix phoneNumber:true, nullable: true
		telefonMobil phoneNumber:true, nullable: true
		email email:true, nullable: true
		dataCreare nullable: true
		firma nullable: true
		enabled nullable: true
		observatii nullable: true
	}

	static mapping = {
		firma ignoreNotFound: true, cascade: 'none'
		version false
	}

}
