package nl.doyle.mccloud.tourdefrance.valueobjects;

import org.apache.commons.lang.builder.ToStringBuilder;

public final class Stad {
	
	private int id;
	private String stad;
	private String land;
	private double latitude;
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
