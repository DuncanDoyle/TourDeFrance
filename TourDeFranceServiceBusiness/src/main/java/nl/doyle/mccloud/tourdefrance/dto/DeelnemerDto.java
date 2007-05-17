package nl.doyle.mccloud.tourdefrance.dto;

import java.util.Set;

import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;

public class DeelnemerDto extends PersoonDto {
	
	private String email;
	private String rekeningnummer;
	private Set<Renner> renners;
	
	public DeelnemerDto() {
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the rekeningnummer
	 */
	public String getRekeningnummer() {
		return rekeningnummer;
	}

	/**
	 * @param rekeningnummer the rekeningnummer to set
	 */
	public void setRekeningnummer(String rekeningnummer) {
		this.rekeningnummer = rekeningnummer;
	}

	/**
	 * @return the renners
	 */
	public Set<Renner> getRenners() {
		return renners;
	}

	/**
	 * @param renners the renners to set
	 */
	public void setRenners(Set<Renner> renners) {
		this.renners = renners;
	}
	
	
}
