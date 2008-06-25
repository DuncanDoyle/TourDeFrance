package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Deelnemer extends Persoon {
	
	private String email;
	private String rekeningnummer;
	private Set<Renner> renners;
	
	/**
	 * @author mccloud
	 * 
	 * Default constructor
	 */
	public Deelnemer() {
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRekeningnummer() {
		return rekeningnummer;
	}

	public void setRekeningnummer(String rekeningnummer) {
		this.rekeningnummer = rekeningnummer;
	}


	public Set<Renner> getRenners() {
		return renners;
	}

	public void setRenners(Set<Renner> renners) {
		this.renners = renners;
	}
	
	public String toString() {
		return new ToStringBuilder(this).append("Deelnemernummer", this.getNummer()).
				append("Voornaam", this.getVoornaam()).
				append("Achternaam", this.getAchternaam()).
				append("E-mail", this.getEmail()).
				append("Rekeningnummer", this.getRekeningnummer()).
				toString();
	}
		
	/* (non-Javadoc)
	 * @see nl.doyle.mccloud.tourdefrance.valueobjects.Persoon#hashCode()
	 */
	@Override
	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
		// ideally different for each class
		return new HashCodeBuilder(11,181)
					.appendSuper(super.hashCode())
					.toHashCode();
	}
}
