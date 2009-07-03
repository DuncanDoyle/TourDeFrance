package nl.doyle.mccloud.tourdefrance.valueobjects;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * The cyclist which competes in the Tour de France.
 * 
 * 
 * @author Duncan Doyle
 * @since v0.1
 */
@Entity
@Table(name="TOUR.RENNER")
public class Renner extends Persoon {
	
	/**
	 * Default constructor.
	 */
	public Renner() {
	}

	/* (non-Javadoc)
	 * @see nl.doyle.mccloud.tourdefrance.valueobjects.Persoon#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		boolean equals = false;
		
		if (obj instanceof Renner) {
		   if (this == obj) {
		     equals = true;
		   } else {
			   equals = new EqualsBuilder()
			   				.appendSuper(super.equals(obj))
			   				.isEquals();
		   }
		}
		return equals;
	}
	/* (non-Javadoc)
	 * @see nl.doyle.mccloud.tourdefrance.valueobjects.Persoon#hashCode()
	 */
	@Override
	public int hashCode() {
		
		// you pick a hard-coded, randomly chosen, non-zero, odd number
		// ideally different for each class
		return new HashCodeBuilder(19, 83)
					.appendSuper(super.hashCode())
					.toHashCode();
	}
	
}
