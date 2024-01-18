package produs

import contact.Firma;

class Produs {

	String denumire = null
	String codProdus = null
	String SN = null
	String revizie = null
	String HMI = null
	Date dataProductie = null
	//String idActiune
	String versiune = null

	String nrComandaFurnizor = null
	Date dataComandaFurnizor = null
	String nrFacturaFurnizor = null
	Date dataFacturaFurnizor = null
	Integer garantieFurnizor = 0

	String licentaEndUser = null
	Firma firma = null
	String nrComandaClient = null
	Date dataComandaClient = null
	String nrFacturaClient = null
	Date dataFacturaClient = null
	Integer garantieClient = null
	String nrCertificatGarantie = null
	Date dataCertificatGarantie = null

	String keyType = null
	String CSN = null
	Date dataExpirareGCC = null
	String GEFOrder = null
	String observatii = null

	String toString(){
		return codProdus + ":" + SN
	}

	boolean codificareIdentica(Produs prod){
		if (this.SN.replace(" " ,"").equals(prod.SN) && this.codProdus.replace(" " ,"").equals(prod.codProdus))
			return true
		else
			return false
	}

	static constraints = {
		denumire (nullable: true)
		SN(blank: false, nullable: true)
		codProdus(nullable: true)
		revizie(nullable: true)
		HMI(nullable: true)
		dataProductie(nullable: true)
		versiune(nullable: true)
		nrComandaFurnizor(nullable: true)
		dataComandaFurnizor(nullable: true)
		nrFacturaFurnizor(nullable: true)
		dataFacturaFurnizor(nullable: true)
		garantieFurnizor(nullable: true)
		licentaEndUser(nullable: true)
		firma(nullable: true)
		nrComandaClient(nullable: true)
		dataComandaClient(nullable:true)
		nrFacturaClient(nullable:true)
		garantieClient(nullable: true)
		dataFacturaClient(nullable: true)
		nrCertificatGarantie(nullable: true)
		dataCertificatGarantie(nullable: true)
		keyType(nullable: true)
		CSN(nullable: true)
		dataExpirareGCC(nullable: true)
		GEFOrder(nullable: true)
		observatii(nullable: true)

	}

	static mapping = {
		firma ignoreNotFound: true
		version false
	}
}
