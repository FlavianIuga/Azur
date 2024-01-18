package produs


class Eveniment {

	//Produs produs
	TipEveniment tipEveniment
	Date dataEveniment
	String observatie

	static belongsTo = [produs: Produs]
	
	public String toString(){
		if (produs!=null)
			return "" + produs.toString() + tipEveniment.toString()
		else
			return "" + tipEveniment.toString()
	}

	static constraints = {
		tipEveniment (nullable: true)
	}

	static mapping = {
		tipEveniment ignoreNotFound: true
	}
}
