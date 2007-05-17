package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;

public abstract class Uitslag implements Serializable {

	private static final Logger logger = Logger.getLogger(Uitslag.class);
	
	private int etappenummer;
	private int positie;
	private Renner renner;
		
	
	/**
	 * @author mccloud
	 * 
	 * Default constructor
	 */
	public Uitslag() {
	}


	public int getPositie() {
		return positie;
	}


	public void setPositie(int positie) {
		this.positie = positie;
	}


	public Renner getRenner() {
		return renner;
	}


	public void setRenner(Renner renner) {
		this.renner = renner;
	}


	public int getEtappenummer() {
		return etappenummer;
	}


	public void setEtappenummer(int etappenummer) {
		this.etappenummer = etappenummer;
	}

	public String toString() {
		return new ToStringBuilder(this).append("etappenummer", this.getEtappenummer())
										.append("positie", this.getPositie())
										.append("renner", this.getRenner())
										.toString();
	}
	

	
}
