package nl.doyle.mccloud.tourdefrance.web.spring.command;

import java.util.Date;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;

public class EtappeCommand {

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
	public void setDatum(final Date datum) {
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
	public void setEtappenummer(final int etappenummer) {
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
	public void setFinishPlaatsIndex(final int finishPlaatsIndex) {
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
	public void setStartPlaatsIndex(final int startPlaatsIndex) {
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
	public void setSteden(final List<Stad> steden) {
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
	public void setOmschrijving(final String omschrijving) {
		this.omschrijving = omschrijving;
	}
	
	
}
