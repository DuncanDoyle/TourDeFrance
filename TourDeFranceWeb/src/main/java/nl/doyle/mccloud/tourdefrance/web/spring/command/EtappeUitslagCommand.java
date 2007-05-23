package nl.doyle.mccloud.tourdefrance.web.spring.command;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EtappeUitslagCommand {

	private static Log logger = LogFactory.getLog(EtappeUitslagCommand.class);
	
	private List<Renner> renners;
	
	private UitslagCommand uitslag;
	private GeleTruiUitslagCommand geleTruiUitslag;
	private GroeneTruiUitslagCommand groeneTruiUitslag;
	private BolletjesTruiUitslagCommand bolletjesTruiUitslag;
	
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

	/**
	 * @return the bolletjesTruiUitslag
	 */
	public BolletjesTruiUitslagCommand getBolletjesTruiUitslag() {
		return bolletjesTruiUitslag;
	}

	/**
	 * @param bolletjesTruiUitslag the bolletjesTruiUitslag to set
	 */
	public void setBolletjesTruiUitslag(
			BolletjesTruiUitslagCommand bolletjesTruiUitslag) {
		this.bolletjesTruiUitslag = bolletjesTruiUitslag;
	}

	/**
	 * @return the geleTruiUitslag
	 */
	public GeleTruiUitslagCommand getGeleTruiUitslag() {
		return geleTruiUitslag;
	}

	/**
	 * @param geleTruiUitslag the geleTruiUitslag to set
	 */
	public void setGeleTruiUitslag(GeleTruiUitslagCommand geleTruiUitslag) {
		this.geleTruiUitslag = geleTruiUitslag;
	}

	/**
	 * @return the groeneTruiUitslag
	 */
	public GroeneTruiUitslagCommand getGroeneTruiUitslag() {
		return groeneTruiUitslag;
	}

	/**
	 * @param groeneTruiUitslag the groeneTruiUitslag to set
	 */
	public void setGroeneTruiUitslag(GroeneTruiUitslagCommand groeneTruiUitslag) {
		this.groeneTruiUitslag = groeneTruiUitslag;
	}

	/**
	 * @return the uitslag
	 */
	public UitslagCommand getUitslag() {
		return uitslag;
	}

	/**
	 * @param uitslag the uitslag to set
	 */
	public void setUitslag(UitslagCommand uitslag) {
		this.uitslag = uitslag;
	}
	
	
	
	
}
