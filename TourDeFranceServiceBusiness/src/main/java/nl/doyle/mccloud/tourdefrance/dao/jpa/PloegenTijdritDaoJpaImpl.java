package nl.doyle.mccloud.tourdefrance.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.util.JpaUtil;
import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the {@link StandaardEtappeDao} interface.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class PloegenTijdritDaoJpaImpl extends JpaDaoSupport implements PloegenTijdritDao {

	/**
	 * The name of the {@link PloegenTijdrit} class as a {@link String}.
	 */
	private static final String PLOEGENTIJDRIT_CLASS_NAME = PloegenTijdrit.class.getName();

	/**
	 * Deletes the given {@link PloegenTijdrit} from the database.
	 * 
	 * @param deletePloegenTijdrit
	 *            the {@link PloegenTijdrit} to be deleted.
	 */
	@Transactional
	public void deletePloegenTijdrit(final PloegenTijdrit deletePloegenTijdrit) {
		getJpaTemplate().remove(deletePloegenTijdrit);
	}

	/**
	 * Laadt alle ploegentijdritten
	 * 
	 * @return Lijst met ploegentijdritten
	 */
	@SuppressWarnings("unchecked")
	public List<PloegenTijdrit> loadAllPloegenTijdritten() {
		return getJpaTemplate().find("from " + PLOEGENTIJDRIT_CLASS_NAME);
	}

	/**
	 * Loads the {@link PloegenTijdrit} with the given stage number from the database.
	 * 
	 * @param etappenummer
	 *            the stage number.
	 * @return the {@link PloegenTijdrit} with the given stage number.
	 */
	public PloegenTijdrit loadPloegenTijdrit(int etappenummer) {
		return getJpaTemplate().find(PloegenTijdrit.class, etappenummer);
	}

	/**
	 * Laadt de ploegentijdrit inclusief de start- en finishplaats en de uitslagen. Deze methode heeft een AOP Hibernate Transactie nodig om
	 * te werken. Geeft null terug wanneer de ploegentijdrit niet in de database staat.
	 * 
	 * @param etappenummer
	 *            Het etappenummer
	 * @return De ploegentijdrit
	 */
	@Transactional
	public PloegenTijdrit loadPloegenTijdritWithUitslagEager(int etappenummer) {
		PloegenTijdrit etappe = loadPloegenTijdritWithStartAndFinish(etappenummer);
		if (etappe != null) {
			JpaUtil.initialize(etappe.getBolletjesTruiUitslag());
			JpaUtil.initialize(etappe.getGeleTruiUitslag());
			JpaUtil.initialize(etappe.getGroeneTruiUitslag());

			// Loading the witte trui by calling its 'getNummer()' method.
			etappe.getWitteTrui().getNummer();
		}
		return etappe;
	}

	/**
	 * Loads the {@link PloegenTijdrit} with the given stage number, including its start and finish {@link Stad cities} (i.e. it loads them
	 * eagerly).
	 * 
	 * @param etappenummer
	 *            the stage number.
	 * @return the {@link PloegenTijdrit} with the given stage number.
	 */
	public PloegenTijdrit loadPloegenTijdritWithStartAndFinish(final int etappenummer) {
		final String queryString = "from " + PLOEGENTIJDRIT_CLASS_NAME + " pt " + "left join fetch pt.startplaats "
				+ "left join fetch pt.finishplaats where pt.etappenummer=:etappenummer";

		return (PloegenTijdrit) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				query.setParameter("etappenummer", etappenummer);
				return query.getSingleResult();
			}
		});
	}

	/**
	 * Saves or updates the given {@link PloegenTijdrit} in the database.
	 * 
	 * @param savePloegenTijdrit
	 *            the {@link PloegenTijdrit} toe be stored.
	 */
	@Transactional
	public void savePloegenTijdrit(final PloegenTijdrit savePloegenTijdrit) {
		getJpaTemplate().merge(savePloegenTijdrit);
	}

	/**
	 * Laad alle ploegentijdritten inclusief steden.
	 * 
	 * @return StandaardEtappe
	 */
	@SuppressWarnings("unchecked")
	public List<PloegenTijdrit> loadAllPloegenTijdrittenWithStartAndFinishEager() {
		final String queryString = "from " + PLOEGENTIJDRIT_CLASS_NAME + " pt " + "left join fetch pt.startplaats "
				+ "left join fetch pt.finishplaats";

		return (List<PloegenTijdrit>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				return query.getResultList();
			}
		});

	}

	/**
	 * Returns the highest stage number.
	 * 
	 * @return the highest stage number.
	 */
	public int getHighestEtappeNummer() {
		int nummer = 0;
		final String queryString = "select max(etappenummer) from " + PLOEGENTIJDRIT_CLASS_NAME;

		Object nummerObject = getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				return query.getSingleResult();
			}
		});
		if (nummerObject != null) {
			nummer = (Integer) nummerObject;
		}
		return nummer;
	}

}
