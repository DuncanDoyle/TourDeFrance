package nl.doyle.mccloud.tourdefrance.dto;

public class DeelnemerBedragDto extends PersoonDto {
	
	private double gewonnenBedrag;
	
	public DeelnemerBedragDto() {
	}

	/**
	 * @return the gewonnenBedrag
	 */
	public double getGewonnenBedrag() {
		return gewonnenBedrag;
	}

	/**
	 * @param gewonnenBedrag the gewonnenBedrag to set
	 */
	public void setGewonnenBedrag(double gewonnenBedrag) {
		this.gewonnenBedrag = gewonnenBedrag;
	}
	
}
