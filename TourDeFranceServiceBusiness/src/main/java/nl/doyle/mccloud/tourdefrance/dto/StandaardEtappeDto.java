package nl.doyle.mccloud.tourdefrance.dto;

import java.util.Set;

public class StandaardEtappeDto extends EtappeDto {

	private Set<UitslagDto> etappeUitslag;

	public StandaardEtappeDto() {
	}

	public Set<UitslagDto> getEtappeUitslag() {
		return etappeUitslag;
	}

	public void setEtappeUitslag(Set<UitslagDto> etappeUitslag) {
		this.etappeUitslag = etappeUitslag;
	}

}
