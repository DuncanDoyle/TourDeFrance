package nl.doyle.mccloud.tourdefrance.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DeelnemerDaoHibernateImpl extends HibernateDaoSupport implements DeelnemerDao {

	private static final Log log = LogFactory.getLog(DeelnemerDaoHibernateImpl.class);
	
	/**
	 * Geeft de deelnemer uit de Deelnemer tabel terug aan de hand van de primary key (deelnemerNummer).
	 * 
	 * @param deelnemerNummer het deelnemernummer van de te laden deelnemer
	 * @return de Deelnemer
	 * 
	 * @see Deelnemer
	 */
	/*
	public Deelnemer loadDeelnemer(final int deelnemerNummer) {
		
		Collection deelnemers = 
			(Collection) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery(
					"from Deelnemer deelnemer where deelnemer.nummer=?");
					query.setInteger(0, deelnemerNummer);
					return query.list();
				}
			});
	*/
		/*	Er kan hoogstens 1 deelnemer zijn, we zoeken immers met de primary key
			Haal deze deelnemer uit de collection en geef 'm terug */
	/*
		Deelnemer returnDeelnemer = null;
		Iterator colIterator = deelnemers.iterator();
		if (colIterator.hasNext()) {
			returnDeelnemer = (Deelnemer) colIterator.next();
		}
		return returnDeelnemer;
	}
	*/
	
	public Deelnemer loadDeelnemer(final int deelnemerNummer) {
		List result = getHibernateTemplate().find("from Deelnemer deelnemer where deelnemer.nummer=?", new Integer(deelnemerNummer));
		//We hebben op primary key gezocht, dus er is maar 1 deelnemer (of geen) in de lijst
		Deelnemer returnDeelnemer = null;
		Iterator listIterator = result.iterator();
		if (listIterator.hasNext()) {
			returnDeelnemer = (Deelnemer) listIterator.next();
		}
		return returnDeelnemer;
	}
	
	/**
	 * Laadt een deelnemer en zijn renners uit de database. Doordat Hibernate gebruik maakt van het lazy-loading
	 * is het noodzakelijk dat alle objecten die benaderd moet worden in 1 Hibernate sessie uit de database
	 * gehaald worden. Deze methode wordt d.m.v. Spring AOP geinjecteerd met een transactie. Een transactie
	 * maakt automatisch gebruik van 1 sessie. Hierdoor wordt het mogelijk om binnen deze methode objecten die 
	 * een relatie hebben met het opgevraagde object ook uit de database te laden (dit wordt gedaan door een
	 * Hibernate.initialize uit te voeren op deze nog niet geladen objecten).
	 *  
	 * @param nummer Het deelnemernummer van de deelnemer
	 * @return de Deelnemer
	 * 
	 * @see Deelnemer
	 */
	public Deelnemer loadDeelnemerEager(int nummer) {
		Deelnemer deelnemer = loadDeelnemer(nummer);
		Hibernate.initialize(deelnemer.getRenners());
		return deelnemer;
	}
	
	/**
	 * Geeft alle deelnemers uit de Deelnemer tabel terug.
	 * 
	 * @return de lijst met deelnemers
	 * 
	 * @see Deelnemer
	 */
	public List<Deelnemer> loadAllDeelnemers() {
		//return getHibernateTemplate().loadAll(Deelnemer.class);
		return getHibernateTemplate().loadAll(Deelnemer.class);
	}
	
	/**
	 * Geeft alle deelnemers uit de Deelnemer tabel terug. Deze methode initialiseert ook 
	 * de renners Set van elke deelnemer
	 * 
	 * @return de lijst met deelnemers
	 * 
	 * @see Deelnemer
	 */
	public List<Deelnemer> loadAllDeelnemersEager() {
		//TODO Performance verbeteren. Misschien kunnen we dit verbeteren door een HQL statement aan te maken waarin we de renners meteen fetchen.
		List<Deelnemer> deelnemers = getHibernateTemplate().loadAll(Deelnemer.class);
		//Itereer door de lijst en initialisser de renners
		for (Deelnemer nextDeelnemer: deelnemers) {
			Hibernate.initialize(nextDeelnemer.getRenners());
		}
		return deelnemers;
	}
	
	/**
	 * Slaat de deelnemer op in de database
	 * 
	 * @param saveDeelnemer
	 */
	public void saveDeelnemer(final Deelnemer saveDeelnemer) {
		getHibernateTemplate().saveOrUpdate(saveDeelnemer);
	}
	
	/**
	 * Update de deelnemer met de nieuwe gegevens. Foreign key relaties met Renners blijven hierdoor ongewijzigd.
	 * 
	 * @param nummer Deelnemernummer
	 * @param voornaam Voornaam van de deelnemer
	 * @param achternaam Achternaam van de deelnemer
	 * @param email E-mail adres van de deelnemer
	 * @param rekeningnummer Rekeningnummer van de deelnemer
	 */
	public void saveDeelnemerDataExcludingForeignKeys(final int nummer, final String voornaam, final String achternaam, final String email, final String rekeningnummer) {
		
		final String hql = "update Deelnemer set voornaam = :newVoornaam, " +
				"achternaam = :newAchternaam, " +
				"email = :newEmail, " +
				"rekeningnummer = :newRekeningnummer " +
				"where nummer = :deelnemerNummer";
		
		int rowCount = 
			((Integer) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery(hql);
					query.setString("newVoornaam", voornaam);
					query.setString("newAchternaam", achternaam);
					query.setString("newRekeningnummer", rekeningnummer);
					query.setString("newEmail", email);
					query.setInteger("deelnemerNummer", nummer);
					return query.executeUpdate();
					
				}
			})).intValue();
		
		log.debug("Rows affected: " + rowCount);
	}
	
	/**
	 * Verwijdert de meegegeven deelnemer uit de database
	 * 
	 * @param delDeelnemer de te verwijderen Deelnemer
	 * 
	 * @return
	 */
	
	public void deleteDeelnemer(final Deelnemer delDeelnemer) {
		getHibernateTemplate().delete(delDeelnemer);
	}

	
	/**
	 * Haalt een deelnemer uit de db op die de meegegeven renner in zijn renners Set heeft.
	 * 
	 * @param renner De renner die in de rennerset van de deelnemer moet zitten
	 * 
	 * @return deelnemer
	 */
	public Deelnemer loadDeelnemerHavingRenner(final Renner renner) {
		final String hql = "select deelnemer from Deelnemer as deelnemer, Renner as renner where renner in elements(deelnemer.renners) and renner.nummer=:rennernummer";
		Deelnemer deelnemer = 
			(Deelnemer) getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery(hql);
					query.setInteger("rennernummer", renner.getNummer());
					return query.uniqueResult();
				}
			});
		return deelnemer;
	}
		
}
