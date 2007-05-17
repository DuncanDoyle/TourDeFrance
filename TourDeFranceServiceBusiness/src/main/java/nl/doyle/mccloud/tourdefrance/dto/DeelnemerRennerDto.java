package nl.doyle.mccloud.tourdefrance.dto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DeelnemerRennerDto {
	
	private static final Log logger = LogFactory.getLog(DeelnemerRennerDto.class);
		
	//Rennergegevens
	private int rennerNummer;
	private String rennerVoornaam;
	private String rennerAchternaam;
	
	//Deelnemergegevens
	private int deelnemerNummer;
	private String deelnemerVoornaam;
	private String deelnemerAchternaam;
	private String deelnemerEmail;
	private String deelnemerRekeningnummer;
	
	/**
	 * Default Constructor
	 */
	public DeelnemerRennerDto(){
	}

	/**
	 * @return the deelnemerAchternaam
	 */
	public String getDeelnemerAchternaam() {
		return deelnemerAchternaam;
	}

	/**
	 * @param deelnemerAchternaam the deelnemerAchternaam to set
	 */
	public void setDeelnemerAchternaam(String deelnemerAchternaam) {
		this.deelnemerAchternaam = deelnemerAchternaam;
	}

	/**
	 * @return the deelnemerEmail
	 */
	public String getDeelnemerEmail() {
		return deelnemerEmail;
	}

	/**
	 * @param deelnemerEmail the deelnemerEmail to set
	 */
	public void setDeelnemerEmail(String deelnemerEmail) {
		this.deelnemerEmail = deelnemerEmail;
	}

	/**
	 * @return the deelnemerNummer
	 */
	public int getDeelnemerNummer() {
		return deelnemerNummer;
	}

	/**
	 * @param deelnemerNummer the deelnemerNummer to set
	 */
	public void setDeelnemerNummer(int deelnemerNummer) {
		this.deelnemerNummer = deelnemerNummer;
	}

	/**
	 * @return the deelnemerRekeningnummer
	 */
	public String getDeelnemerRekeningnummer() {
		return deelnemerRekeningnummer;
	}

	/**
	 * @param deelnemerRekeningnummer the deelnemerRekeningnummer to set
	 */
	public void setDeelnemerRekeningnummer(String deelnemerRekeningnummer) {
		this.deelnemerRekeningnummer = deelnemerRekeningnummer;
	}

	/**
	 * @return the deelnemerVoornaam
	 */
	public String getDeelnemerVoornaam() {
		return deelnemerVoornaam;
	}

	/**
	 * @param deelnemerVoornaam the deelnemerVoornaam to set
	 */
	public void setDeelnemerVoornaam(String deelnemerVoornaam) {
		this.deelnemerVoornaam = deelnemerVoornaam;
	}

	/**
	 * @return the rennerAchternaam
	 */
	public String getRennerAchternaam() {
		return rennerAchternaam;
	}

	/**
	 * @param rennerAchternaam the rennerAchternaam to set
	 */
	public void setRennerAchternaam(String rennerAchternaam) {
		this.rennerAchternaam = rennerAchternaam;
	}

	/**
	 * @return the rennerNummer
	 */
	public int getRennerNummer() {
		return rennerNummer;
	}

	/**
	 * @param rennerNummer the rennerNummer to set
	 */
	public void setRennerNummer(int rennerNummer) {
		this.rennerNummer = rennerNummer;
	}

	/**
	 * @return the rennerVoornaam
	 */
	public String getRennerVoornaam() {
		return rennerVoornaam;
	}

	/**
	 * @param rennerVoornaam the rennerVoornaam to set
	 */
	public void setRennerVoornaam(String rennerVoornaam) {
		this.rennerVoornaam = rennerVoornaam;
	}
	
	public String toString() {
		
		return new ToStringBuilder(this)
			.append("Rennernummer", getRennerNummer())
			.append("RennerVoornaam", getRennerVoornaam())
			.append("RennerAchternaam", getRennerAchternaam())
			.append("DeelnemerNummer", getDeelnemerNummer())
			.append("DeelnemerVoornaam", getDeelnemerVoornaam())
			.append("DeelnemerAchternaam", getDeelnemerAchternaam())
			.append("DeelnemerEmail", getDeelnemerEmail())
			.append("DeelnemerRekeningnummer", getDeelnemerRekeningnummer())
			.toString();
	}
	
	
	
	
	

}
