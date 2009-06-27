package nl.doyle.mccloud.tourdefrance.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import nl.doyle.mccloud.tourdefrance.dao.UitslagBedragDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedragPk;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag.Categorien;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the {@link UitslagBedragDao} interface.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class UitslagBedragDaoJpaImpl extends JpaDaoSupport implements UitslagBedragDao {

	/**
	 * The name of the {@link UitslagBedrag} class as a {@link String}.
	 */
	private static final String UITSLAGBEDRAG_CLASS_NAME = UitslagBedrag.class.getName();
	
	private static final String UITSLAGBEDRAF_PK_CLASS_NAME = UitslagBedragPk.class.getName();
	

	@SuppressWarnings("unchecked")
	public List<UitslagBedrag> loadAllUitslagBedragen() {
		return getJpaTemplate().find("from " + UITSLAGBEDRAG_CLASS_NAME);
	}

	@SuppressWarnings("unchecked")
	public List<UitslagBedrag> loadAllUitslagBedragenPerCategorie(final Categorien categorie) {
	/*	
	"SELECT e FROM Professor e WHERE e.id.country = ?1 AND e.id.id = ?2")
    .setParameter(1, country).setParameter(2, id).getSingleResult();
		*/
		final String hql = "from UitslagBedrag ub WHERE ub.uitslagBedragPk.categorie=:currentcategorie";

		List<UitslagBedrag> uitslagBedragen = (List<UitslagBedrag>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(hql);
				//Query query = em.createQuery("SELECT * FROM UITSLAG_BEDRAG ub WHERE ub.uitslagBedragPk.categorie = :currentcategorie");
				query.setParameter("currentcategorie", categorie);
				return query.getResultList();
			}
		});
		return uitslagBedragen;
	}

	public UitslagBedrag loadUitslagBedrag(final Categorien categorie, final int positie) {
		final String queryString = "from " + UITSLAGBEDRAG_CLASS_NAME + " ub WHERE ub.uitslagBedragPk.categorie=:currentcategorie and ub.uitslagBedragPk.positie=:currentpositie";

		UitslagBedrag uitslagBdr = (UitslagBedrag) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				//Query query = em.createQuery("SELECT * FROM UITSLAG_BEDRAG ub WHERE ub.uitslagBedragPk.categorie = :currentcategorie and ub.uitslagBedragPk.positie=:currentpositie");

				query.setParameter("currentcategorie", categorie);
				query.setParameter("currentpositie", positie);
				return query.getSingleResult();
			}
		});
		return uitslagBdr;
	}

	/**
	 * Saves or updates the {@link UitslagBedrag} in the database.
	 * 
	 * @param saveUitslagBedrag
	 *            the {@link UitslagBedrag} to be saved.
	 */
	@Transactional
	public void saveUitslagBedrag(UitslagBedrag saveUitslagBedrag) {
		getJpaTemplate().merge(saveUitslagBedrag);
	}

}
