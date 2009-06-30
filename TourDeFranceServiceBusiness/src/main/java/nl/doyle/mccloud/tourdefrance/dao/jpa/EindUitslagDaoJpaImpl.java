package nl.doyle.mccloud.tourdefrance.dao.jpa;

import nl.doyle.mccloud.tourdefrance.dao.EindUitslagDao;
import nl.doyle.mccloud.tourdefrance.util.JpaUtil;
import nl.doyle.mccloud.tourdefrance.valueobjects.EindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the {@link EindUitslagDao} interface.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class EindUitslagDaoJpaImpl extends JpaDaoSupport implements EindUitslagDao {

	/**
	 * Retrieves the {@link EindUitslag} from the database.
	 * 
	 * @return the {@link EindUitslag}.
	 */
	public EindUitslag loadEindUitslag() {
		return getJpaTemplate().find(EindUitslag.class, 0);
	}

	/**
	 * Eagerly retrieves the {@link EindUitslag} from the database.
	 * 
	 * @return the {@link EindUitslag}.
	 */
	@Transactional
	public EindUitslag loadEindUitslagWithUitslagEager() {
		// TODO Auto-generated method stub
		EindUitslag uitslag = loadEindUitslag();
		// Als er geen etappe is gevonden krijgen we null terug
		if (uitslag != null) {
			// Initialize eagerly.
			Renner loadRenner;
			loadRenner = uitslag.getEersteUitvaller();
			if (loadRenner != null) {
				loadRenner.getNummer();
			}
			loadRenner = uitslag.getRodeLantaren();
			if (loadRenner != null) {
				loadRenner.getNummer();
			}
			loadRenner = uitslag.getMostCombativeRacer();
			if (loadRenner != null) {
				loadRenner.getNummer();
			}
			
			JpaUtil.initialize(uitslag.getBolletjesTruiUitslag());
			JpaUtil.initialize(uitslag.getGeleTruiUitslag());
			JpaUtil.initialize(uitslag.getGroeneTruiUitslag());
			JpaUtil.initialize(uitslag.getWitteTruiUitslag());
		}
		return uitslag;
	}

	/**
	 * Saves or updates the given {@link EindUitslag} in the database.
	 * 
	 * @param saveEindUitslag
	 *            the {@link EindUitslag} to be saved.
	 */
	@Transactional
	public void saveEindUitslag(EindUitslag saveEindUitslag) {
		getJpaTemplate().merge(saveEindUitslag);
	}

}
