package nl.doyle.mccloud.tourdefrance.dto;

import java.util.Set;

public abstract class AbstractEtappeAndEindUitslagDto {

	private int etappenummer;
	private String omschrijving;
	private Set<UitslagDto> geleTruiUitslag;
	private Set<UitslagDto> groeneTruiUitslag;
	private Set<UitslagDto> bolletjesTruiUitslag;
	private Set<UitslagDto> witteTrui;

	
	public AbstractEtappeAndEindUitslagDto() {
	}


	public int getEtappenummer() {
		return etappenummer;
	}


	public void setEtappenummer(int etappenummer) {
		this.etappenummer = etappenummer;
	}


	public String getOmschrijving() {
		return omschrijving;
	}


	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}


	public Set<UitslagDto> getGeleTruiUitslag() {
		return geleTruiUitslag;
	}


	public void setGeleTruiUitslag(Set<UitslagDto> geleTruiUitslag) {
		this.geleTruiUitslag = geleTruiUitslag;
	}


	public Set<UitslagDto> getGroeneTruiUitslag() {
		return groeneTruiUitslag;
	}


	public void setGroeneTruiUitslag(Set<UitslagDto> groeneTruiUitslag) {
		this.groeneTruiUitslag = groeneTruiUitslag;
	}


	public Set<UitslagDto> getBolletjesTruiUitslag() {
		return bolletjesTruiUitslag;
	}


	public void setBolletjesTruiUitslag(Set<UitslagDto> bolletjesTruiUitslag) {
		this.bolletjesTruiUitslag = bolletjesTruiUitslag;
	}

	/**
	 * @return the witteTrui
	 */
	public Set<UitslagDto> getWitteTrui() {
		return witteTrui;
	}


	/**
	 * @param witteTrui the witteTrui to set
	 */
	public void setWitteTrui(Set<UitslagDto> witteTrui) {
		this.witteTrui = witteTrui;
	}

}
