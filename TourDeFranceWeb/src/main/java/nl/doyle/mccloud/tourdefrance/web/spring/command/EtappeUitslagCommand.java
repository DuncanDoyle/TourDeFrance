package nl.doyle.mccloud.tourdefrance.web.spring.command;

import java.util.Date;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EtappeUitslagCommand {

	private static Log logger = LogFactory.getLog(EtappeUitslagCommand.class);
	
	private List<Renner> renners;
	
	//private UitslagCommand uitslag;
	//private GeleTruiUitslagCommand geleTruiUitslag;
	//private GroeneTruiUitslagCommand groeneTruiUitslag;
	//private BolletjesTruiUitslagCommand bolletjesTruiUitslag;
	private int[] uitslag = new int[15];
	private int[] geleTruiUitslag = new int[5];
	private int[] groeneTruiUitslag = new int[3];
	private int[] bolletjesTruiUitslag = new int[3];
	
	private int etappenummer;
	private Stad startPlaats;
	private Stad finishPlaats;
	private Date datum;
	
	private boolean standaardEtappe;
	
	public EtappeUitslagCommand() {
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
	 * @return the datum
	 */
	public Date getDatum() {
		return datum;
	}

	/**
	 * @param datum the datum to set
	 */
	public void setDatum(Date datum) {
		this.datum = datum;
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
	 * @return the finishPlaats
	 */
	public Stad getFinishPlaats() {
		return finishPlaats;
	}

	/**
	 * @param finishPlaats the finishPlaats to set
	 */
	public void setFinishPlaats(Stad finishPlaats) {
		this.finishPlaats = finishPlaats;
	}

	/**
	 * @return the startPlaats
	 */
	public Stad getStartPlaats() {
		return startPlaats;
	}

	/**
	 * @param startPlaats the startPlaats to set
	 */
	public void setStartPlaats(Stad startPlaats) {
		this.startPlaats = startPlaats;
	}

	/**
	 * @return the isPloegenTijdrit
	 */
	public boolean isStandaardEtappe() {
		return standaardEtappe;
	}

	/**
	 * @param isPloegenTijdrit the isPloegenTijdrit to set
	 */
	public void setStandaardEtappe(boolean isStandaardEtappe) {
		this.standaardEtappe = isStandaardEtappe;
	}

	/**
	 * @return the bolletjesTruiUitslag
	 */
	public int[] getBolletjesTruiUitslag() {
		return bolletjesTruiUitslag;
	}

	/**
	 * @param bolletjesTruiUitslag the bolletjesTruiUitslag to set
	 */
	public void setBolletjesTruiUitslag(int[] bolletjesTruiUitslag) {
		this.bolletjesTruiUitslag = bolletjesTruiUitslag;
	}

	/**
	 * @return the geleTruiUitslag
	 */
	public int[] getGeleTruiUitslag() {
		return geleTruiUitslag;
	}

	/**
	 * @param geleTruiUitslag the geleTruiUitslag to set
	 */
	public void setGeleTruiUitslag(int[] geleTruiUitslag) {
		this.geleTruiUitslag = geleTruiUitslag;
	}

	/**
	 * @return the groenTruiUitslag
	 */
	public int[] getGroeneTruiUitslag() {
		return groeneTruiUitslag;
	}

	/**
	 * @param groenTruiUitslag the groenTruiUitslag to set
	 */
	public void setGroeneTruiUitslag(int[] groeneTruiUitslag) {
		this.groeneTruiUitslag = groeneTruiUitslag;
	}

	/**
	 * @return the uitslag
	 */
	public int[] getUitslag() {
		return uitslag;
	}

	/**
	 * @param uitslag the uitslag to set
	 */
	public void setUitslag(int[] uitslag) {
		this.uitslag = uitslag;
	}
	
	
	
	
}
