package nl.doyle.mccloud.tourdefrance.web.spring.model;

import nl.doyle.mccloud.tourdefrance.dto.DeelnemerDto;

public class DeelnemerAndBedragModel extends DeelnemerModel {

	private double gewonnenBedrag;
	private DeelnemerModel deelnemer;
	
	public DeelnemerAndBedragModel() {
		
	}

	/**
	 * @return the deelnemer
	 */
	public DeelnemerModel getDeelnemer() {
		return deelnemer;
	}

	/**
	 * @param deelnemer the deelnemer to set
	 */
	public void setDeelnemer(DeelnemerModel deelnemer) {
		this.deelnemer = deelnemer;
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
