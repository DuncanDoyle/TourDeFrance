package nl.doyle.mccloud.tourdefrance.dto;

import java.util.Set;

public class EindUitslagDto extends AbstractEtappeAndEindUitslagDto {

	private Set<UitslagDto> eersteUitvaller;
	private Set<UitslagDto> rodeLantaren;
	private Set<UitslagDto> witteTrui;

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

	public Set<UitslagDto> getWitteTrui() {
		return witteTrui;
	}

	public void setWitteTrui(Set<UitslagDto> witteTrui) {
		this.witteTrui = witteTrui;
	}

}
