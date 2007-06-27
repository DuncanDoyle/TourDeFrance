package nl.doyle.mccloud.tourdefrance.valueobjects;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class Persoon {
	
	private int nummer;
	private String voornaam;
	private String achternaam;
	
	public Persoon() {
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	
	public String toString() {
		return new ToStringBuilder(this).append("Nummer", this.getNummer())
										.append("Voornaam", this.getVoornaam())
										.append("Achternaam", this.getAchternaam()).toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		
		if (obj instanceof Persoon) {
		   if (this == obj) {
		     equals = true;
		   } else {
			   Persoon equalsPersoon = (Persoon) obj;
			   equals = new EqualsBuilder()
			   				.append(this.getNummer(), equalsPersoon.getNummer())
			   				.append(this.getAchternaam(), equalsPersoon.getAchternaam())
			   				.append(this.getVoornaam(), equalsPersoon.getVoornaam())
			   				.isEquals();
		   }
		}
		return equals;
	}
	
	public int hashCode() {
	     // you pick a hard-coded, randomly chosen, non-zero, odd number
	     // ideally different for each class
	     return new HashCodeBuilder(17, 37)
	       			.append(this.getNummer())
	       			.append(this.getVoornaam())
	       			.append(this.getAchternaam())
	       			.toHashCode();
	   }
}
