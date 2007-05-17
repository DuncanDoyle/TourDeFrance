package nl.doyle.mccloud.tourdefrance.web.spring.command;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EtappeUitslagCommand {

	private static Log logger = LogFactory.getLog(EtappeUitslagCommand.class);
	
	private List<Renner> renners;
	
	private Etappe etappe;
	
	public EtappeUitslagCommand() {
	}

	/**
	 * @return the etappe
	 */
	public Etappe getEtappe() {
		return etappe;
	}

	/**
	 * @param etappe the etappe to set
	 */
	public void setEtappe(Etappe etappe) {
		this.etappe = etappe;
	}

	/**
	 * @return the renners
	 */
	public List<Renner> getRenners() {
		return renners;
	}

	/**
	 * @param renners the renners to set
	 */
	public void setRenners(List<Renner> renners) {
		this.renners = renners;
	}
	
	
	
	
	
	
}
