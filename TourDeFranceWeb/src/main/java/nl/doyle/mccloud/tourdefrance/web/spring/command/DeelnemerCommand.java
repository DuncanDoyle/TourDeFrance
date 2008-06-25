package nl.doyle.mccloud.tourdefrance.web.spring.command;


public class DeelnemerCommand {
	
	/**
	 * Deelnemer number.
	 */
	private int nummer;
	
	/**
	 * Deelnemer firstname.
	 */
	private String voornaam;
	
	/**
	 * Deelnemer surname.
	 */
	private String achternaam;
	
	/**
	 * Deelnemer e-mail.
	 */
	private String email;
	
	/**
	 * Deelnemer accountnumber.
	 */
	private String rekeningnummer;
	
	/**
	 * Default Constructor
	 */
	public DeelnemerCommand(){
	}
	
	/**
	 * @return the achternaam
	 */
	public String getAchternaam() {
		return achternaam;
	}
	/**
	 * @param achternaam the achternaam to set
	 */
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
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
	 * @return the nummer
	 */
	public int getNummer() {
		return nummer;
	}
	/**
	 * @param nummer the nummer to set
	 */
	public void setNummer(int nummer) {
		this.nummer = nummer;
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
	 * @return the voornaam
	 */
	public String getVoornaam() {
		return voornaam;
	}
	/**
	 * @param voornaam the voornaam to set
	 */
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	
	
	
}
