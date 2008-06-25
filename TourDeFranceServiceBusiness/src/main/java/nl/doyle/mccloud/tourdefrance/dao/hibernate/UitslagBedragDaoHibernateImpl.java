package nl.doyle.mccloud.tourdefrance.dao.hibernate;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.UitslagBedragDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag.Categorien;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UitslagBedragDaoHibernateImpl extends HibernateDaoSupport implements UitslagBedragDao {

	@SuppressWarnings("unchecked")
	public List<UitslagBedrag> loadAllUitslagBedragen() {
		return getHibernateTemplate().loadAll(UitslagBedrag.class);
	}

	@SuppressWarnings("unchecked")
	public List<UitslagBedrag> loadAllUitslagBedragenPerCategorie(final Categorien categorie) {
		final String hql = "from UitslagBedrag ub WHERE ub.categorie=:currentcategorie";
		
		List<UitslagBedrag> uitslagBedragen = (List<UitslagBedrag>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				query.setSerializable("currentcategorie", categorie);
				return query.list();
			}
		});
		return uitslagBedragen;
	}

	public UitslagBedrag loadUitslagBedrag(final Categorien categorie, final int positie) {
		final String hql = "from UitslagBedrag ub WHERE ub.categorie=:currentcategorie and ub.positie=:currentpositie";
		
		UitslagBedrag uitslagBdr = (UitslagBedrag) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				query.setSerializable("currentcategorie", categorie);
				query.setInteger("currentpositie", positie);
				return query.uniqueResult();
			}
		});
		return uitslagBdr;
	}

	public void saveUitslagBedrag(UitslagBedrag saveUitslagBedrag) {
		getHibernateTemplate().saveOrUpdate(saveUitslagBedrag);
	}

}
