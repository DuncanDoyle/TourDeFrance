package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Set;

public class Team {
	
	/**
	 * The team number.
	 */
	private int nummer;
	
	/**
	 * The team name.
	 */
	private String naam;
	
	/**
	 * The team's renners.
	 */
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

	public void setNummer(final int nummer) {
		this.nummer = nummer;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(final String naam) {
		this.naam = naam;
	}

	public Set<Renner> getRenners() {
		return renners;
	}

	public void setRenners(final Set<Renner> renners) {
		this.renners = renners;
	}
	
	
	
	

}
