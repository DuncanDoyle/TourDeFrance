package nl.doyle.mccloud.tourdefrance.dto;

public abstract class PersoonDto {

	private int nummer;
	private String voornaam;
	private String achternaam;
	
	public PersoonDto() {
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
	public void setAchternaam(final String achternaam) {
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
	public void setNummer(final int nummer) {
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
	public void setVoornaam(final String voornaam) {
		this.voornaam = voornaam;
	}
	
	
	
	
}
