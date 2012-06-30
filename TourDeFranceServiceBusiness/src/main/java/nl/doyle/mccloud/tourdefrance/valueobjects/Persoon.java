package nl.doyle.mccloud.tourdefrance.valueobjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Persoon {

	@Id
	@Column(name = "NUMMER")
	private int nummer;

	@Column(name = "VOORNAAM")
	private String voornaam;

	@Column(name = "ACHTERNAAM")
	private String achternaam;

	public Persoon() {
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(final String achternaam) {
		this.achternaam = achternaam;
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(final int nummer) {
		this.nummer = nummer;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(final String voornaam) {
		this.voornaam = voornaam;
	}

	public String toString() {
		return new ToStringBuilder(this).append("Nummer", this.getNummer()).append("Voornaam", this.getVoornaam()).append("Achternaam",
				this.getAchternaam()).toString();
	}

	/**
	 * Compares the passed object to <code>this</code>. The objects are said to be equal when the {@link #nummer numbers},
	 * {@link #voornaam firstnames) and {@link #achternaam surnames} are equal.
	 * 
	 * @param obj
	 *            The object which will be compared to <code>this</code> object.
	 * @return <code>true</code> when the objects are the same, <code>false</code> otherwise.
	 */
	@Override
	public boolean equals(final Object obj) {
		boolean equals = false;

		if (obj instanceof Persoon) {
			if (this == obj) {
				equals = true;
			} else {
				Persoon equalsPersoon = (Persoon) obj;
				equals = new EqualsBuilder().append(this.getNummer(), equalsPersoon.getNummer()).append(this.getAchternaam(),
						equalsPersoon.getAchternaam()).append(this.getVoornaam(), equalsPersoon.getVoornaam()).isEquals();
			}
		}
		return equals;
	}

	public int hashCode() {
		/* 
		 * you pick a hard-coded, randomly chosen, non-zero, odd number, ideally different for each class.
		 */
		return new HashCodeBuilder(17, 37).append(this.getNummer()).append(this.getVoornaam()).append(this.getAchternaam()).toHashCode();
	}
}
