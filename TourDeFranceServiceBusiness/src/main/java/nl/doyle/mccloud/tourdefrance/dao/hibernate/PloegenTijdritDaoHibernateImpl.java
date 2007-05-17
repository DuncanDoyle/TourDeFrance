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

public class PloegenTijdritDaoHibernateImpl extends HibernateDaoSupport implements
		PloegenTijdritDao {

	public void deletePloegenTijdrit(PloegenTijdrit deletePloegenTijdrit) {
		getHibernateTemplate().delete(deletePloegenTijdrit);
	}

	public List<PloegenTijdrit> loadAllPloegenTijdritten() {
		return getHibernateTemplate().loadAll(PloegenTijdrit.class);
	}

	public PloegenTijdrit loadPloegenTijdrit(int etappenummer) {
		List<PloegenTijdrit> result = getHibernateTemplate().find("from PloegenTijdrit tijdrit where tijdrit.etappenummer=?", new Integer(etappenummer));
		//	We hebben op primary key gezocht, dus er is maar 1 ploegentijdrit (of geen) in de lijst
		PloegenTijdrit returnPloegenTijdrit = null;
		Iterator<PloegenTijdrit> listIterator = result.iterator();
		if (listIterator.hasNext()) {
			returnPloegenTijdrit = listIterator.next();
		}
		return returnPloegenTijdrit;
	}
	
	public PloegenTijdrit loadPloegenTijdritEager(int etappenummer) {
		PloegenTijdrit etappe = loadPloegenTijdrit(etappenummer);
		Hibernate.initialize(etappe.getStartplaats());
		Hibernate.initialize(etappe.getFinishplaats());
		return etappe;
	}

	public void savePloegenTijdrit(PloegenTijdrit savePloegenTijdrit) {
		getHibernateTemplate().save(savePloegenTijdrit);
	}
	
	/**
	 * Laad alle ploegentijdritten inclusief steden.
	 *
	 * @return StandaardEtappe
	 */
	public List<PloegenTijdrit> loadAllPloegenTijdrittenWithStedenEager() {
		final String hql = "from PloegenTijdrit pt " +
		"left join fetch pt.startplaats " +
		"left join fetch pt.finishplaats";

		List<PloegenTijdrit> etappes = (List<PloegenTijdrit>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
		return etappes;
	}
	
	

}
