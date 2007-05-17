package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Set;

import org.apache.log4j.Logger;

public class Team {
	
	private static Logger logger = Logger.getLogger(Team.class);
	
	private int nummer;
	private String naam;
	private Set<Renner> renners;
	
	/**
	 * @author mccloud
	 * 
	 * Default constructor
	 *
	 */
	public Team() {
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Set<Renner> getRenners() {
		return renners;
	}

	public void setRenners(Set<Renner> renners) {
		this.renners = renners;
	}
	
	
	
	

}
