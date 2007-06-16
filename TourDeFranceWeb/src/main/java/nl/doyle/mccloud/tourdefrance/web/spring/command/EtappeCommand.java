package nl.doyle.mccloud.tourdefrance.web.spring.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EtappeCommand {

	private static final Log logger = LogFactory.getLog(EtappeCommand.class);
	
	private int etappenummer;
	private String omschrijving;
	private List<Stad> steden;
	private int startPlaatsIndex;
	private int finishPlaatsIndex;
	private Date datum;
	
	/**
	 * Default Constructor
	 */
	public EtappeCommand() {
	}

	/**
	 * @return the datum
	 */
	public Date getDatum() {
		return datum;
	}

	/**
	 * Deze setter accepteert een String als input omdat dat uit het form komt.
	 * 
	 * @param datum the datum to set
	 */
	public void setDatum(Date datum) {
		//TODO Door deze constructie is dit geen JavaBean meer. Oplossing zoeken (misschien het form een Datum object laten aanmaken of in dit object Datum als string representeren)
		this.datum = datum;
	}

	/**
	 * @return the etappeNummer
	 */
	public int getEtappenummer() {
		return etappenummer;
	}

	/**
	 * @param etappeNummer the etappeNummer to set
	 */
	public void setEtappenummer(int etappenummer) {
		this.etappenummer = etappenummer;
	}

	/**
	 * @return the finishPlaatsIndex
	 */
	public int getFinishPlaatsIndex() {
		return finishPlaatsIndex;
	}

	/**
	 * @param finishPlaatsIndex the finishPlaatsIndex to set
	 */
	public void setFinishPlaatsIndex(int finishPlaatsIndex) {
		this.finishPlaatsIndex = finishPlaatsIndex;
	}

	/**
	 * @return the startPlaatsIndex
	 */
	public int getStartPlaatsIndex() {
		return startPlaatsIndex;
	}

	/**
	 * @param startPlaatsIndex the startPlaatsIndex to set
	 */
	public void setStartPlaatsIndex(int startPlaatsIndex) {
		this.startPlaatsIndex = startPlaatsIndex;
	}

	/**
	 * @return the steden
	 */
	public List<Stad> getSteden() {
		return steden;
	}

	/**
	 * @param steden the steden to set
	 */
	public void setSteden(List<Stad> steden) {
		this.steden = steden;
	}

	/**
	 * @return the omschrijving
	 */
	public String getOmschrijving() {
		return omschrijving;
	}

	/**
	 * @param omschrijving the omschrijving to set
	 */
	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	
	
}
