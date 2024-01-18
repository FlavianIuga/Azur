package util

class Settings {

	String cheie
	String valoare
	
	String toString(){
		return cheie + " " + valoare
	}
	
    static constraints = {
		cheie unique: true
    }
}
