package nl.doyle.mccloud.tourdefrance.web.spring.command;

import org.apache.commons.lang.builder.ToStringBuilder;

public class RennerCommand {

	
	private String voornaam;
	private String achternaam;
	private int nummer;
	
	/**
	 * @mccloud
	 * 
	 * Default Constructor
	 * 
	 */
	public RennerCommand() {}

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
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("Rennernummer", this.getNummer()).
		append("Voornaam", this.getVoornaam()).
		append("Achternaam", this.getAchternaam()).
		toString();
	}
	
}
