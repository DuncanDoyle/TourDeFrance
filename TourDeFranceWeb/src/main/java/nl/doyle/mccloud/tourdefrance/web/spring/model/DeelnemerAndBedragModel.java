package nl.doyle.mccloud.tourdefrance.web.spring.model;

import nl.doyle.mccloud.tourdefrance.dto.DeelnemerDto;

public class DeelnemerAndBedragModel extends DeelnemerModel {

	private String gewonnenBedrag;
	
	public DeelnemerAndBedragModel() {
		
	}

	/**
	 * @return the gewonnenBedrag
	 */
	public String getGewonnenBedrag() {
		return gewonnenBedrag;
	}


	/**
	 * @param gewonnenBedrag the gewonnenBedrag to set
	 */
	public void setGewonnenBedrag(String gewonnenBedrag) {
		this.gewonnenBedrag = gewonnenBedrag;
	}
	
	
	
	
}
