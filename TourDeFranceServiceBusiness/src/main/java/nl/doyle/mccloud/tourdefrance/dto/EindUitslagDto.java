package nl.doyle.mccloud.tourdefrance.dto;

import java.util.Set;

public class EindUitslagDto extends AbstractEtappeAndEindUitslagDto {

	private Set<UitslagDto> eersteUitvaller;
	private Set<UitslagDto> rodeLantaren;
	private Set<UitslagDto> mostCombativeResult;

	public EindUitslagDto() {
	}

	public Set<UitslagDto> getEersteUitvaller() {
		return eersteUitvaller;
	}

	public void setEersteUitvaller(Set<UitslagDto> eersteUitvaller) {
		this.eersteUitvaller = eersteUitvaller;
	}

	public Set<UitslagDto> getRodeLantaren() {
		return rodeLantaren;
	}

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
