package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ETAPPE")
public abstract class Etappe extends AbstractEtappeAndEindUitslag {

	/**
	 * The date of the etappe.
	 */
	@Column(name="DATUM")
	private Date datum;

	/**
	 * The start city.
	 */
	@ManyToOne
	@JoinColumn(name="STARTPLAATS")
	private Stad startplaats;

	/**
	 * The finish city.
	 */
	@ManyToOne
	@JoinColumn(name="FINISHPLAATS")
	private Stad finishplaats;

	/**
	 * Default constructor
	 */
	public Etappe() {

	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(final Date datum) {
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
	public void setFinishplaats(final Stad finishplaats) {
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
	public void setStartplaats(final Stad startplaats) {
		this.startplaats = startplaats;
	}

}
