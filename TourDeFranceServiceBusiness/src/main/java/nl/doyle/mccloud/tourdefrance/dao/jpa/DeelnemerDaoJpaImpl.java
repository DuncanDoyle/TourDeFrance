package nl.doyle.mccloud.tourdefrance.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao;
import nl.doyle.mccloud.tourdefrance.util.JpaUtil;
import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the {@link DeelnemerDao} interface.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class DeelnemerDaoJpaImpl extends JpaDaoSupport implements DeelnemerDao {

	/**
	 * Commons-Logging logger.
	 */
	private static final Log LOG = LogFactory.getLog(DeelnemerDaoJpaImpl.class);

	/**
	 * The name of the {@link Deelnemer} class as a {@link String}.
	 */
	private static final String DEELNEMER_CLASS_NAME = Deelnemer.class.getName();
	
	/**
	 * Geeft de deelnemer uit de Deelnemer tabel terug aan de hand van de primary key (deelnemerNummer).
	 * 
	 * @param deelnemerNummer
	 *            het deelnemernummer van de te laden deelnemer
	 * @return de Deelnemer
	 * 
	 * @see Deelnemer
	 */
	public Deelnemer loadDeelnemer(final int deelnemerNummer) {
		return getJpaTemplate().find(Deelnemer.class, deelnemerNummer);
	}

	/**
	 * Laadt een deelnemer en zijn renners uit de database. Doordat Hibernate gebruik maakt van het lazy-loading is het noodzakelijk dat
	 * alle objecten die benaderd moet worden in 1 Hibernate sessie uit de database gehaald worden. Deze methode wordt d.m.v. Spring AOP
	 * geinjecteerd met een transactie. Een transactie maakt automatisch gebruik van 1 sessie. Hierdoor wordt het mogelijk om binnen deze
	 * methode objecten die een relatie hebben met het opgevraagde object ook uit de database te laden (dit wordt gedaan door een
	 * Hibernate.initialize uit te voeren op deze nog niet geladen objecten).
	 * 
	 * @param nummer
	 *            Het deelnemernummer van de deelnemer
	 * @return de Deelnemer
	 * 
	 * @see Deelnemer
	 */
	public Deelnemer loadDeelnemerEager(final int nummer) {
		Deelnemer deelnemer = loadDeelnemer(nummer);
		//Eagerly load the set of cyclists.
		JpaUtil.initialize(deelnemer.getRenners());
		return deelnemer;
	}

	/**
	 * Geeft alle deelnemers uit de Deelnemer tabel terug.
	 * 
	 * @return de lijst met deelnemers
	 * 
	 * @see Deelnemer
	 */
	@SuppressWarnings("unchecked")
	public List<Deelnemer> loadAllDeelnemers() {
		return getJpaTemplate().find("from " + DEELNEMER_CLASS_NAME);
	}

	/**
	 * Geeft alle deelnemers uit de Deelnemer tabel terug. Deze methode initialiseert ook de renners Set van elke deelnemer
	 * 
	 * @return de lijst met deelnemers
	 * 
	 * @see Deelnemer
	 */
	@Transactional
	public List<Deelnemer> loadAllDeelnemersEager() {
		// TODO Performance verbeteren. Misschien kunnen we dit verbeteren door een HQL statement aan te maken waarin we de renners meteen
		// fetchen.
		List<Deelnemer> deelnemers = loadAllDeelnemers();
		// Iterate over list and initialize cyclists.
		for (Deelnemer nextDeelnemer : deelnemers) {
			//Initialize the lazy loaded set.
			JpaUtil.initialize(nextDeelnemer.getRenners());
		}
		return deelnemers;
	}

	/**
	 * Stores or updates the {@link Deelnemer} in the database.
	 * 
	 * @param saveDeelnemer the {@link Deelnemer} to be saved.
	 */
	@Transactional
	public void saveDeelnemer(final Deelnemer saveDeelnemer) {
		getJpaTemplate().merge(saveDeelnemer);
	}

	/**
	 * Update de deelnemer met de nieuwe gegevens. Foreign key relaties met Renners blijven hierdoor ongewijzigd.
	 * 
	 * @param nummer
	 *            Deelnemernummer
	 * @param voornaam
	 *            Voornaam van de deelnemer
	 * @param achternaam
	 *            Achternaam van de deelnemer
	 * @param email
	 *            E-mail adres van de deelnemer
	 * @param rekeningnummer
	 *            Rekeningnummer van de deelnemer
	 */
	@Transactional
	public void saveDeelnemerDataExcludingForeignKeys(final int nummer, final String voornaam, final String achternaam, final String email,
			final String rekeningnummer) {

		final String queryString = "update " + DEELNEMER_CLASS_NAME + " set voornaam = :newVoornaam, " + "achternaam = :newAchternaam, " + "email = :newEmail, "
				+ "rekeningnummer = :newRekeningnummer " + "where nummer = :deelnemerNummer";
				
		int rowCount = ((Integer) getJpaTemplate().execute(new JpaCallback() {
			@Override
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				query.setParameter("newVoornaam", voornaam);
				query.setParameter("newAchternaam", achternaam);
				query.setParameter("newRekeningnummer", rekeningnummer);
				query.setParameter("newEmail", email);
				query.setParameter("deelnemerNummer", nummer);
				return query.executeUpdate();
			}
		}));
		
		LOG.debug("Rows affected: " + rowCount);
	}

	/**
	 * Verwijdert de meegegeven deelnemer uit de database
	 * 
	 * @param delDeelnemer
	 *            de te verwijderen Deelnemer
	 * 
	 * @return
	 */
	@Transactional
	public void deleteDeelnemer(final Deelnemer delDeelnemer) {
		getJpaTemplate().remove(delDeelnemer);
	}

	/**
	 * Haalt een deelnemer uit de db op die de meegegeven renner in zijn renners Set heeft.
	 * 
	 * @param renner
	 *            De renner die in de rennerset van de deelnemer moet zitten
	 * 
	 * @return deelnemer
	 */
	public Deelnemer loadDeelnemerHavingRenner(final Renner renner) {
		final String queryString = "select deelnemer from Deelnemer as deelnemer, Renner as renner where renner in elements(deelnemer.renners) and renner.nummer=:rennernummer";
		Deelnemer deelnemer = (Deelnemer) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				query.setParameter("rennernummer", renner.getNummer());
				return query.getSingleResult();
			}
		});
		return deelnemer;
	}

}
