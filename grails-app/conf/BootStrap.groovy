import grails.util.GrailsUtil
import produs.Eveniment
import produs.Produs
import produs.TipEveniment
import security.SecRole
import security.SecUser
import security.SecUserSecRole
import util.Settings
import actiune.Actiune
import actiune.Arhiva
import actiune.Etapa
import actiune.Tema
import contact.ContBancar
import contact.Contact
import contact.Firma
import contact.TipClient


class BootStrap {

	def init = { servletContext ->

		if (!Settings.count()){
			new Settings(cheie: "rootFilesPath", valoare: "C:").save(failOnError:true)
		}

		def userRole =	SecRole.findByAuthority('ROLE_USER') ?: new SecRole(authority:'ROLE_USER').save(failOnError:true)
		def adminRole =	SecRole.findByAuthority('ROLE_ADMIN') ?: new SecRole(authority:'ROLE_ADMIN').save(failOnError:true)

		SecUser userUser
		SecUser userZeus
		if (!SecUser.count()){
			userUser = 		new SecUser(username:'user', password:'user', enabled:true, nume: "User", dataCreare: new Date()).save(failOnError:true)
			userZeus = 		new SecUser(username:'admin', password:'admin', enabled:true, nume: "Admin", dataCreare: new Date()).save(failOnError: true)
		}

		if (!SecUserSecRole.count()){
			if (!userUser.authorities.contains(userRole))
				SecUserSecRole.create userUser, userRole, true

			if (!userZeus.authorities.contains(adminRole)){
				SecUserSecRole.create userZeus, adminRole, true
				SecUserSecRole.create userZeus, userRole, true
			}
		}

		if (!TipClient.count()){
			new TipClient(tip:"Firma").save(failOnError:true)
		}

		if (!TipEveniment.count()){
			new TipEveniment(tipEv:"Buletin de service").save(failOnError:true)
			new TipEveniment(tipEv:"Reparatie").save(failOnError:true)
			new TipEveniment(tipEv:"Inlocuire").save(failOnError:true)
		}


		if (!Etapa.count()){
			new Etapa(id: 1, nume: "Cerere Oferta", termenLimitaImplicit: 5, enabled: true).save(failOnError:true)
			new Etapa(id: 2, nume: "Oferta", termenLimitaImplicit: 5, enabled: true).save(failOnError:true)
			new Etapa(id: 3, nume: "Comanda", termenLimitaImplicit: 5, enabled: true).save(failOnError:true)
			new Etapa(id: 8, nume: "Livrare", termenLimitaImplicit: 5, enabled: true).save(failOnError:true)
			new Etapa(id: 9, nume: "Inchis", termenLimitaImplicit: 5, enabled: true).save(failOnError:true)
		}

		switch (GrailsUtil.environment) {
			case 'development':

//				if (!Firma.count()){
//					new Firma(nume: "Microsoft",			cod: "2ng7", adresa:"strada Microsoft", localitate:"Redmond", judet: "Washington",	tara: "USA", 	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "05 05 1982"), observatii: "client mare").save(failOnError:true)
//					new Firma(nume: "Apple", 				cod: "s8z4", adresa:"strada Apple", localitate: "Cupertino", judet: "California", 	tara: "USA", 	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "01 04 1976"), observatii: "iApple").save(failOnError:true)
//					new Firma(nume: "Google", 				cod: "o9g2", adresa:"strada Google", localitate: "Menlo Park", judet: "California", tara: "USA",  	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "04 09 1998"), observatii: "Gooooooogle").save(failOnError:true)
//					new Firma(nume: "Research In Motion",	cod: "9ito", adresa:"strata RIM", localitate: "Quebec", judet: "Quebec",			tara: "Canada",	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "567292", j: "234534",	dataCreare:new Date().parse("dd mm yyyy", "07 02 2001"), observatii: "Research In Motion (RIM)").save(failOnError:true)
//					
//					new Firma(nume: "Microsoft",			cod: "2ng7", adresa:"strada Microsoft", localitate:"Redmond", judet: "Washington",	tara: "USA", 	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "05 05 1982"), observatii: "client mare").save(failOnError:true)
//					new Firma(nume: "Apple", 				cod: "s8z4", adresa:"strada Apple", localitate: "Cupertino", judet: "California", 	tara: "USA", 	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "01 04 1976"), observatii: "iApple").save(failOnError:true)
//					new Firma(nume: "Google", 				cod: "o9g2", adresa:"strada Google", localitate: "Menlo Park", judet: "California", tara: "USA",  	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "04 09 1998"), observatii: "Gooooooogle").save(failOnError:true)
//					new Firma(nume: "Research In Motion",	cod: "9ito", adresa:"strata RIM", localitate: "Quebec", judet: "Quebec",			tara: "Canada",	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "567292", j: "234534",	dataCreare:new Date().parse("dd mm yyyy", "07 02 2001"), observatii: "Research In Motion (RIM)").save(failOnError:true)
//					
//					new Firma(nume: "Microsoft",			cod: "2ng7", adresa:"strada Microsoft", localitate:"Redmond", judet: "Washington",	tara: "USA", 	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "05 05 1982"), observatii: "client mare").save(failOnError:true)
//					new Firma(nume: "Apple", 				cod: "s8z4", adresa:"strada Apple", localitate: "Cupertino", judet: "California", 	tara: "USA", 	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "01 04 1976"), observatii: "iApple").save(failOnError:true)
//					new Firma(nume: "Google", 				cod: "o9g2", adresa:"strada Google", localitate: "Menlo Park", judet: "California", tara: "USA",  	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "04 09 1998"), observatii: "Gooooooogle").save(failOnError:true)
//					new Firma(nume: "Research In Motion",	cod: "9ito", adresa:"strata RIM", localitate: "Quebec", judet: "Quebec",			tara: "Canada",	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "567292", j: "234534",	dataCreare:new Date().parse("dd mm yyyy", "07 02 2001"), observatii: "Research In Motion (RIM)").save(failOnError:true)
//					
//					new Firma(nume: "Microsoft",			cod: "2ng7", adresa:"strada Microsoft", localitate:"Redmond", judet: "Washington",	tara: "USA", 	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "05 05 1982"), observatii: "client mare").save(failOnError:true)
//					new Firma(nume: "Apple", 				cod: "s8z4", adresa:"strada Apple", localitate: "Cupertino", judet: "California", 	tara: "USA", 	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "01 04 1976"), observatii: "iApple").save(failOnError:true)
//					new Firma(nume: "Google", 				cod: "o9g2", adresa:"strada Google", localitate: "Menlo Park", judet: "California", tara: "USA",  	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "04 09 1998"), observatii: "Gooooooogle").save(failOnError:true)
//					new Firma(nume: "Research In Motion",	cod: "9ito", adresa:"strata RIM", localitate: "Quebec", judet: "Quebec",			tara: "Canada",	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "567292", j: "234534",	dataCreare:new Date().parse("dd mm yyyy", "07 02 2001"), observatii: "Research In Motion (RIM)").save(failOnError:true)
//					
//					new Firma(nume: "Microsoft",			cod: "2ng7", adresa:"strada Microsoft", localitate:"Redmond", judet: "Washington",	tara: "USA", 	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "05 05 1982"), observatii: "client mare").save(failOnError:true)
//					new Firma(nume: "Apple", 				cod: "s8z4", adresa:"strada Apple", localitate: "Cupertino", judet: "California", 	tara: "USA", 	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "01 04 1976"), observatii: "iApple").save(failOnError:true)
//					new Firma(nume: "Google", 				cod: "o9g2", adresa:"strada Google", localitate: "Menlo Park", judet: "California", tara: "USA",  	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "04 09 1998"), observatii: "Gooooooogle").save(failOnError:true)
//					new Firma(nume: "Research In Motion",	cod: "9ito", adresa:"strata RIM", localitate: "Quebec", judet: "Quebec",			tara: "Canada",	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "567292", j: "234534",	dataCreare:new Date().parse("dd mm yyyy", "07 02 2001"), observatii: "Research In Motion (RIM)").save(failOnError:true)
//					
//					new Firma(nume: "Microsoft",			cod: "2ng7", adresa:"strada Microsoft", localitate:"Redmond", judet: "Washington",	tara: "USA", 	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "05 05 1982"), observatii: "client mare").save(failOnError:true)
//					new Firma(nume: "Apple", 				cod: "s8z4", adresa:"strada Apple", localitate: "Cupertino", judet: "California", 	tara: "USA", 	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "01 04 1976"), observatii: "iApple").save(failOnError:true)
//					new Firma(nume: "Google", 				cod: "o9g2", adresa:"strada Google", localitate: "Menlo Park", judet: "California", tara: "USA",  	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "193048", j: "290384",	dataCreare:new Date().parse("dd mm yyyy", "04 09 1998"), observatii: "Gooooooogle").save(failOnError:true)
//					new Firma(nume: "Research In Motion",	cod: "9ito", adresa:"strata RIM", localitate: "Quebec", judet: "Quebec",			tara: "Canada",	industrie:"IT", tipClient: TipClient.findByTip("Firma"), CUI: "567292", j: "234534",	dataCreare:new Date().parse("dd mm yyyy", "07 02 2001"), observatii: "Research In Motion (RIM)").save(failOnError:true)
//				}
//
//				if (!Contact.count()){
//					new Contact(nume:"Georgiana",	departament:"vanzari", functie:"sef", telefonMobil:"+40727584958", telefonFix:"+40255489502", observatii: "detalii multe", 		email:"georgiana@microsoft.com",firma: Firma.findByNume("Microsoft"),	dataCreare:new Date().parse("dd mm yyyy", "01 04 1976"),		enabled: true).save(failOnError:true)
//					new Contact(nume:"Mihai", 		departament:"vanzari", functie:"sef", telefonMobil:"+40737000111", telefonFix:"+40255456782", observatii: "detalii multe",		email:"mihai@microsoft.com",	firma: Firma.findByNume("Microsoft"),	dataCreare:new Date().parse("dd mm yyyy", "01 04 1976"),		enabled: false).save(failOnError:true)
//					new Contact(nume:"Arici",		departament:"vanzari", functie:"sef", telefonMobil:"+40747987656", telefonFix:"+40255446122", observatii: "detalii multe", 		email:"george@apple.com",		firma: Firma.findByNume("Apple"), 		dataCreare:new Date().parse("dd mm yyyy", "01 04 1976"),		enabled: true).save(failOnError:true)
//					new Contact(nume:"John",		departament:"vanzari", functie:"sef", telefonMobil:"+40727849385", telefonFix:"+40255546474", observatii: "detalii multe", 		email:"john@google.com",		firma: Firma.findByNume("Google"),		dataCreare:new Date().parse("dd mm yyyy", "01 04 1976"),		enabled: true).save(failOnError:true)
//					new Contact(nume:"Elizabeth",	departament:"vanzari", functie:"sef", telefonMobil:"+40727999999", telefonFix:"+40255489090", observatii: "detalii multe",		email:"elizabeth@google.com",	firma: Firma.findByNume("Google"),		dataCreare:new Date().parse("dd mm yyyy", "01 04 1976"),		enabled: true).save(failOnError:true)
//					new Contact(nume:"Mary",		departament:"vanzari", functie:"sef", telefonMobil:"+40727967290", telefonFix:"+40255312905", observatii: "sefa de la Quebec",	email:"mary@rim.com",			firma: Firma.findByNume("Research In Motion"), dataCreare:new Date().parse("dd mm yyyy", "01 04 1976"),	enabled: true).save(failOnError:true)
//				}
//
//				if (!ContBancar.count()){
//					new ContBancar(contBancar:"29817943872314", banca:"ING").save(failOnError:true)
//					new ContBancar(contBancar:"84237498237498", banca:"ING").save(failOnError:true)
//					new ContBancar(contBancar:"93840923847575", banca:"BRD").save(failOnError:true)
//					new ContBancar(contBancar:"90812309812093", banca:"Raiffeisen").save(failOnError:true)
//					new ContBancar(contBancar:"85985098121239", banca:"BCR").save(failOnError:true)
//				}
//
//				if (!Tema.count()){
//					new Tema(nume: "Promotie A", descriere: "Promotia lunii octombrie", dataCreare : new Date()).save(failOnError:true)
//					new Tema(nume: "Promotie B", descriere: "Promotia lunii noiembrie", dataCreare : new Date()).save(failOnError:true)
//				}
//
//				if (!Actiune.count()){
//					new Actiune(idActiune: "1", subiect: "Actiunea 1", user: SecUser.findByNume("User"), firma: Firma.findByNume("Google"), contact: Contact.findByNume("John"), tema: Tema.findByNume("Promotie A"), etapa: Etapa.findByNume("Cerere Oferta"),	termenPornire: new Date().parse("dd mm yyyy", "05 10 2012"), termenLimita: new Date().parse("dd mm yyyy", "12 10 2012"), observatii: "Prima actiune deschisa este o cerere de oferta", documentPath: "").save(failOnError:true)
//					new Actiune(idActiune: "1", subiect: "Actiunea 1", user: SecUser.findByNume("User"), firma: Firma.findByNume("Google"), contact: Contact.findByNume("John"), tema: Tema.findByNume("Promotie A"), etapa: Etapa.findByNume("Oferta"),			termenPornire: new Date().parse("dd mm yyyy", "09 10 2012"), termenLimita: new Date().parse("dd mm yyyy", "15 10 2012"), observatii: "Am trimis oferta concreta!", documentPath: "").save(failOnError:true)
//					new Actiune(idActiune: "1", subiect: "Actiunea 1", user: SecUser.findByNume("User"), firma: Firma.findByNume("Google"), contact: Contact.findByNume("John"), tema: Tema.findByNume("Promotie A"), etapa: Etapa.findByNume("Comanda"),		termenPornire: new Date().parse("dd mm yyyy", "12 10 2012"), termenLimita: new Date().parse("dd mm yyyy", "14 10 2012"), observatii: "Clientul a plasat comanda", documentPath: "").save(failOnError:true)
//					new Actiune(idActiune: "1", subiect: "Actiunea 1", user: SecUser.findByNume("User"), firma: Firma.findByNume("Google"), contact: Contact.findByNume("Elizabeth"), tema: Tema.findByNume("Promotie A"), etapa: Etapa.findByNume("Livrare"),	termenPornire: new Date().parse("dd mm yyyy", "14 10 2012"), termenLimita: new Date().parse("dd mm yyyy", "21 10 2012"), observatii: "Comanda ar trebui sa ajunga intr-o saptamana", documentPath: "").save(failOnError:true)
//
//					new Actiune(idActiune: "2", subiect: "Actiunea 2", user: SecUser.findByNume("User"), firma: Firma.findByNume("Microsoft"), contact: Contact.findByNume("Georgiana"), tema: Tema.findByNume("Promotie A"), etapa: Etapa.findByNume("Cerere Oferta"),	termenPornire: new Date().parse("dd mm yyyy", "13 10 2012"), termenLimita: new Date().parse("dd mm yyyy", "21 10 2012"), observatii: "Cerere de la Apple", documentPath: "").save(failOnError:true)
//					new Actiune(idActiune: "2", subiect: "Actiunea 2", user: SecUser.findByNume("User"), firma: Firma.findByNume("Microsoft"), contact: Contact.findByNume("Georgiana"), tema: Tema.findByNume("Promotie A"), etapa: Etapa.findByNume("Oferta"),			termenPornire: new Date().parse("dd mm yyyy", "15 10 2012"), termenLimita: new Date().parse("dd mm yyyy", "25 10 2012"), observatii: "Oferta noastra", documentPath: "").save(failOnError:true)
//					new Actiune(idActiune: "2", subiect: "Actiunea 2", user: SecUser.findByNume("User"), firma: Firma.findByNume("Microsoft"), contact: Contact.findByNume("Mihai"), tema: Tema.findByNume("Promotie A"), etapa: Etapa.findByNume("Livrare"),				termenPornire: new Date().parse("dd mm yyyy", "17 10 2012"), termenLimita: new Date().parse("dd mm yyyy", "26 10 2012"), observatii: "Am trimis factura", documentPath: "").save(failOnError:true)
//				}
//
//
//
//				if (!Arhiva.count()){
//					new Arhiva(idActiune: "3", subiect: "Actiunea 3", user: SecUser.findByNume("Admin"), firma: Firma.findByNume("Google"), contact: Contact.findByNume("John"), tema: Tema.findByNume("Promotie B"), etapa: Etapa.findByNume("Cerere Oferta"),	termenPornire: new Date().parse("dd mm yyyy", "05 10 2012"), termenLimita: new Date().parse("dd mm yyyy", "12 10 2012"), observatii: "Cerere oferta", documentPath: "").save(failOnError:true)
//					new Arhiva(idActiune: "3", subiect: "Actiunea 3", user: SecUser.findByNume("Admin"), firma: Firma.findByNume("Google"), contact: Contact.findByNume("John"), tema: Tema.findByNume("Promotie B"), etapa: Etapa.findByNume("Oferta"),			termenPornire: new Date().parse("dd mm yyyy", "09 10 2012"), termenLimita: new Date().parse("dd mm yyyy", "15 10 2012"), observatii: "Am trimis oferta!", documentPath: "").save(failOnError:true)
//					new Arhiva(idActiune: "3", subiect: "Actiunea 3", user: SecUser.findByNume("Admin"), firma: Firma.findByNume("Google"), contact: Contact.findByNume("John"), tema: Tema.findByNume("Promotie B"), etapa: Etapa.findByNume("Comanda"),		termenPornire: new Date().parse("dd mm yyyy", "12 10 2012"), termenLimita: new Date().parse("dd mm yyyy", "14 10 2012"), observatii: "Clientul a plasat comanda completa", documentPath: "").save(failOnError:true)
//					new Arhiva(idActiune: "3", subiect: "Actiunea 3", user: SecUser.findByNume("Admin"), firma: Firma.findByNume("Google"), contact: Contact.findByNume("Elizabeth"), tema: Tema.findByNume("Promotie B"), etapa: Etapa.findByNume("Livrare"),	termenPornire: new Date().parse("dd mm yyyy", "14 10 2012"), termenLimita: new Date().parse("dd mm yyyy", "21 10 2012"), observatii: "Comanda este pe drum", documentPath: "").save(failOnError:true)
//				}
//
//				if (!Produs.count()){
//					new Produs(codProdus: "898df23a", SN: "9r28rywei", revizie: "0", versiune: "1.5", dataProductie: new Date().parse("dd mm yyyy", "05 05 2010"), idActiune: "", 	nrComandaFurnizor: "67", dataComandaFurnizor: new Date().parse("dd mm yyyy", "05 05 2012"), nrFacturaFurnizor: "23847", dataFacturaFurnizor: new Date().parse("dd mm yyyy", "05 06 2012"),	 garantieFurnizor: 36, firma: Firma.findByNume("Microsoft"), nrComandaClient: "399", dataComandaClient: new Date().parse("dd mm yyyy", "05 07 2012"), nrFacturaClient: "293829", dataFacturaClient: new Date().parse("dd mm yyyy", "05 08 2012"), garantieClient: 24, nrCertificatGarantie: "23948", observatii: "produs bun").save(failOnError:true)
//					new Produs(codProdus: "898df23a", SN: "9r28r7689", revizie: "1", versiune: "2.7", dataProductie: new Date().parse("dd mm yyyy", "05 05 2010"), idActiune: "", 	nrComandaFurnizor: "67", dataComandaFurnizor: new Date().parse("dd mm yyyy", "05 05 2012"), nrFacturaFurnizor: "23848", dataFacturaFurnizor: new Date().parse("dd mm yyyy", "05 06 2012"),	 garantieFurnizor: 36, firma: Firma.findByNume("Google"), nrComandaClient: "422", dataComandaClient: new Date().parse("dd mm yyyy", "05 07 2012"), nrFacturaClient: "293829", dataFacturaClient: new Date().parse("dd mm yyyy", "05 08 2012"), garantieClient: 24, nrCertificatGarantie: "41546", observatii: "produs foarte bun").save(failOnError:true)
//					new Produs(codProdus: "898df23a", SN: "96425782t", revizie: "3", versiune: "2.9.5", dataProductie: new Date().parse("dd mm yyyy", "05 05 2010"), idActiune: "", nrComandaFurnizor: "67", dataComandaFurnizor: new Date().parse("dd mm yyyy", "05 05 2012"), nrFacturaFurnizor: "23849", dataFacturaFurnizor: new Date().parse("dd mm yyyy", "05 06 2012"),	 garantieFurnizor: 36, firma: Firma.findByNume("Apple"), nrComandaClient: "645", dataComandaClient: new Date().parse("dd mm yyyy", "05 07 2012"), nrFacturaClient: "293829", dataFacturaClient: new Date().parse("dd mm yyyy", "05 08 2012"), garantieClient: 24, nrCertificatGarantie: "73540", observatii: "produs foarte foarte bun").save(failOnError:true)
//				}
//
//				if (!Eveniment.count()){
//					new Eveniment(produs: Produs.findWhere(codProdus: "898df23a",SN: "9r28rywei"), tipEveniment: TipEveniment.findByTipEv("Buletin de service"), dataEveniment: new Date().parse("dd mm yyyy", "06 07 2011"), observatie: "A aparut un buletin de service").save(failOnError:true)
//					new Eveniment(produs: Produs.findWhere(codProdus: "898df23a",SN: "9r28rywei"), tipEveniment: TipEveniment.findByTipEv("Reparatie"), dataEveniment: new Date().parse("dd mm yyyy", "09 08 2011"), observatie: "Am retras produsul").save(failOnError:true)
//					new Eveniment(produs: Produs.findWhere(codProdus: "898df23a",SN: "9r28rywei"), tipEveniment: TipEveniment.findByTipEv("Buletin de service"), dataEveniment: new Date().parse("dd mm yyyy", "10 10 2011"), observatie: "A aparut alt buletin de service").save(failOnError:true)
//
//					new Eveniment(produs: Produs.findWhere(codProdus: "898df23a",SN: "96425782t"), tipEveniment: TipEveniment.findByTipEv("Buletin de service"), dataEveniment: new Date().parse("dd mm yyyy", "06 07 2011"), observatie: "A aparut un buletin de service").save(failOnError:true)
//					new Eveniment(produs: Produs.findWhere(codProdus: "898df23a",SN: "96425782t"), tipEveniment: TipEveniment.findByTipEv("Reparatie"), dataEveniment: new Date().parse("dd mm yyyy", "09 08 2011"), observatie: "Am retras produsul").save(failOnError:true)
//				}



				break
		}
	}

	def destroy = {
	}
}
