package nl.doyle.mccloud.tourdefrance.dto;

import java.util.Set;

public class StandaardEtappeDto extends EtappeDto {

	private Set<UitslagDto> etappeUitslag;
	private Set<UitslagDto> rodeLantaren;
	private Set<UitslagDto> mostCombativeResult;
	
	public StandaardEtappeDto() {
	}

	public Set<UitslagDto> getEtappeUitslag() {
		return etappeUitslag;
	}

	public void setEtappeUitslag(Set<UitslagDto> etappeUitslag) {
		this.etappeUitslag = etappeUitslag;
	}
	
	/**
	 * @return the rodeLantaren
	 */
	public Set<UitslagDto> getRodeLantaren() {
		return rodeLantaren;
	}
	
	/**
	 * @param rodeLantaren the rodeLantaren to set
	 */
	public void setRodeLantaren(Set<UitslagDto> rodeLantaren) {
		this.rodeLantaren = rodeLantaren;
	}

	/**
	 * @return the mostCombativeResult
	 */
	public Set<UitslagDto> getMostCombativeResult() {
		return mostCombativeResult;
	}

	/**
	 * @param mostCombativeResult the mostCombativeResult to set
	 */
	public void setMostCombativeResult(Set<UitslagDto> mostCombativeResult) {
		this.mostCombativeResult = mostCombativeResult;
	}


}
