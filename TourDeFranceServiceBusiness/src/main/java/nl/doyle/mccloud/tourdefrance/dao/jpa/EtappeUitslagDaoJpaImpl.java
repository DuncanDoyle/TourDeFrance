package nl.doyle.mccloud.tourdefrance.dao.jpa;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.EtappeUitslagDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.EtappeUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagPk;

import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 * JPA implementation of the {@link EtappeUitslagDao} interface.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class EtappeUitslagDaoJpaImpl extends JpaDaoSupport implements EtappeUitslagDao {

	/**
	 * The name of the {@link EtappeUitslag} class as a {@link String}.
	 */
	private static final String ETAPPEUITSLAG_CLASS_NAME = EtappeUitslag.class.getName();

	/**
	 * Removes the given {@link EtappeUitslag} from the database.
	 * 
	 * @param delEtappeUitslag
	 *            the {@link EtappeUitslag} to be removed.
	 */
	public void deleteEtappeUitslag(final EtappeUitslag delEtappeUitslag) {
		getJpaTemplate().remove(delEtappeUitslag);
	}

	/**
	 * Loads all the {@link EtappeUitslag} entities from the database.
	 * 
	 * @return a {@link List} containing all {@link EtappeUitslag} entities.
	 */
	@SuppressWarnings("unchecked")
	public List<EtappeUitslag> loadAllEtappeUitslagen() {
		return getJpaTemplate().find("from " + ETAPPEUITSLAG_CLASS_NAME);
	}

	/**
	 * Loads the {@link EtappeUitslag} with the given stage number and position from the database.
	 * 
	 * @param etappeNummer
	 *            the stage number.
	 * @param positie
	 *            the position.
	 * @return the {@link EtappeUitslag} with the given stage number and position.
	 */
	public EtappeUitslag loadDEtappeUitslag(final int etappeNummer, final int positie) {
		return getJpaTemplate().find(EtappeUitslag.class, new UitslagPk(etappeNummer, positie));
	}

	/**
	 * Saves or updates the given {@link EtappeUitslag} in the database.
	 * 
	 * @param saveEtappeUitslag
	 *            the {@link EtappeUitslag} to be saved.
	 */
	public void saveEtappeUitslag(EtappeUitslag saveEtappeUitslag) {
		getJpaTemplate().merge(saveEtappeUitslag);
	}

}
