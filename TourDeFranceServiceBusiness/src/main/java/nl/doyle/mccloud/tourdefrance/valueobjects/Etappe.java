package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Date;

public abstract class Etappe extends AbstractEtappeAndEindUitslag {

	/**
	 * The date of the etappe.
	 */
	private Date datum;

	/**
	 * The start city.
	 */
	private Stad startplaats;

	/**
	 * The finish city.
	 */
	private Stad finishplaats;

	/**
	 * Default constructor
	 */
	public Etappe() {

	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	/**
	 * @return the finishplaats
	 */
	public Stad getFinishplaats() {
		return finishplaats;
	}

	/**
	 * @param finishplaats
	 *            the finishplaats to set
	 */
	public void setFinishplaats(Stad finishplaats) {
		this.finishplaats = finishplaats;
	}

	/**
	 * @return the startplaats
	 */
	public Stad getStartplaats() {
		return startplaats;
	}

	/**
	 * @param startplaats
	 *            the startplaats to set
	 */
	public void setStartplaats(Stad startplaats) {
		this.startplaats = startplaats;
	}

}
