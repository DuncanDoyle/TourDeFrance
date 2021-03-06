package nl.doyle.mccloud.tourdefrance.dto;

import java.util.Date;

public abstract class EtappeDto extends AbstractEtappeAndEindUitslagDto {

	private Date datum;
	private String startplaats;
	private String finishplaats;
	
	public EtappeDto() {
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(final Date datum) {
		this.datum = datum;
	}

	public String getStartplaats() {
		return startplaats;
	}

	public void setStartplaats(String startplaats) {
		this.startplaats = startplaats;
	}

	public String getFinishplaats() {
		return finishplaats;
	}

	public void setFinishplaats(String finishplaats) {
		this.finishplaats = finishplaats;
	}
	
	
	
	
}
