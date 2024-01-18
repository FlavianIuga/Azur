package contact


class TipClient {

	String tip

	String toString(){
		return tip
	}

	static constraints = { tip(blank: false) }

}
