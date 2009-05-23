package nl.doyle.mccloud.tourdefrance.valueobjects;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Defines the stage ranking. Defines the racer's position in the stage ranking for the specified stage.
 * 
 * @see Uitslag
 * @author Duncan Doyle
 * @since 0.1
 */
@Entity
@Table(name="TOUR.ETAPPEUITSLAG")
public class EtappeUitslag extends Uitslag {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

}
