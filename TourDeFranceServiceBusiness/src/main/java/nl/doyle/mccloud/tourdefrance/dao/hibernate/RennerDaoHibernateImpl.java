package nl.doyle.mccloud.tourdefrance.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class RennerDaoHibernateImpl extends HibernateDaoSupport implements RennerDao {
	
	private static final Log log = LogFactory.getLog(RennerDaoHibernateImpl.class);
	
	
	
	/**
	 * Default Constructor
	 */
	public RennerDaoHibernateImpl() {
	}

	/**
	 * Verwijdert de Renner uit de database
	 * 
	 * @param deleteRenner de te verwijderen Renner
	 * 
	 * @see Renner
	 */
	public void deleteRenner(Renner deleteRenner) {
		getHibernateTemplate().delete(deleteRenner);
	}

	/**
	 * Laadt alle renners uit de database.
	 * 
	 * @return de lijst met Renners
	 * 
	 * @see Renner
	 */
	public List<Renner> loadAllRenners() {
		return getHibernateTemplate().loadAll(Renner.class);
	}

	public List<Renner> loadAllRennersOrdered() {
		//TODO Dit moet anders geimplementeerd worden. Gebruik maken van Callback functie en HQL
		final String hql = "from Renner ren  ORDER BY ren.nummer";
		
		List<Renner> renners = (List<Renner>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
		return renners;
	}
		
	/**
	 * Laadt de renner met de meegegeven primary key (rennerNummer) uit de database
	 * 
	 * @param rennerNummer het nummer van de te laden Renner
	 * 
	 * @return de Renner
	 * 
	 * @see Renner
	 */
	public Renner loadRenner(int rennerNummer) {
		List result = getHibernateTemplate().find("from Renner renner where renner.nummer=?", new Integer(rennerNummer));
		//We hebben op primary key gezocht, dus er is maar 1 renner (of geen) in de lijst
		Renner returnRenner = null;
		Iterator listIterator = result.iterator();
		if (listIterator.hasNext()) {
			returnRenner = (Renner) listIterator.next();
		}
		return returnRenner;
	}
	
	/**
	 * Slaat de renner op in de database. Gooit een exceptie als er al een renner met hetzelfde rennernummer
	 * (primary key) in de database zit.
	 * 
	 * @param saveRenner de Renner die moet worden opgeslagen
	 * 
	 * @see Renner
	 */
	public void saveRenner(Renner saveRenner) {
		getHibernateTemplate().save(saveRenner);
	}
	
	/**
	 * Slaat de renner op in de database. Maakt een nieuwe db-entry aan als er nog geen renner met
	 * dit rennernummer (primary key) in de database zit. Update een renner als deze reeds aanwezig is.
	 * 
	 * @param saveRenner de Renner die moet worden opgeslagen
	 * 
	 * @see Renner
	 */
	public void saveOrUpdateRenner(Renner saveRenner) {
		getHibernateTemplate().saveOrUpdate(saveRenner);
	}
	
}
