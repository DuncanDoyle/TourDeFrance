package nl.doyle.mccloud.tourdefrance.valueobjects;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Defines the green jersey ranking. Defines the racer's position in the green jersey ranking after the specified stage. 
 * 
 * @see Uitslag
 * @author Duncan Doyle
 * @since 0.1
 */
@Entity
@Table(name="GROENETRUIUITSLAG")
public class GroeneTruiUitslag extends Uitslag {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

}
