package nl.doyle.mccloud.tourdefrance.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.EindUitslagDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.EindUitslag;

import org.hibernate.Hibernate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EindUitslagDaoHibernateImpl extends HibernateDaoSupport implements EindUitslagDao {

	/*
	public EindUitslag loadEindUitslag() {
		EindUitslag uitslag;
		try {
			uitslag = (EindUitslag) getHibernateTemplate().load(EindUitslag.class, 0);
		} catch (ObjectRetrievalFailureException orfe) {
			//TODO is dit een nette manier om dit te doen. Design by contract is dat er 'null' teruggegeven wordt. Maar hier zijn we eigenlijk unchecked exceptions aan het opeten.
			uitslag = null;
		}
		return uitslag;
	}
	*/
	@SuppressWarnings("unchecked")
	public EindUitslag loadEindUitslag() {
		List<EindUitslag> result = getHibernateTemplate().find("from EindUitslag se where se.etappenummer=?", new Integer(0));
		//We hebben op primary key gezocht, dus er is maar 1 etappe (of geen) in de lijst
		EindUitslag returnEtappe = null;
		Iterator<EindUitslag> listIterator = result.iterator();
		if (listIterator.hasNext()) {
			returnEtappe = listIterator.next();
		}
		return returnEtappe;
	}

	public EindUitslag loadEindUitslagWithUitslagEager() {
		// TODO Auto-generated method stub
		EindUitslag uitslag = loadEindUitslag();
		//Als er geen etappe is gevonden krijgen we null terug
		if (uitslag != null) {
			Hibernate.initialize(uitslag.getEersteUitvaller());
			Hibernate.initialize(uitslag.getRodeLantaren());
			Hibernate.initialize(uitslag.getWitteTrui());
			Hibernate.initialize(uitslag.getBolletjesTruiUitslag());
			Hibernate.initialize(uitslag.getGeleTruiUitslag());
			Hibernate.initialize(uitslag.getGroeneTruiUitslag());
		}
		return uitslag;
	}

	public void saveEindUitslag(EindUitslag saveEindUitslag) {
		getHibernateTemplate().saveOrUpdate(saveEindUitslag);
	}

}
