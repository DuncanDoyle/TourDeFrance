package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Set;

public class StandaardEtappe extends Etappe {
	
	/**
	 * Set of uitslagen.
	 */
	private Set<EtappeUitslag> etappeUitslag;
	
	
	public int getPositieInEtappeUitslag(Renner renner) {
		return getPositieInUitslag(etappeUitslag, renner);
	}
	
	/**
	 * @author mccloud
	 * 
	 * Default constructor
	 */
	public StandaardEtappe() {
		super();
	}


	public Set<EtappeUitslag> getEtappeUitslag() {
		return etappeUitslag;
	}


	public void setEtappeUitslag(Set<EtappeUitslag> etappeUitslag) {
		this.etappeUitslag = etappeUitslag;
	}
	
	
	

}
