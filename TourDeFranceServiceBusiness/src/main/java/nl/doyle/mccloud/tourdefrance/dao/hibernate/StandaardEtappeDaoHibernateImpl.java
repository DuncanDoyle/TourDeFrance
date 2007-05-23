package nl.doyle.mccloud.tourdefrance.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StandaardEtappeDaoHibernateImpl extends HibernateDaoSupport implements StandaardEtappeDao {

	private static final Log log = LogFactory.getLog(StandaardEtappeDaoHibernateImpl.class);
	
	/**
	 * @author mccloud
	 * 
	 * Default constructor
	 */
	public StandaardEtappeDaoHibernateImpl() {
	}
	
	/**
	 * @author mccloud
	 * 
	 * Laadt alle standaardetappes uit de database
	 * 
	 * @return Collection van StandaardEtappes
	 */
	public List<StandaardEtappe> loadAllStandaardEtappes() {
		return getHibernateTemplate().loadAll(StandaardEtappe.class);
	}
	
	/**
	 * @author mccloud
	 * 
	 * Laadt de etappe met de meegegeven primary key uit de database
	 * 
	 * @param int etappenummer
	 * 
	 * @return StandaardEtappe
	 */
	public StandaardEtappe loadStandaardEtappe(int etappenummer) {
		List<StandaardEtappe> result = getHibernateTemplate().find("from StandaardEtappe se where se.etappenummer=?", new Integer(etappenummer));
		//We hebben op primary key gezocht, dus er is maar 1 etappe (of geen) in de lijst
		StandaardEtappe returnEtappe = null;
		Iterator<StandaardEtappe> listIterator = result.iterator();
		if (listIterator.hasNext()) {
			returnEtappe = listIterator.next();
		}
		return returnEtappe;
	}
	
	
	public StandaardEtappe loadStandaardEtappeWithStartAndFinish(final int etappenummer) {
		/*StandaardEtappe etappe = loadStandaardEtappe(etappenummer);
		Hibernate.initialize(etappe.getStartplaats());
		Hibernate.initialize(etappe.getFinishplaats());
		return etappe;
		*/
		final String hql = "from StandaardEtappe st " +
		"left join fetch st.startplaats " +
		"left join fetch st.finishplaats where st.etappenummer=:etappenummer";

		StandaardEtappe etappe = (StandaardEtappe) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				query.setInteger("etappenummer", etappenummer);
				return query.uniqueResult();
			}
		});
		return etappe;
	}

	
	/**
	 * Laad de etappe met het meegekregen etappenummer inclusief left-outer-joins naar uitslagen en renners.
	 * Deze methode vereist dat er een Hibernate transactie aanwezig is.
	 *
	 * @param etappenummer Nummer van de te laden etappe
	 * @return StandaardEtappe
	 */
	public StandaardEtappe loadStandaardEtappeWithUitslagEager(final int etappenummer) {
		//TODO Deze methode moet in volgende versies van Hibernate makkelijker kunnen.
		/* 
		 * Het probleem is nu dat we 3 sets moeten laden. Als we dit in 1 HQL statement doen dan krijgen we te maken met het
		 * 'Cartesian Product' probleem, wat ervoor zorgt dat het HQL statement veel te veel rijen teruggeeft wat nadelig is
		 * (kan zijn) voor de performance van de applicatie.
		 * Met een criteria bleek het probleem ook niet oplosbaar omdat je aan een Criteria slechts 1 Criteria op het root niveau
		 * kan koppelen.
		 * 
		 * De ideale oplossing is om een Etappe object te laden d.m.v. 3 HQL statements die elk een set inladen. Dit blijkt
		 * echter op dit moement niet te kunnen.
		 * 
		 * We hebben het nu op gelost door "lazy=false" te definieren in de Hibernate mappings van de uitslagen en de 3 sets
		 * te initialiseren d.m.v. een Hibernate.initialize. Dit lijkt in theorie de best performende manier te zijn.
		 * 
		/*
		final String hql = "from StandaardEtappe st " +
							"left join fetch st.geleTruiUitslag as geel " +
							"left join fetch geel.renner as rennergeel " +
							"left join fetch st.groeneTruiUitslag as groen " +
							"left join fetch groen.renner as rennergroen " +
							"where st.etappenummer = :etappenummer";
		
		StandaardEtappe etappe = (StandaardEtappe) getHibernateTemplate().execute(new HibernateCallback() {
								public Object doInHibernate(Session session) throws HibernateException {
									Query query = session.createQuery(hql);
									query.setInteger("etappenummer", etappenummer);
									return query.uniqueResult();
								}
			});
		
		*/
		/*
		Session session = getSession();
		
		StandaardEtappe etappe = (StandaardEtappe) session.createCriteria(StandaardEtappe.class).add(Restrictions.eq("etappenummer", etappenummer))
				.createCriteria("geleTruiUitslag", CriteriaSpecification.LEFT_JOIN)
				.createCriteria("renner", CriteriaSpecification.LEFT_JOIN)
				.uniqueResult();
		*/
		/*
		Criteria standaardEtappe = session.createCriteria(StandaardEtappe.class).add(Restrictions.eq("etappenummer", etappenummer));
		Criteria geleTrui = new Criteria(("geleTruiUitslag", CriteriaSpecification.LEFT_JOIN);
		*/
		
		StandaardEtappe etappe = loadStandaardEtappe(etappenummer);
		//Als er geen etappe is gevonden krijgen we null terug
		if (etappe != null) {
			Hibernate.initialize(etappe.getStartplaats());
			Hibernate.initialize(etappe.getFinishplaats());
			Hibernate.initialize(etappe.getBolletjesTruiUitslag());
			Hibernate.initialize(etappe.getGeleTruiUitslag());
			Hibernate.initialize(etappe.getGroeneTruiUitslag());
			Hibernate.initialize(etappe.getEtappeUitslag());
		}
		return etappe;
	}
	
	/**
	 * Laad alle standaardetappes inclusief steden.
	 *
	 * 
	 * @return StandaardEtappe
	 */
	public List<StandaardEtappe> loadAllStandaardEtappesWithStedenEager() {
		final String hql = "from StandaardEtappe st " +
		"left join fetch st.startplaats " +
		"left join fetch st.finishplaats";

		List<StandaardEtappe> etappes = (List<StandaardEtappe>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
		return etappes;
	}

	/**
	 * @author mccloud
	 * 
	 * Slaat de meegegeven etappe op in de database
	 * 
	 * @param StandaardEtappe
	 */
	public void saveStandaardEtappe(StandaardEtappe saveStandaardEtappe) {
		getHibernateTemplate().saveOrUpdate(saveStandaardEtappe);
	}
		
	/**
	 * @author mccloud
	 * 
	 * Verwijdert de meegegeven standaardetappe uit de database
	 *
	 * @param StandaardEtappe
	 */
	public void deleteStandaardEtappe(StandaardEtappe deleteStandaardEtappe) {
		getHibernateTemplate().delete(deleteStandaardEtappe);
	}
}
