package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TOUR.TEAM")
public class Team {
	
	/**
	 * The team number.
	 */
	@Id
	@Column(name="NUMMER")
	private int nummer;
	
	/**
	 * The team name.
	 */
	@Column(name="NAAM")
	private String naam;
	
	/**
	 * The team's renners.
	 */
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="TEAM_RENNER", 
			joinColumns = 
				@JoinColumn(name="TEAMNUMMER", referencedColumnName="NUMMER"),
			inverseJoinColumns =
				@JoinColumn(name="RENNERNUMMER", referencedColumnName="NUMMER")
	)
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
