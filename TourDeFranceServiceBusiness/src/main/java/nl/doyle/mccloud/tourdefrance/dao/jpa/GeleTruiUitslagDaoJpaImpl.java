package nl.doyle.mccloud.tourdefrance.dao.jpa;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.GeleTruiUitslagDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.GeleTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagPk;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the {@link GeleTruiUitslagDao} interface.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class GeleTruiUitslagDaoJpaImpl extends JpaDaoSupport implements GeleTruiUitslagDao {

	/**
	 * The name of the {@link GeleTruiUitslag} class as a {@link String}.
	 */
	public static final String GELETRUIUITSLAG_CLASS_NAME = GeleTruiUitslag.class.getName();

	/**
	 * Deletes the given {@link GeleTruiUitslag} from the database.
	 * 
	 * @param the
	 *            {@link GeleTruiUitslag} to be deleted.
	 */
	@Transactional
	public void deleteGeleTruiUitslag(final GeleTruiUitslag delGeleTruiUitslag) {
		getJpaTemplate().remove(delGeleTruiUitslag);
	}

	/**
	 * Loads all {@link GeleTruiUitslag} entities from the database.
	 * 
	 * @return a {@link List} containing all {@link GeleTruiUitslag} entities.
	 */
	@SuppressWarnings("unchecked")
	public List<GeleTruiUitslag> loadAllGeleTruiUitslagen() {
		return getJpaTemplate().find("from " + GELETRUIUITSLAG_CLASS_NAME);
	}

	/**
	 * Loads the {@link GeleTruiUitslag} with the given stage number and position from the database.
	 * 
	 * @param etappeNummer
	 *            the stage number
	 * @param position
	 *            the position
	 * 
	 * @return the {@link GeleTruiUitslag} with the given stage number and position.
	 * 
	 */
	public GeleTruiUitslag loadGeleTruiUitslag(final int etappeNummer, final int positieNummer) {
		return getJpaTemplate().find(GeleTruiUitslag.class, new UitslagPk(etappeNummer, positieNummer));
	}

	/**
	 * Saves or updates the given {@link GeleTruiUitslag} in the database.
	 */
	@Transactional
	public void saveGeleTruiUitslag(final GeleTruiUitslag saveGeleTruiUitslag) {
		getJpaTemplate().merge(saveGeleTruiUitslag);
	}

}
