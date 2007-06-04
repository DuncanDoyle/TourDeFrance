package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public abstract class Etappe extends AbstractEtappeAndEindUitslag {

	
	private static final Logger logger = Logger.getLogger(Etappe.class);
	
	private Date datum;
	private Stad startplaats;
	private Stad finishplaats;
	
	
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
