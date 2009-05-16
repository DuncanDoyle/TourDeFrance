package nl.doyle.mccloud.tourdefrance.valueobjects;

import nl.doyle.mccloud.tourdefrance.valueobjects.visitor.ValueObjectVisitor;


public class EindUitslag extends AbstractEtappeAndEindUitslag {
	
	private Renner eersteUitvaller;
	private Renner rodeLantaren;
	private Renner witteTrui;
	
	public EindUitslag() {
	}

	
	/**
	 * Accepts a {@link ValueObjectVisitor} and calls its {@link ValueObjectVisitor#visit(EindUitslag)} method.
	 * <p>
	 * See the Visitor pattern.
	 * 
	 * @param visitor
	 *            the accepted visitor.
	 */
	@Override
	public void accept(final ValueObjectVisitor visitor) {
		visitor.visit(this);
	}
	
	public int getPositieInRodeLantarenUitslag(final Renner renner) {
		int positie = 0;
		if (renner.equals(rodeLantaren)) {
			positie = 1;
		}
		return positie;
	}
	
	public int getPositieInWitteTruiUitslag(final Renner renner) {
		int positie = 0;
		if (renner.equals(witteTrui)) {
			positie = 1;
		}
		return positie;
	}
	
	public int getPositieInEersteUitvallerUitslag(final Renner renner) {
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
	public void setEersteUitvaller(final Renner eersteUitvaller) {
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
	public void setRodeLantaren(final Renner rodeLantaren) {
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
	public void setWitteTrui(final Renner witteTrui) {
		this.witteTrui = witteTrui;
	}
	
	
	
	
	
}
