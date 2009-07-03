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
	public final String getAchternaam() {
		return achternaam;
	}

	/**
	 * @param achternaam the achternaam to set
	 */
	public final void setAchternaam(final String achternaam) {
		this.achternaam = achternaam;
	}

	/**
	 * @return the nummer
	 */
	public final int getNummer() {
		return nummer;
	}

	/**
	 * @param nummer the nummer to set
	 */
	public final void setNummer(final int nummer) {
		this.nummer = nummer;
	}

	/**
	 * @return the voornaam
	 */
	public final String getVoornaam() {
		return voornaam;
	}

	/**
	 * @param voornaam the voornaam to set
	 */
	public final void setVoornaam(final String voornaam) {
		this.voornaam = voornaam;
	}
	
	
	
}
