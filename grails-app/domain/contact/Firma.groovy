package contact

import produs.Produs
import actiune.Actiune
import actiune.Arhiva

class Firma {

	String nume = null
	String cod = null
	String adresa = null
	String localitate = null
	String judet = null
	String codPostal = null
	String tara = null
	String telefonFix = null
	String fax = null
	TipClient tipClient = null
	String industrie = null
	String CUI = null
	String j = null
	//	boolean platitorTVA
	//	boolean bugetar
	String observatii = null
	Date dataCreare = null
	static hasMany= [contBancar:ContBancar]

	String toString(){
		return nume
	}

	static constraints = {
		nume(required: true, blank: false, nullable: true)
		cod(nullable: true)
		adresa(nullable: true)
		localitate(nullable: true)
		judet(nullable: true)
		codPostal(nullable: true)
		tara(nullable: true)
		telefonFix(nullable: true)
		fax(nullable: true)
		tipClient(nullable: true)
		industrie(nullable: true)
		CUI(nullable: true)
		j(nullable: true)
		//		platitorTVA()
		//		bugetar()
		observatii(nullable: true)
		dataCreare(nullable: true)
	}

	static mapping = {
		tipClient ignoreNotFound: true
		version false
	}

}
