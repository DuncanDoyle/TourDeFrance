package nl.doyle.mccloud.tourdefrance.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PloegenTijdritDaoHibernateImpl extends HibernateDaoSupport
		implements PloegenTijdritDao {

	/**
	 * Verwijdert de ploegentijdrit uit de database
	 * 
	 * @param deletePloegenTijdrit
	 *            De ploegentijdrit
	 */
	public void deletePloegenTijdrit(PloegenTijdrit deletePloegenTijdrit) {
		getHibernateTemplate().delete(deletePloegenTijdrit);
	}

	/**
	 * Laadt alle ploegentijdritten
	 * 
	 * @return Lijst met ploegentijdritten
	 */
	public List<PloegenTijdrit> loadAllPloegenTijdritten() {
		return getHibernateTemplate().loadAll(PloegenTijdrit.class);
	}

	/**
	 * Laadt de ploegentijdrit. Geeft null terug wanneer de ploegentijdrit niet
	 * in de database staat.
	 * 
	 * @param etappenummer
	 *            Het etappenummer
	 * @return De ploegentijdrit
	 */
	public PloegenTijdrit loadPloegenTijdrit(int etappenummer) {
		List<PloegenTijdrit> result = getHibernateTemplate().find(
				"from PloegenTijdrit tijdrit where tijdrit.etappenummer=?",
				new Integer(etappenummer));
		// We hebben op primary key gezocht, dus er is maar 1 ploegentijdrit (of
		// geen) in de lijst
		PloegenTijdrit returnPloegenTijdrit = null;
		Iterator<PloegenTijdrit> listIterator = result.iterator();
		if (listIterator.hasNext()) {
			returnPloegenTijdrit = listIterator.next();
		}
		return returnPloegenTijdrit;
	}

	/**
	 * Laadt de ploegentijdrit inclusief de start- en finishplaats en de
	 * uitslagen. Deze methode heeft een AOP Hibernate Transactie nodig om te
	 * werken. Geeft null terug wanneer de ploegentijdrit niet in de database
	 * staat.
	 * 
	 * @param etappenummer
	 *            Het etappenummer
	 * @return De ploegentijdrit
	 */
	public PloegenTijdrit loadPloegenTijdritWithUitslagEager(int etappenummer) {
		PloegenTijdrit etappe = loadPloegenTijdrit(etappenummer);
		if (etappe != null) {
			Hibernate.initialize(etappe.getStartplaats());
			Hibernate.initialize(etappe.getFinishplaats());
			Hibernate.initialize(etappe.getBolletjesTruiUitslag());
			Hibernate.initialize(etappe.getGeleTruiUitslag());
			Hibernate.initialize(etappe.getGroeneTruiUitslag());
		}
		return etappe;
	}

	/**
	 * Laadt de ploegentijdrit inclusief start- en finishplaats. Geeft null
	 * terug wanneer de ploegentijdrit niet in de database staat.
	 * 
	 * @param etappenummer
	 *            Het etappenummer
	 * @return De ploegentijdrit
	 */
	public PloegenTijdrit loadPloegenTijdritWithStartAndFinish(
			final int etappenummer) {
		final String hql = "from PloegenTijdrit pt "
				+ "left join fetch pt.startplaats "
				+ "left join fetch pt.finishplaats where pt.etappenummer=:etappenummer";

		PloegenTijdrit etappe = (PloegenTijdrit) getHibernateTemplate()
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						query.setInteger("etappenummer", etappenummer);
						return query.uniqueResult();
					}
				});
		return etappe;
	}

	/**
	 * Slaat de ploegentijdrit op in de database.
	 * 
	 * @param savePloegenTijdrit
	 *            De ploegentijdrit die opgeslagen moet worden.
	 */
	public void savePloegenTijdrit(PloegenTijdrit savePloegenTijdrit) {
		getHibernateTemplate().saveOrUpdate(savePloegenTijdrit);
	}

	/**
	 * Laad alle ploegentijdritten inclusief steden.
	 * 
	 * @return StandaardEtappe
	 */
	public List<PloegenTijdrit> loadAllPloegenTijdrittenWithStedenEager() {
		final String hql = "from PloegenTijdrit pt "
				+ "left join fetch pt.startplaats "
				+ "left join fetch pt.finishplaats";

		List<PloegenTijdrit> etappes = (List<PloegenTijdrit>) getHibernateTemplate()
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						return query.list();
					}
				});
		return etappes;
	}

	/**
	 * Geeft het hoogste etappenummer van de ploegentijdritten terug.
	 * 
	 * @return etappenummer
	 */
	public int getHighestEtappeNummer() {
		int nummer = 0;
		final String hql = "select max(etappenummer) from PloegenTijdrit";
		Object nummerObject = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				return query.uniqueResult();
			}
		});
		if (nummerObject != null) {
			nummer = (Integer) nummerObject;
		}
		return nummer;
	}

}
