package nl.doyle.mccloud.tourdefrance.dao.jpa;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.GroeneTruiUitslagDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.GroeneTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagPk;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the {@link GroeneTruiUitslagDao} interface.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class GroeneTruiUitslagDaoJpaImpl extends JpaDaoSupport implements GroeneTruiUitslagDao {

	/**
	 * The name of the {@link GroeneTruiUitslag} class as a {@link String}.
	 */
	public static final String GROENETRUIUITSLAG_CLASS_NAME = GroeneTruiUitslag.class.getName();

	/**
	 * Deletes the given {@link GroeneTruiUitslag} from the database.
	 * 
	 * @param the
	 *            {@link GroeneTruiUitslag} to be deleted.
	 */
	@Transactional
	public void deleteGroeneTruiUitslag(GroeneTruiUitslag delGroeneTruiUitslag) {
		getJpaTemplate().remove(delGroeneTruiUitslag);
	}

	/**
	 * Loads all {@link GroeneTruiUitslag} entities from the database.
	 * 
	 * @return a {@link List} containing all {@link GroeneTruiUitslag} entities.
	 */
	@SuppressWarnings("unchecked")
	public List<GroeneTruiUitslag> loadAllGroeneTruiUitslagen() {
		return getJpaTemplate().find("from " + GROENETRUIUITSLAG_CLASS_NAME);
	}

	/**
	 * Loads the {@link GroeneTruiUitslag} with the given stage number and position from the database.
	 * 
	 * @param etappeNummer
	 *            the stage number
	 * @param position
	 *            the position
	 * 
	 * @return the {@link GroeneTruiUitslag} with the given stage number and position.
	 * 
	 */
	public GroeneTruiUitslag loadGroeneTruiUitslag(int etappeNummer, int positieNummer) {
		return getJpaTemplate().find(GroeneTruiUitslag.class, new UitslagPk(etappeNummer, positieNummer));
	}

	/**
	 * Saves or updates the given {@link GroeneTruiUitslag} in the database.
	 */
	@Transactional
	public void saveGroeneTruiUitslag(GroeneTruiUitslag saveGroeneTruiUitslag) {
		getJpaTemplate().merge(saveGroeneTruiUitslag);
	}

}
