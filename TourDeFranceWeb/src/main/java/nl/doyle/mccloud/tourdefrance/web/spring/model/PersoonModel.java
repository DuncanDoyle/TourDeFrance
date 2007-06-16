package nl.doyle.mccloud.tourdefrance.web.spring.model;

public abstract class PersoonModel {
	private int nummer;
	private String voornaam;
	private String achternaam;

	public PersoonModel() {
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
