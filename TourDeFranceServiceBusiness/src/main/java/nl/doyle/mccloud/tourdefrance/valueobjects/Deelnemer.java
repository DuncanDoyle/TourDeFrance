package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * A participant of the Tour de France Manager game.
 * 
 * @author Duncan Doyle
 * @since 0.1
 */
@Entity
@Table(name="DEELNEMER")
public class Deelnemer extends Persoon {
	
	/**
	 * The particpant's email address.
	 */
	@Column(name="EMAIL")
	private String email;
	
	/**
	 * The participant's bankaccountnumber.
	 */
	@Column(name="REKENINGNUMMER")
	private String rekeningnummer;
	
	//TODO Maps this to a set of cyclists.
	@OneToMany
	@JoinTable(name="DEELNEMER_RENNER", 
			joinColumns = 
				@JoinColumn(name="DEELNEMERNUMMER", referencedColumnName="NUMMER"),
			inverseJoinColumns =
				@JoinColumn(name="RENNERNUMMER", referencedColumnName="NUMMER")
	)
	private Set<Renner> renners;
	
	/**
	 * @author mccloud
	 * 
	 * Default constructor
	 */
	public Deelnemer() {
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getRekeningnummer() {
		return rekeningnummer;
	}

	public void setRekeningnummer(final String rekeningnummer) {
		this.rekeningnummer = rekeningnummer;
	}

	public Set<Renner> getRenners() {
		return renners;
	}

	public void setRenners(final Set<Renner> renners) {
		this.renners = renners;
	}
	
	public String toString() {
		return new ToStringBuilder(this).append("Deelnemernummer", this.getNummer()).
				append("Voornaam", this.getVoornaam()).
				append("Achternaam", this.getAchternaam()).
				append("E-mail", this.getEmail()).
				append("Rekeningnummer", this.getRekeningnummer()).
				toString();
	}
		
	/* (non-Javadoc)
	 * @see nl.doyle.mccloud.tourdefrance.valueobjects.Persoon#hashCode()
	 */
	@Override
	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
		// ideally different for each class
		return new HashCodeBuilder(11, 181)
					.appendSuper(super.hashCode())
					.toHashCode();
	}
}
