package nl.doyle.mccloud.tourdefrance.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.util.JpaUtil;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the {@link StandaardEtappeDao} interface.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class StandaardEtappeDaoJpaImpl extends JpaDaoSupport implements StandaardEtappeDao {

	/**
	 * The name of the {@link StandaardEtappe} class as a {@link String}.
	 */
	private static final String STANDAARDETAPPE_CLASS_NAME = StandaardEtappe.class.getName();

	/**
	 * Default constructor
	 */
	public StandaardEtappeDaoJpaImpl() {
	}

	/**
	 * Loads all {@link StandaardEtappe} instances from the database.
	 * 
	 * @return all {@link StandaardEtappe} entities in the database.
	 */
	@SuppressWarnings("unchecked")
	public List<StandaardEtappe> loadAllStandaardEtappes() {
		return getJpaTemplate().find("from " + STANDAARDETAPPE_CLASS_NAME);
	}

	/**
	 * Loads the {@link StandaardEtappe} with the given primary key from the database.
	 * 
	 * @param int the stage number (i.e. primary key).
	 * 
	 * @return the {@link StandaardEtappe} with the given primary key.
	 */
	public StandaardEtappe loadStandaardEtappe(final int etappenummer) {
		return getJpaTemplate().find(StandaardEtappe.class, etappenummer);
	}

	/**
	 * Loads the {@link StandaardEtappe} with given primary key from the database, including its start and finish {@link Stad cities} (i.e.
	 * it loads these entities eagerly).
	 * 
	 * @param etappenummer
	 *            the stage number of the {@link StandaardEtappe} to be loaded.
	 * @return the {@link StandaardEtappe} with the given stage number, including its start and finish {@link Stad cities}.
	 */
	public StandaardEtappe loadStandaardEtappeWithStartAndFinish(final int etappenummer) {
		final String queryString = "from " + STANDAARDETAPPE_CLASS_NAME + " st " + "left join fetch st.startplaats "
				+ "left join fetch st.finishplaats where st.etappenummer=:etappenummer";

		return (StandaardEtappe) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				query.setParameter("etappenummer", etappenummer);
				Object result;
				try {
					result = query.getSingleResult();
				} catch (NoResultException nre) {
					//Nothing found. Just return null
					result = null;
				}
				return result;
			}
		});
	}

	/**
	 * Laad de etappe met het meegekregen etappenummer inclusief left-outer-joins naar uitslagen en renners. Deze methode vereist dat er een
	 * Hibernate transactie aanwezig is.
	 * 
	 * @param etappenummer
	 *            Nummer van de te laden etappe
	 * @return StandaardEtappe
	 */
	@Transactional
	public StandaardEtappe loadStandaardEtappeWithUitslagEager(final int etappenummer) {
		// TODO Deze methode moet in volgende versies van Hibernate makkelijker kunnen.
		/*
		 * Het probleem is nu dat we 3 sets moeten laden. Als we dit in 1 HQL statement doen dan krijgen we te maken met het 'Cartesian
		 * Product' probleem, wat ervoor zorgt dat het HQL statement veel te veel rijen teruggeeft wat nadelig is (kan zijn) voor de
		 * performance van de applicatie. Met een criteria bleek het probleem ook niet oplosbaar omdat je aan een Criteria slechts 1
		 * Criteria op het root niveau kan koppelen.
		 * 
		 * De ideale oplossing is om een Etappe object te laden d.m.v. 3 HQL statements die elk een set inladen. Dit blijkt echter op dit
		 * moement niet te kunnen.
		 * 
		 * We hebben het nu op gelost door "lazy=false" te definieren in de Hibernate mappings van de uitslagen en de 3 sets te
		 * initialiseren d.m.v. een Hibernate.initialize. Dit lijkt in theorie de best performende manier te zijn.
		 * 
		 * Update: New version uses JPA, so we're initializing using our JpaUtil.initialize method on the Sets.
		 */

		StandaardEtappe etappe = loadStandaardEtappeWithStartAndFinish(etappenummer);
		// Als er geen etappe is gevonden krijgen we null terug
		if (etappe != null) {
			JpaUtil.initialize(etappe.getBolletjesTruiUitslag());
			JpaUtil.initialize(etappe.getGeleTruiUitslag());
			JpaUtil.initialize(etappe.getGroeneTruiUitslag());
			JpaUtil.initialize(etappe.getWitteTruiUitslag());
			JpaUtil.initialize(etappe.getEtappeUitslag());

			// Loading these racers by calling their  'getNummer()' methods.
			Renner loadRenner;
			loadRenner = etappe.getMostCombativeRacer();
			if (loadRenner != null) {
				loadRenner.getNummer();
			}
			loadRenner = etappe.getRodeLantaren();
			if (loadRenner != null) {
				loadRenner.getNummer();
			}
		}
		return etappe;
	}

	/**
	 * Laad alle standaardetappes inclusief steden.
	 * 
	 * 
	 * @return StandaardEtappe
	 */
	@SuppressWarnings("unchecked")
	public List<StandaardEtappe> loadAllStandaardEtappesWithStartAndFinishEager() {
		final String queryString = "from " + STANDAARDETAPPE_CLASS_NAME + " st " + "left join fetch st.startplaats "
				+ "left join fetch st.finishplaats";

		return (List<StandaardEtappe>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				return query.getResultList();
			}
		});
	}

	/**
	 * Saves or updates the given {@link StandaardEtappe} in the database.
	 * 
	 * @param saveStandaardEtappe
	 *            the {@link StandaardEtappe} to be stored.
	 */
	@Transactional
	public void saveStandaardEtappe(final StandaardEtappe saveStandaardEtappe) {
		getJpaTemplate().merge(saveStandaardEtappe);

	}

	/**
	 * Removes the given {@link StandaardEtappe} from the database.
	 * 
	 * @param deleteStandaardEtappe
	 *            the {@link StandaardEtappe} to be removed.
	 */
	@Transactional
	public void deleteStandaardEtappe(final StandaardEtappe deleteStandaardEtappe) {
		getJpaTemplate().remove(deleteStandaardEtappe);
	}

	/**
	 * Returns the highest stage number.
	 * 
	 * @return the highest stage number.
	 */
	public int getHighestEtappeNummer() {
		int nummer = 0;
		final String queryString = "select max(etappenummer) from " + STANDAARDETAPPE_CLASS_NAME;
		Object nummerObject = getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				Object result;
				try {
					result = query.getSingleResult();
				} catch (NoResultException nre) {
					//Nothing found. Just return null
					result = null;
				}
				return result;
			}
		});
		if (nummerObject != null) {
			nummer = (Integer) nummerObject;
		}
		return nummer;
	}

}
