package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

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
	private static final long serialVersionUID = 1L;

	// TODO Maybe change this implementation. The etappe owns its uitslag object, so maybe this should contain an array of renners, no
	// etappenummer.
	private int etappenummer;
	private int positie;
	private Renner renner;

	/**
	 * @author mccloud
	 * 
	 *         Default constructor
	 */
	public Uitslag() {
	}

	public int getPositie() {
		return positie;
	}

	public void setPositie(int positie) {
		this.positie = positie;
	}

	public Renner getRenner() {
		return renner;
	}

	public void setRenner(Renner renner) {
		this.renner = renner;
	}

	public int getEtappenummer() {
		return etappenummer;
	}

	public void setEtappenummer(int etappenummer) {
		this.etappenummer = etappenummer;
	}

	public String toString() {
		return new ToStringBuilder(this).append("etappenummer", this.getEtappenummer()).append("positie", this.getPositie()).append(
				"renner", this.getRenner()).toString();
	}

}
