package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Set;

import org.apache.log4j.Logger;

public class StandaardEtappe extends Etappe {
	
	private static final Logger logger = Logger.getLogger(StandaardEtappe.class);
	
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
