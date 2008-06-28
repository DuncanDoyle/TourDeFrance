package nl.doyle.mccloud.tourdefrance.web.spring.model;

public class DeelnemerModel extends PersoonModel {

	private String email;
	private String rekeningnummer;
	
	public DeelnemerModel() {
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
	
	
}
