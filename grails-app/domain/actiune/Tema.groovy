package actiune

class Tema {

	String nume
	String descriere
	Date dataCreare

	String toString(){
		return nume
	}

	static constraints = {
		nume(blank: false)
		descriere()
	}

}
