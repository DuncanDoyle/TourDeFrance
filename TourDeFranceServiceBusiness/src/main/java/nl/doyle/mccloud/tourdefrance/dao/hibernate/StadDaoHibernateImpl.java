package nl.doyle.mccloud.tourdefrance.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.StadDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StadDaoHibernateImpl extends HibernateDaoSupport implements StadDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.doyle.mccloud.tourdefrance.dao.StadDao#deleteStad(nl.doyle.mccloud.tourdefrance.valueobjects.Stad)
	 */
	public void deleteStad(Stad delStad) {
		getHibernateTemplate().delete(delStad);

	}

	@SuppressWarnings("unchecked")
	public Stad loadStad(int id) {
		List<Stad> result = getHibernateTemplate().find("from Stad stad where stad.id=?", new Integer(id));
		// We hebben op primary key gezocht, dus er is maar 1 deelnemer (of geen) in de lijst
		Stad returnStad = null;
		Iterator<Stad> listIterator = result.iterator();
		if (listIterator.hasNext()) {
			returnStad = (Stad) listIterator.next();
		}
		return returnStad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.doyle.mccloud.tourdefrance.dao.StadDao#loadAllSteden()
	 */
	@SuppressWarnings("unchecked")
	public List<Stad> loadAllSteden() {
		return getHibernateTemplate().loadAll(Stad.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.doyle.mccloud.tourdefrance.dao.StadDao#loadStad(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Stad> loadStad(final String stad, final String land) {
		List<Stad> steden = (List<Stad>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("from Stad stad WHERE stad.stad=? AND stad.land=?");
				query.setString(0, stad);
				query.setString(1, stad);
				return query.list();
			}
		});

		return steden;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.doyle.mccloud.tourdefrance.dao.StadDao#saveStad(nl.doyle.mccloud.tourdefrance.valueobjects.Stad)
	 */
	public void saveOrUpdateStad(Stad saveStad) {
		getHibernateTemplate().saveOrUpdate(saveStad);

	}

}
