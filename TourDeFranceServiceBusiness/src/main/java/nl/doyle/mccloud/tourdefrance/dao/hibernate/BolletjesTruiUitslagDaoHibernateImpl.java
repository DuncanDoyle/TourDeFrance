package nl.doyle.mccloud.tourdefrance.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.BolletjesTruiUitslagDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.BolletjesTruiUitslag;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BolletjesTruiUitslagDaoHibernateImpl extends HibernateDaoSupport implements
		BolletjesTruiUitslagDao {

	
	public void deleteBolletjesTruiUitslag(BolletjesTruiUitslag delBolletjesTruiUitslag) {
		getHibernateTemplate().delete(delBolletjesTruiUitslag);
	}

	@SuppressWarnings("unchecked")
	public List<BolletjesTruiUitslag> loadAllBolletjesTruiUitslagen() {
		return getHibernateTemplate().loadAll(BolletjesTruiUitslag.class);
	}

	@SuppressWarnings("unchecked")
	public BolletjesTruiUitslag loadBolletjesTruiUitslag(int etappenummer, int positienummer) {
		Integer [] parameters = {new Integer(etappenummer), new Integer(positienummer)};
		List<BolletjesTruiUitslag> result = getHibernateTemplate().find("from BolletjesTruiUitslag uitslag where uitslag.etappenummer=? and uitslag.positie=?", parameters);
		//	We hebben op primary key gezocht, dus er is maar 1 uitslag (of geen) in de lijst
		BolletjesTruiUitslag returnBolletjesTruiUitslag = null;
		Iterator<BolletjesTruiUitslag> listIterator = result.iterator();
		if (listIterator.hasNext()) {
			returnBolletjesTruiUitslag = (BolletjesTruiUitslag) listIterator.next();
		}
		return returnBolletjesTruiUitslag;
	}

	public void saveBolletjesTruiUitslag(BolletjesTruiUitslag saveBolletjesTruiUitslag) {
		getHibernateTemplate().save(saveBolletjesTruiUitslag);
	}

}
