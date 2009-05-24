package nl.doyle.mccloud.tourdefrance.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import nl.doyle.mccloud.tourdefrance.dao.StadDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 * JPA implementation of the {@link StadDao} interface.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class StadDaoJpaImpl extends JpaDaoSupport implements StadDao {

	/**
	 * The name of the {@link Stad} class as a {@link String}.
	 */
	private static final String STAD_CLASS_NAME = Stad.class.getName();

	/**
	 * Removes the passed {@link Stad} from the database.
	 * 
	 * @param delStad
	 *            the {@link Stad} to be deleted.
	 */
	public void deleteStad(Stad delStad) {
		getJpaTemplate().remove(delStad);

	}

	/**
	 * Returns the {@link Stad} with the given id.
	 * 
	 * @param id
	 *            the {@link Stad} id.
	 * @return the {@link Stad} with the given id.
	 */
	public Stad loadStad(int id) {
		return getJpaTemplate().find(Stad.class, id);
	}

	/**
	 * Loads all {@link Stad} objects from the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Stad> loadAllSteden() {
		return (List<Stad>) getJpaTemplate().find("from " + STAD_CLASS_NAME);
	}

	/**
	 * Loads the {@link Stad} with the given name in the given country.
	 * 
	 * @param stad
	 *            the name
	 * @param land
	 *            the country
	 * 
	 * @return the {@link Stad} with the given name in the given country.
	 */
	@SuppressWarnings("unchecked")
	public List<Stad> loadStad(final String stad, final String land) {
		return (List<Stad>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery("from " + STAD_CLASS_NAME + " stad WHERE stad.stad=? AND stad.land=?");
				query.setParameter(0, stad);
				query.setParameter(1, stad);
				return query.getResultList();
			}
		});
	}

	/**
	 * Stores or updates the given {@link Stad} in the database.
	 */
	public void saveOrUpdateStad(Stad saveStad) {
		getJpaTemplate().merge(saveStad);

	}

}
