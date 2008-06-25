package nl.doyle.mccloud.tourdefrance.setup;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao;
import nl.doyle.mccloud.tourdefrance.dao.EindUitslagDao;
import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.dao.TeamDao;
import nl.doyle.mccloud.tourdefrance.setup.dao.GameSetupDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;
import nl.doyle.mccloud.tourdefrance.valueobjects.EindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.Team;

public class GameSetupControllerImpl implements GameSetupController {

	/**
	 * The deelnemer DAO.
	 */
	private DeelnemerDao deelnemerDao;

	/**
	 * The renner DAO.
	 */
	private RennerDao rennerDao;

	/**
	 * The standaardEtappe DAO.
	 */
	private StandaardEtappeDao standaardEtappeDao;

	/**
	 * The ploegentijdrit DAO.
	 */
	private PloegenTijdritDao ploegenTijdritDao;

	/**
	 * The team DAO.
	 */
	private TeamDao teamDao;

	/**
	 * The einduitslag DAO.
	 */
	private EindUitslagDao eindUitslagDao;

	/**
	 * The gamesetup DAO.
	 */
	private GameSetupDao gameSetupDao;

	/**
	 * Initialiseert de database aan de hand van het aantal ploegen, aantal etappes en het etappenummer van de ploegentijdrit.
	 * <p>
	 * Het aantal ploegen bepaalt tevens het aantal deelnemers (evenveel) en het aantal renners (9 * aantalPloegen).
	 * <p>
	 * De inhoud van de volledige database wordt gewist. In de database worden alleen de nummers (deelnemers, renners, etc.) aangemaakt.
	 * Waardes kunnen daarna ingevuld worden.
	 * 
	 * @param aantalPloegen
	 *            het aantal ploegen dat meedoet aan de Tour de France
	 * @param aantalEtappes
	 *            het aantal etappes
	 * @param ploegenTijdrit
	 *            het etappenummer van de ploegentijdrit
	 */
	public void initializeGame(final int aantalPloegen, final int aantalEtappes, final int ploegenTijdrit) {
		setupDatabase(aantalPloegen, aantalEtappes, ploegenTijdrit);
	}

	/**
	 * Wijst renners aan deelnemers toe. Dit gebeurt d.m.v. een random generator.
	 * <p>
	 * Elke deelnemer krijgt een kopman. De rest van de renners wordt random toegewezen.
	 */
	public void generateDeelnemerTeams() {
		// TODO: Testen of de gegevens in de database goed zijn zodat de generatie ook echt kan draaien.
		gameSetupDao.deleteAllTeamRecords();
		// Laad alle Deelnemers in een List
		List<Deelnemer> deelnemersKopman = deelnemerDao.loadAllDeelnemers();
		// Doorloop de list en maak de interne objecten voor Rennerverdeling aan
		Iterator<Deelnemer> deelnemerIterator = deelnemersKopman.iterator();
		List<DeelnemerRenners> deelnemersRenners = new LinkedList<DeelnemerRenners>();
		// Voeg de DeelnemerRenners toe aan de nieuwe LinkedList
		List<Deelnemer> storeDeelnemers = new LinkedList<Deelnemer>();
		while (deelnemerIterator.hasNext()) {
			Deelnemer initializeDeelnemer = deelnemerIterator.next();
			/*
			 * Zet de Renners set van een deelnemer op een nieuwe HashSet. Dit heeft 2 doelen: - Als de deelnemer al Renners had toegewezen
			 * gekregen dan zijn deze nu verwijderd. - Er komt geen Lazy-Loading exception van Hibernate als er een RennerSet wordt
			 * opgevraagd omdat de HashSet opnieuw is gezet. (Alle deelnemers worden lazy geladen)
			 */
			initializeDeelnemer.setRenners(new HashSet<Renner>());
			deelnemersRenners.add(new DeelnemerRenners(initializeDeelnemer));
		}
		// Laad alle Renners in een List
		List<Renner> renners = rennerDao.loadAllRenners();

		// Laad nu een Renner uit de List (random)

		while (renners.size() != 0) {
			// Bepaal nu random een renner
			int randomRennerNummer = new Double(Math.floor((Math.random() * renners.size()))).intValue();
			Renner randomRenner = renners.get(randomRennerNummer);
			// Bepaal nu of de renner een kopman is
			if (randomRenner.getNummer() % 10 == 1) {
				// bepaal nu random een deelnemer uit de kopman lijst
				int randomDeelnemerNummer = new Double(Math.floor((Math.random() * deelnemersKopman.size()))).intValue();
				Deelnemer randomDeelnemer = deelnemersKopman.get(randomDeelnemerNummer);
				randomDeelnemer.getRenners().add(randomRenner);
				// Verwijder de deelnemer uit de deelnemerskopman lijst
				deelnemersKopman.remove(randomDeelnemerNummer);
				/*
				 * Sla nu de deelnemer op in de store list Dit is nodig om een referentie naar het Deelnemer object te behouden omdat de
				 * Deelnemers uit beide lijsten worden gehaald als ze en een kopman en alle renners hebben toegewezen gekregen
				 */
				storeDeelnemers.add(randomDeelnemer);
			} else {
				int randomDeelnemerNummer = new Double(Math.floor((Math.random() * deelnemersRenners.size()))).intValue();
				DeelnemerRenners randomDeelnemerRenners = deelnemersRenners.get(randomDeelnemerNummer);
				// Voeg renner toe aan de Deelnemer's rennerlijst
				randomDeelnemerRenners.getDeelnemer().getRenners().add(randomRenner);
				// Haal nu 1 van het aantal Renners van deze deelnemer af
				randomDeelnemerRenners.decreaseAantalRenners();
				// bepaal of deze DeelnemerRenners uit de lijst verwijderd moet worden omdat de deelnemer al 8 renners heeft
				if (randomDeelnemerRenners.getAantalRenners() == 0) {
					deelnemersRenners.remove(randomDeelnemerNummer);
				}
			}
			// Haal nu de randomRenner uit de rennerslijst
			renners.remove(randomRennerNummer);
		}

		// Sla nu alle deelnemers op
		Iterator<Deelnemer> iterate = storeDeelnemers.iterator();
		while (iterate.hasNext()) {
			deelnemerDao.saveDeelnemer(iterate.next());
		}
	}

	/**
	 * Doet de setup van de database. Maakt het juiste aantal deelnemers, teams, etappes, etc. aan met default instellingen (geen namen,
	 * data, etc.). Deze gegevens moeten later ingevuld worden.
	 * 
	 * @param aantalPloegen
	 *            Het aantal ploegen dat meedoet aan de Tour de France. Definieert tevens het aantal deelnemers dat mee kan doen aan het
	 *            spel.
	 * @param aantalEtappes
	 *            Het aantal etappes van de Tour de France.
	 * @param ploegenTijdrit
	 *            Geeft aan welke etappe de ploegentijdrit is. Ploegentijdrit heeft namelijk andere opties dan een normale etappe.
	 */

	private void setupDatabase(final int aantalPloegen, final int aantalEtappes, final int ploegenTijdrit) {
		dropAllData();
		createDeelnemers(aantalPloegen);
		createTeamsAndRenners(aantalPloegen);
		createStandaardEtappes(aantalEtappes, ploegenTijdrit);
		createPloegenTijdrit(ploegenTijdrit);
		createEindUitslag();
	}

	/**
	 * Verwijdert alle data uit de database.
	 */
	private void dropAllData() {
		// GameSetupDao gebruiken om alle data uit de database te verwijderen.
		gameSetupDao.deleteAllRecordsFromDatabase();
	}

	/**
	 * Maakt de deelnemers met default instellingen aan en slaat deze op in de database.
	 * 
	 * @param aantal
	 *            aantal deelnemers aan het spel
	 */
	private void createDeelnemers(final int aantal) {
		Deelnemer storeDeelnemer;
		for (int teller = 0; teller < aantal; teller++) {
			storeDeelnemer = new Deelnemer();
			storeDeelnemer.setNummer(teller + 1001);
			deelnemerDao.saveDeelnemer(storeDeelnemer);
		}
	}

	/**
	 * Maakt de teams en renners aan en slaat deze op in de database.
	 * 
	 * @param numberOfTeams
	 *            het aantal teams
	 * 
	 * @see Team
	 * @see Renner
	 */

	private void createTeamsAndRenners(int numberOfTeams) {
		Team storeTeam;
		//Create the teams one by one.
		for (int teamCounter = 0; teamCounter < numberOfTeams; teamCounter++) {
			storeTeam = new Team();
			storeTeam.setNummer(teamCounter + 1);
			// Create the team's racers.
			HashSet<Renner> racersSet = new HashSet<Renner>();
			for (int racerCounter = 0; racerCounter < 9; racerCounter++) {
				Renner storeRenner = new Renner();
				storeRenner.setNummer((teamCounter * 10) + (racerCounter + 1));
				racersSet.add(storeRenner);
			}
			storeTeam.setRenners(racersSet);
			teamDao.saveTeam(storeTeam);
		}
	}

	/**
	 * Maakt alle etappes aan en slaat ze op in de database.
	 * 
	 * @param aantal
	 *            het aantal etappes
	 * @param ploegenTijdrit
	 *            het etappenummer van de ploegentijdrit
	 */
	private void createStandaardEtappes(int aantal, int ploegenTijdrit) {
		StandaardEtappe storeEtappe;
		for (int teller = 0; teller < aantal; teller++) {
			int etappeNummer = teller + 1;
			if (etappeNummer != ploegenTijdrit) {
				storeEtappe = new StandaardEtappe();
				storeEtappe.setEtappenummer(etappeNummer);
				standaardEtappeDao.saveStandaardEtappe(storeEtappe);
			}
		}
	}

	/**
	 * Maakt de ploegentijdrit aan en slaat deze op in de database.
	 * 
	 * @param ploegenTijdrit
	 *            het etappenummer van de ploegentijdrit
	 */
	private void createPloegenTijdrit(int ploegenTijdrit) {
		if (ploegenTijdrit > 0) {
			PloegenTijdrit storeTijdrit = new PloegenTijdrit();
			storeTijdrit.setEtappenummer(ploegenTijdrit);
			ploegenTijdritDao.savePloegenTijdrit(storeTijdrit);
		} else {
			// TODO Gooi exception als het de ploegentijdrit geen juist getal is. Misschien moeten de parameters wel eerder gechecked
			// worden.
		}
	}

	public void generateTestData() {
		// Bepaal eerst het aantal deelnemers
		List<Deelnemer> deelnemers = deelnemerDao.loadAllDeelnemers();
		for (Deelnemer nextDeelnemer : deelnemers) {
			nextDeelnemer.setAchternaam("de Tester-" + nextDeelnemer.getNummer());
			nextDeelnemer.setVoornaam("Jan-" + nextDeelnemer.getNummer());
			nextDeelnemer.setEmail("Jan.de.Tester@tester.nl");
			nextDeelnemer.setRekeningnummer("12345678");
			deelnemerDao.saveDeelnemer(nextDeelnemer);
		}
		// Bepaal het aantal renners
		List<Renner> renners = rennerDao.loadAllRenners();
		for (Renner nextRenner : renners) {
			nextRenner.setAchternaam("Boogerd-" + nextRenner.getNummer());
			nextRenner.setVoornaam("Michael-" + nextRenner.getNummer());
			rennerDao.saveRenner(nextRenner);
		}

		// Genereer renners
	}

	/**
	 * Maakt de einduitslag aan en slaat deze op in de database.
	 */
	private void createEindUitslag() {
		EindUitslag storeEindUitslag = new EindUitslag();
		storeEindUitslag.setEtappenummer(0);
		storeEindUitslag.setOmschrijving("Eind Uitslag");
		eindUitslagDao.saveEindUitslag(storeEindUitslag);
	}

	public void setDeelnemerDao(DeelnemerDao deelnemerDao) {
		this.deelnemerDao = deelnemerDao;
	}

	public void setPloegenTijdritDao(PloegenTijdritDao ploegenTijdritDao) {
		this.ploegenTijdritDao = ploegenTijdritDao;
	}

	public void setRennerDao(RennerDao rennerDao) {
		this.rennerDao = rennerDao;
	}

	public void setStandaardEtappeDao(StandaardEtappeDao standaardEtappeDao) {
		this.standaardEtappeDao = standaardEtappeDao;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	public void setGameSetupDao(GameSetupDao gameSetupDao) {
		this.gameSetupDao = gameSetupDao;
	}

	/**
	 * @param eindUitslagDao
	 *            the eindUitslagDao to set
	 */
	public void setEindUitslagDao(EindUitslagDao eindUitslagDao) {
		this.eindUitslagDao = eindUitslagDao;
	}

	private class DeelnemerRenners {

		private Deelnemer deelnemer;
		private int aantalRenners;

		public DeelnemerRenners(Deelnemer deelnemer) {
			this.deelnemer = deelnemer;
			aantalRenners = 8;
		}

		public int getAantalRenners() {
			return aantalRenners;
		}

		public void decreaseAantalRenners() {
			aantalRenners = aantalRenners - 1;
		}

		public Deelnemer getDeelnemer() {
			return deelnemer;
		}
	}

}
