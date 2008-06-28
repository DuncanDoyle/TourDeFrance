package nl.doyle.mccloud.tourdefrance.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;

import org.hibernate.SessionFactory;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

//TODO This class uses its own test-setup for the DB. We need to create a default test db (using DB-Unit for example) and use Spring's AbstractTransactionalDatasourceContextTests class to rollback transactions.
public class TestRennerDaoImpl extends AbstractDependencyInjectionSpringContextTests {

	DeelnemerDao deelnemerDao;
	RennerDao rennerDao;
	SessionFactory sessionFactory;

	{
		setAutowireMode(AUTOWIRE_NO);
	}

	protected void onSetUp() throws Exception {
		deelnemerDao = (DeelnemerDao) applicationContext.getBean("deelnemerDao");
		rennerDao = (RennerDao) applicationContext.getBean("rennerDao");
		insertTestData();
	}

	protected String[] getConfigLocations() {
		return new String[] { "classpath:spring/business_applicationcontext.xml", "classpath:spring/testBusiness_applicationcontext.xml" };
	}

	public void testLoadAllRenners() {
		List<Renner> renners = rennerDao.loadAllRenners();
		Iterator<Renner> iterate = renners.iterator();
		while (iterate.hasNext()) {
			Renner rnr = (Renner) iterate.next();
			System.out.println(rnr.getNummer());
			System.out.println(rnr.getVoornaam());
			System.out.println(rnr.getAchternaam());
		}
	}

	public void testLoadRenner() {
		// Load renner
		Renner loadRenner = rennerDao.loadRenner(1);
		if (loadRenner == null) {
			// Renner zit nog niet in de databae
			Renner saveRenner = new Renner();
			saveRenner.setNummer(1);
			saveRenner.setAchternaam("Armstrong");
			saveRenner.setVoornaam("Lance");
			rennerDao.saveRenner(saveRenner);
			loadRenner = rennerDao.loadRenner(1);
		}
		assertEquals(loadRenner.getNummer(), 1);
	}

	private List<Deelnemer> testLoadAllDeelnemers() {
		List<Deelnemer> deelnemers = deelnemerDao.loadAllDeelnemers();
		Iterator<Deelnemer> iterate = deelnemers.iterator();
		while (iterate.hasNext()) {
			Deelnemer dlnmr = (Deelnemer) iterate.next();
			System.out.println(dlnmr.getNummer());
			System.out.println(dlnmr.getVoornaam());
			System.out.println(dlnmr.getAchternaam());
		}
		return deelnemers;
	}

	public void testDeleteAllDeelnemers() {
		// Verwijder nu alle deelnemer data en alle Deelnemer_Renner data
		// rennerDao.deleteAllRenners();
	}

	private void insertTestData() {
		// Haal data op uit Deelnemer tabel. Nodig om ervoor te zorgen dat we geen data toevoegen die al aanwezig is
		List<Deelnemer> deelnemers = testLoadAllDeelnemers();
		// We testen met 21 deelnemers, dus loopje van 0 tot 20
		int counter;
		int deelnemerNummer;
		for (counter = 0; counter < 21; counter++) {
			deelnemerNummer = 1001 + counter;
			Iterator<Deelnemer> iterate = deelnemers.iterator();
			boolean save = true;
			while (iterate.hasNext()) {
				// Als de deelnemer nog niet bestaat moet ie aangemaakt worden
				if (deelnemerNummer == ((Deelnemer) iterate.next()).getNummer()) {
					save = false;
				}
			}
			if (save) {
				Deelnemer saveDeelnemer = new Deelnemer();
				saveDeelnemer.setNummer(deelnemerNummer);
				saveDeelnemer.setAchternaam("Jan");
				saveDeelnemer.setVoornaam("de Tester");
				saveDeelnemer.setRekeningnummer(String.valueOf((Math.floor((Math.random() * 10000000)))));
				saveDeelnemer.setEmail("doyle@xs4all.nl");
				// Voeg renners toe, gebruik Random rennernummers
				HashSet<Renner> renners = new HashSet<Renner>();
				for (int counter2 = 0; counter2 < 3; counter2++) {
					Renner renner = new Renner();
					renner.setAchternaam("Armstrong");
					int rennernummer = (int) (Math.floor((Math.random() * 201))) + 1;
					// probeer renner te laden. Als de renner al bestaat, sla de renner dan over
					// code is niet optimaal (checked niet of een renner al aan een deelnemer gekoppeld is)
					// maar het is maar een test.
					if (rennerDao.loadRenner(rennernummer) == null) {
						renner.setNummer(rennernummer);
						renner.setVoornaam("Lance");
						renners.add(renner);
					}
				}
				saveDeelnemer.setRenners(renners);
				deelnemerDao.saveDeelnemer(saveDeelnemer);
				System.out.println("Deelnemer in DB opgeslagen");
			}
		}

	}

}
