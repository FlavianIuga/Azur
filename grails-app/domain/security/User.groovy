package security

import actiune.Actiune
import actiune.Arhiva


class User {

	String nume
	Date dataCreare

	String toString(){
		return nume
	}

	static constraints = {
		nume(blank: false)
		dataCreare()
	}

	def beforeDelete() {

		Actiune.findAllByUser(this, [lock: true]).each {
			it.user = null
			it.save()
		}

		Arhiva.findAllByUser(this, [lock: true]).each {
			it.user = null
			it.save()
		}
	}
}
