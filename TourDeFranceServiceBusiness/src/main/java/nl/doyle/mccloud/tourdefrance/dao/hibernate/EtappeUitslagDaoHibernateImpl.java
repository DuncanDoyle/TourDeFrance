package nl.doyle.mccloud.tourdefrance.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.EtappeUitslagDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.EtappeUitslag;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EtappeUitslagDaoHibernateImpl extends HibernateDaoSupport implements EtappeUitslagDao {

	public void deleteEtappeUitslag(EtappeUitslag delEtappeUitslag) {
		getHibernateTemplate().delete(delEtappeUitslag);		
	}

	public List<EtappeUitslag> loadAllEtappeUitslagen() {
		return getHibernateTemplate().loadAll(EtappeUitslag.class);
	}

	public EtappeUitslag loadDEtappeUitslag(int etappeNummer, int positieNummer) {
		Integer [] parameters = {new Integer(etappeNummer), new Integer(positieNummer)};
		List result = getHibernateTemplate().find("from EtappeUitslag uitslag where uitslag.etappenummer=? and uitslag.positie=?", parameters);
		//	We hebben op primary key gezocht, dus er is maar 1 uitslag (of geen) in de lijst
		EtappeUitslag returnEtappeUitslag = null;
		Iterator listIterator = result.iterator();
		if (listIterator.hasNext()) {
			returnEtappeUitslag = (EtappeUitslag) listIterator.next();
		}
		return returnEtappeUitslag;
	}

	public void saveEtappeUitslag(EtappeUitslag saveEtappeUitslag) {
		getHibernateTemplate().save(saveEtappeUitslag);
	}

}
