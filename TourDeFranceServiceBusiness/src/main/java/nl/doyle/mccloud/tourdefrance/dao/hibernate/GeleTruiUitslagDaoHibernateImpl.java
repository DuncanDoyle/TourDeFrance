package nl.doyle.mccloud.tourdefrance.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.GeleTruiUitslagDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.GeleTruiUitslag;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class GeleTruiUitslagDaoHibernateImpl extends HibernateDaoSupport implements
		GeleTruiUitslagDao {

	public void deleteGeleTruiUitslag(GeleTruiUitslag delGeleTruiUitslag) {
		getHibernateTemplate().delete(delGeleTruiUitslag);		
	}

	public List<GeleTruiUitslag> loadAllGeleTruiUitslagen() {
		return getHibernateTemplate().loadAll(GeleTruiUitslag.class);
	}

	public GeleTruiUitslag loadGeleTruiUitslag(int etappeNummer, int positieNummer) {
		Integer [] parameters = {new Integer(etappeNummer), new Integer(positieNummer)};
		List result = getHibernateTemplate().find("from GeleTruiUitslag uitslag where uitslag.etappenummer=? and uitslag.positie=?", parameters);
		//	We hebben op primary key gezocht, dus er is maar 1 uitslag (of geen) in de lijst
		GeleTruiUitslag returnGeleTruiUitslag = null;
		Iterator listIterator = result.iterator();
		if (listIterator.hasNext()) {
			returnGeleTruiUitslag = (GeleTruiUitslag) listIterator.next();
		}
		return returnGeleTruiUitslag;
	}

	public void saveGeleTruiUitslag(GeleTruiUitslag saveGeleTruiUitslag) {
		getHibernateTemplate().save(saveGeleTruiUitslag);
	}

	
	
}
