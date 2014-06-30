package nl.doyle.mccloud.tourdefrance.valueobjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * A city within the Tour de France (e.g. city where a stage starts or finishes).
 * 
 * @author Duncan Doyle
 * @since 0.1
 */
@Entity
@Table(name="TOUR.STAD")
public class Stad {

	/**
	 * The cities ID. Primarily used for storage of this entity.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	/**
	 * The city's name.
	 */
	@Column(name="STAD")
	private String stad;
	
	/**
	 * The city's country.
	 */
	@Column(name="COUNTRY")
	private String land;
	
	/**
	 * The city's latitude.
	 */
	@Column(name="LATITUDE")
	private double latitude;
	
	/**
	 * The city's longitude.
	 */
	@Column(name="Longitude")
	private double longitude;
	
	/**
	 * Default Constructor
	 */
	public Stad() {
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}


	/**
	 * @return the land
	 */
	public String getLand() {
		return land;
	}


	/**
	 * @param land the land to set
	 */
	public void setLand(final String land) {
		this.land = land;
	}


	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}


	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(final double latitude) {
		this.latitude = latitude;
	}


	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}


	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(final double longitude) {
		this.longitude = longitude;
	}


	/**
	 * @return the stad
	 */
	public String getStad() {
		return stad;
	}


	/**
	 * @param stad the stad to set
	 */
	public void setStad(final String stad) {
		this.stad = stad;
	}
	
	public String toString() {
		return new ToStringBuilder(this)
			.append("Land", this.getLand())
			.append("Stad", this.getStad())
			.append("Longitude", this.getLongitude())
			.append("Latitude", this.getLatitude())
			.toString();
	}
	
	
	
	
	
	

}
