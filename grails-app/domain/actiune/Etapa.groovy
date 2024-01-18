package actiune

import java.util.Date;

class Etapa {

	int id
	String nume
	boolean enabled
	/**
	 * Termen limita implicit pentru etapa, dar in numar de zile
	 */
	int termenLimitaImplicit

	String toString(){
		return nume
	}

	static constraints = {
		id()
		nume(blank: false, unique: true)
		termenLimitaImplicit()
	}
}
