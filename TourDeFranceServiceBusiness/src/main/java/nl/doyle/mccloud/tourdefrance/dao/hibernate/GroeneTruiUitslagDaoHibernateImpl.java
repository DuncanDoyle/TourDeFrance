package nl.doyle.mccloud.tourdefrance.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.GroeneTruiUitslagDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.GroeneTruiUitslag;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class GroeneTruiUitslagDaoHibernateImpl extends HibernateDaoSupport implements
		GroeneTruiUitslagDao {

	public void deleteGroeneTruiUitslag(GroeneTruiUitslag delGroeneTruiUitslag) {
		getHibernateTemplate().delete(delGroeneTruiUitslag);		
	}

	public List<GroeneTruiUitslag> loadAllGroeneTruiUitslagen() {
		return getHibernateTemplate().loadAll(GroeneTruiUitslag.class);
	}

	public GroeneTruiUitslag loadGroeneTruiUitslag(int etappeNummer, int positieNummer) {
		Integer [] parameters = {new Integer(etappeNummer), new Integer(positieNummer)};
		List result = getHibernateTemplate().find("from GroeneTruiUitslag uitslag where uitslag.etappenummer=? and uitslag.positie=?", parameters);
		//	We hebben op primary key gezocht, dus er is maar 1 uitslag (of geen) in de lijst
		GroeneTruiUitslag returnGroeneTruiUitslag = null;
		Iterator listIterator = result.iterator();
		if (listIterator.hasNext()) {
			returnGroeneTruiUitslag = (GroeneTruiUitslag) listIterator.next();
		}
		return returnGroeneTruiUitslag;
	}

	public void saveGroeneTruiUitslag(GroeneTruiUitslag saveGroeneTruiUitslag) {
		getHibernateTemplate().save(saveGroeneTruiUitslag);
	}

	
}
