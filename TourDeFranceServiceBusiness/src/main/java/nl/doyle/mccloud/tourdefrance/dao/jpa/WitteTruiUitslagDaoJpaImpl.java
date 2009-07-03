package nl.doyle.mccloud.tourdefrance.dao.jpa;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.WitteTruiUitslagDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagPk;
import nl.doyle.mccloud.tourdefrance.valueobjects.WitteTruiUitslag;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the {@link WitteTruiUitslagDao} interface.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class WitteTruiUitslagDaoJpaImpl extends JpaDaoSupport implements WitteTruiUitslagDao {

	/**
	 * The name of the {@link WitteTruiUitslag} class as a {@link String}.
	 */
	public static final String WITTETRUIUITSLAG_CLASS_NAME = WitteTruiUitslag.class.getName();

	/**
	 * Deletes the given {@link WitteTruiUitslag} from the database.
	 * 
	 * @param delWitteTruiUitslag
	 *            the {@link WitteTruiUitslag} to be removed.
	 */
	@Transactional
	public void deleteWitteTruiUitslag(final WitteTruiUitslag delWitteTruiUitslag) {
		getJpaTemplate().remove(delWitteTruiUitslag);
	}

	/**
	 * Loads all {@link WitteTruiUitslag} entities from the database.
	 * 
	 * 
	 * @return all {@link WitteTruiUitslag} entities stored in the database.
	 */
	@SuppressWarnings("unchecked")
	public List<WitteTruiUitslag> loadAllWitteTruiUitslagen() {
		return getJpaTemplate().find("from " + WITTETRUIUITSLAG_CLASS_NAME);
	}

	/**
	 * Loads the {@link WitteTruiUitslag} with the given stage number and position from the database.
	 * 
	 * @param etappenummer
	 *            the stage number
	 * @param positienummer
	 *            the position
	 * 
	 * @return the {@link WitteTruiUitslag} with the given stage number and position.
	 */
	public WitteTruiUitslag loadWitteTruiUitslag(final int etappenummer, final int positienummer) {
		return getJpaTemplate().find(WitteTruiUitslag.class, new UitslagPk(etappenummer, positienummer));

	}

	/**
	 * Saves or updates the passed {@link WitteTruiUitslag} in the database.
	 * 
	 * @param saveWitteTruiUitslag
	 *            the {@link WitteTruiUitslag} to be saved.
	 */
	@Transactional
	public void saveWitteTruiUitslag(WitteTruiUitslag saveWitteTruiUitslag) {
		getJpaTemplate().merge(saveWitteTruiUitslag);
	}

}
