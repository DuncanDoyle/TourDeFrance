package nl.doyle.mccloud.tourdefrance.valueobjects;

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
	

}
