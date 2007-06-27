package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.util.Set;

public class EindUitslag extends AbstractEtappeAndEindUitslag {
	
	private Renner eersteUitvaller;
	private Renner rodeLantaren;
	private Renner witteTrui;
	
	public EindUitslag() {
	}

	public int getPositieInRodeLantarenUitslag(Renner renner) {
		int positie = 0;
		if (renner.equals(rodeLantaren)) {
			positie = 1;
		}
		return positie;
	}
	
	public int getPositieInWitteTruiUitslag(Renner renner) {
		int positie = 0;
		if (renner.equals(witteTrui)) {
			positie = 1;
		}
		return positie;
	}
	
	public int getPositieInEersteUitvallerUitslag(Renner renner) {
		int positie = 0;
		if (renner.equals(eersteUitvaller)) {
			positie = 1;
		}
		return positie;
	}
	
	
	/**
	 * @return the eersteUitvaller
	 */
	public Renner getEersteUitvaller() {
		return eersteUitvaller;
	}

	/**
	 * @param eersteUitvaller the eersteUitvaller to set
	 */
	public void setEersteUitvaller(Renner eersteUitvaller) {
		this.eersteUitvaller = eersteUitvaller;
	}

	/**
	 * @return the rodeLantaren
	 */
	public Renner getRodeLantaren() {
		return rodeLantaren;
	}

	/**
	 * @param rodeLantaren the rodeLantaren to set
	 */
	public void setRodeLantaren(Renner rodeLantaren) {
		this.rodeLantaren = rodeLantaren;
	}

	/**
	 * @return the witteTrui
	 */
	public Renner getWitteTrui() {
		return witteTrui;
	}

	/**
	 * @param witteTrui the witteTrui to set
	 */
	public void setWitteTrui(Renner witteTrui) {
		this.witteTrui = witteTrui;
	}
	
	
	
	
	
}
