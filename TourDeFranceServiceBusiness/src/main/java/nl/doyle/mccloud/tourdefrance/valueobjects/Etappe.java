package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public abstract class Etappe {

	
	private static final Logger logger = Logger.getLogger(Etappe.class);
	
	private int etappenummer;
	private Date datum;
	private Stad startplaats;
	private Stad finishplaats;
	private Set<GeleTruiUitslag> geleTruiUitslag;
	private Set<GroeneTruiUitslag> groeneTruiUitslag;
	private Set<BolletjesTruiUitslag> bolletjesTruiUitslag;
	
	/**
	 * @author mccloud
	 * 
	 * Default constructor
	 *
	 */
	public Etappe(){
		
	}
	
	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getEtappenummer() {
		return etappenummer;
	}

	public void setEtappenummer(int etappenummer) {
		this.etappenummer = etappenummer;
	}
	
	public Set<BolletjesTruiUitslag> getBolletjesTruiUitslag() {
		return bolletjesTruiUitslag;
	}

	public void setBolletjesTruiUitslag(
			Set<BolletjesTruiUitslag> bolletjesTruiUitslag) {
		this.bolletjesTruiUitslag = bolletjesTruiUitslag;
	}

	public Set<GeleTruiUitslag> getGeleTruiUitslag() {
		return geleTruiUitslag;
	}

	public void setGeleTruiUitslag(Set<GeleTruiUitslag> geleTruiUitslag) {
		this.geleTruiUitslag = geleTruiUitslag;
	}

	public Set<GroeneTruiUitslag> getGroeneTruiUitslag() {
		return groeneTruiUitslag;
	}

	public void setGroeneTruiUitslag(Set<GroeneTruiUitslag> groeneTruiUitslag) {
		this.groeneTruiUitslag = groeneTruiUitslag;
	}

	/**
	 * @return the finishplaats
	 */
	public Stad getFinishplaats() {
		return finishplaats;
	}

	/**
	 * @param finishplaats the finishplaats to set
	 */
	public void setFinishplaats(Stad finishplaats) {
		this.finishplaats = finishplaats;
	}

	/**
	 * @return the startplaats
	 */
	public Stad getStartplaats() {
		return startplaats;
	}

	/**
	 * @param startplaats the startplaats to set
	 */
	public void setStartplaats(Stad startplaats) {
		this.startplaats = startplaats;
	}

}
