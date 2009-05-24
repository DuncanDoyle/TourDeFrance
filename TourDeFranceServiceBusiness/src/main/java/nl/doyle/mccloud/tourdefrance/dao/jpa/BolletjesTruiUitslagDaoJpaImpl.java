package nl.doyle.mccloud.tourdefrance.dao.jpa;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.BolletjesTruiUitslagDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.BolletjesTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagPk;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the {@link BolletjesTruiUitslagDao} interface.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class BolletjesTruiUitslagDaoJpaImpl extends JpaDaoSupport implements BolletjesTruiUitslagDao {

	/**
	 * The name of the {@link BolletjesTruiUitslag} class as a {@link String}.
	 */
	public static final String BOLLETJESTRUIUITSLAG_CLASS_NAME = BolletjesTruiUitslag.class.getName();

	/**
	 * Deletes the given {@link BolletjesTruiUitslag} from the database.
	 * 
	 * @param delBolletjesTruiUitslag
	 *            the {@link BolletjesTruiUitslag} to be removed.
	 */
	@Transactional
	public void deleteBolletjesTruiUitslag(final BolletjesTruiUitslag delBolletjesTruiUitslag) {
		getJpaTemplate().remove(delBolletjesTruiUitslag);
	}

	/**
	 * Loads all {@link BolletjesTruiUitslag} entities from the database.
	 * 
	 * 
	 * @return all {@link BolletjesTruiUitslag} entities stored in the database.
	 */
	@SuppressWarnings("unchecked")
	public List<BolletjesTruiUitslag> loadAllBolletjesTruiUitslagen() {
		return getJpaTemplate().find("from " + BOLLETJESTRUIUITSLAG_CLASS_NAME);
	}

	/**
	 * Loads the {@link BolletjesTruiUitslag} with the given stage number and position from the database.
	 * 
	 * @param etappenummer
	 *            the stage number
	 * @param positienummer
	 *            the position
	 * 
	 * @return the {@link BolletjesTruiUitslag} with the given stage number and position.
	 */
	public BolletjesTruiUitslag loadBolletjesTruiUitslag(final int etappenummer, final int positienummer) {
		return getJpaTemplate().find(BolletjesTruiUitslag.class, new UitslagPk(etappenummer, positienummer));

	}

	/**
	 * Saves or updates the passed {@link BolletjesTruiUitslag} in the database.
	 * 
	 * @param saveBolletjesTruiUitslag
	 *            the {@link BolletjesTruiUitslag} to be saved.
	 */
	@Transactional
	public void saveBolletjesTruiUitslag(BolletjesTruiUitslag saveBolletjesTruiUitslag) {
		getJpaTemplate().merge(saveBolletjesTruiUitslag);
	}

}
