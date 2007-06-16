package nl.doyle.mccloud.tourdefrance.dto;

public class DeelnemerBedragDto {
	
	private double gewonnenBedrag;
	private DeelnemerDto deelnemer;
	
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

	/**
	 * @return the deelnemer
	 */
	public DeelnemerDto getDeelnemer() {
		return deelnemer;
	}

	/**
	 * @param deelnemer the deelnemer to set
	 */
	public void setDeelnemer(DeelnemerDto deelnemer) {
		this.deelnemer = deelnemer;
	}
	
	
	
}
