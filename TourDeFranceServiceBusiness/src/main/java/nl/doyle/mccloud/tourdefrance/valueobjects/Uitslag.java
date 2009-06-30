package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Defines the ranking. Defines the racer's position in the ranking for the specified stage. Subclasses define which ranking.
 * 
 * @author Duncan Doyle
 * @since 0.1
 */
public abstract class Uitslag implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 2L;

	/* TODO Maybe change this implementation. The etappe owns its uitslag object, so maybe this should contain an array of renners, no
	 * etappenummer. If we change this, the etappe should contain a result object, not a set of result objects.
	 */

	/**
	 * @author mccloud
	 * 
	 *         Default constructor
	 */
	public Uitslag() {
	}

	public abstract Renner getRenner();

	public abstract void setRenner(final Renner renner);

	public abstract int getPositie();
		
	public abstract void setPositie(final int positie);
		
	public abstract int getEtappenummer();
		
	public abstract void setEtappenummer(final int etappenummer);
		
	
	/*
	public String toString() {
		return new ToStringBuilder(this).append("etappenummer", this.getEtappenummer()).append("positie", this.getPositie()).append(
				"renner", this.getRenner()).toString();
	}
	*/

}
