package nl.doyle.mccloud.tourdefrance.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;

import org.springframework.beans.factory.config.SetFactoryBean;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the {@link RennerDao} interface.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class RennerDaoJpaImpl extends JpaDaoSupport implements RennerDao {

	/**
	 * The name of the {@link Renner} class as a {@link String}.
	 */
	private static final String RENNER_CLASS_NAME = Renner.class.getName();
	
	/**
	 * Default Constructor
	 */
	public RennerDaoJpaImpl() {
	}

	/**
	 * Deletes the {@link Renner} from the database.
	 * 
	 * @param deleteRenner
	 *            the {@link Renner} to be deleted.
	 * 
	 * @see Renner
	 */
	@Transactional
	public void deleteRenner(Renner deleteRenner) {
		getJpaTemplate().remove(deleteRenner);
	}

	/**
	 * Loads all {@link Renner renners} from the database.
	 * 
	 * @return de lijst met Renners
	 * 
	 * @see Renner
	 */
	@SuppressWarnings("unchecked")
	public List<Renner> loadAllRenners() {
		return getJpaTemplate().find("from " + RENNER_CLASS_NAME);
	}

	@SuppressWarnings("unchecked")
	public List<Renner> loadAllRennersOrdered() {
		final String queryString = "from " + RENNER_CLASS_NAME + " ren ORDER BY ren.nummer";

		return (List<Renner>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery(queryString);
				return query.getResultList();
			}
		});
	}

	/**
	 * Loads the {@link Renner} with the given primary key from the database.
	 * 
	 * @param rennerNummer
	 *            the primary key of the {@link Renner} to be loaded.
	 * 
	 * @return the {@link Renner}
	 * 
	 * @see Renner
	 */
	public Renner loadRenner(int rennerNummer) {
		return getJpaTemplate().find(Renner.class, rennerNummer);
	}

	/**
	 * Slaat de renner op in de database. Maakt een nieuwe db-entry aan als er nog geen renner met dit rennernummer (primary key) in de
	 * database zit. Update een renner als deze reeds aanwezig is.
	 * 
	 * @param saveRenner
	 *            de Renner die moet worden opgeslagen
	 * 
	 * @see Renner
	 */
	@Transactional
	public void saveRenner(final Renner saveRenner) {
		getJpaTemplate().merge(saveRenner);
	}

}
