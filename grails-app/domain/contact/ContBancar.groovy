package contact

class ContBancar {

	String contBancar
	String banca
	
	String toString(){
		return contBancar
	}
	
    static constraints = {
		contBancar(blank: false)
		banca()
    }
}
