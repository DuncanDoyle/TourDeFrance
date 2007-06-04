package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Date;
import java.util.Set;

public abstract class AbstractEtappeAndEindUitslag {

	private int etappenummer;
	private Set<GeleTruiUitslag> geleTruiUitslag;
	private Set<GroeneTruiUitslag> groeneTruiUitslag;
	private Set<BolletjesTruiUitslag> bolletjesTruiUitslag;
	
	
	
	/**
	 * @return the bolletjesTruiUitslag
	 */
	public Set<BolletjesTruiUitslag> getBolletjesTruiUitslag() {
		return bolletjesTruiUitslag;
	}
	/**
	 * @param bolletjesTruiUitslag the bolletjesTruiUitslag to set
	 */
	public void setBolletjesTruiUitslag(
			Set<BolletjesTruiUitslag> bolletjesTruiUitslag) {
		this.bolletjesTruiUitslag = bolletjesTruiUitslag;
	}
	/**
	 * @return the etappenummer
	 */
	public int getEtappenummer() {
		return etappenummer;
	}
	/**
	 * @param etappenummer the etappenummer to set
	 */
	public void setEtappenummer(int etappenummer) {
		this.etappenummer = etappenummer;
	}
	/**
	 * @return the geleTruiUitslag
	 */
	public Set<GeleTruiUitslag> getGeleTruiUitslag() {
		return geleTruiUitslag;
	}
	/**
	 * @param geleTruiUitslag the geleTruiUitslag to set
	 */
	public void setGeleTruiUitslag(Set<GeleTruiUitslag> geleTruiUitslag) {
		this.geleTruiUitslag = geleTruiUitslag;
	}
	/**
	 * @return the groeneTruiUitslag
	 */
	public Set<GroeneTruiUitslag> getGroeneTruiUitslag() {
		return groeneTruiUitslag;
	}
	/**
	 * @param groeneTruiUitslag the groeneTruiUitslag to set
	 */
	public void setGroeneTruiUitslag(Set<GroeneTruiUitslag> groeneTruiUitslag) {
		this.groeneTruiUitslag = groeneTruiUitslag;
	}
	
}
