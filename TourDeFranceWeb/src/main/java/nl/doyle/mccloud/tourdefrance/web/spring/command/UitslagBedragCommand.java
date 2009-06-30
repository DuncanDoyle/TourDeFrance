package nl.doyle.mccloud.tourdefrance.web.spring.command;

public class UitslagBedragCommand {

	private double[] etappe;
	private double[] geleTrui;
	private double[] groeneTrui;
	private double[] bolletjesTrui;
	private double[] witteTrui;
	private double[] rodeLantaren;
	private double[] mostCombativeStage;
	private double[] geleTruiEind;
	private double[] groeneTruiEind;
	private double[] bolletjesTruiEind;
	private double[] witteTruiEind;
	private double[] rodeLantarenEind;
	private double[] eersteUitvallerEind;
	private double[] mostCombativeFinal;

	/**
	 * Default constructor
	 */
	public UitslagBedragCommand(final int etappe, final int geleTrui, final int groeneTrui, final int bolletjesTrui, final int witteTrui, final int rodeLantaren, final int geleTruiEind, final int groeneTruiEind,
			final int bolletjesTruiEind, final int witteTruiEind, final int rodeLantarenEind, final int eersteUitvallerEind, final int mostCombativeStage,
			final int mostCombativeFinal) {
		this.etappe = new double[etappe];
		this.geleTrui = new double[geleTrui];
		this.groeneTrui = new double[groeneTrui];
		this.bolletjesTrui = new double[bolletjesTrui];
		this.witteTrui = new double[witteTrui];
		this.rodeLantaren = new double[rodeLantaren];
		this.geleTruiEind = new double[geleTruiEind];
		this.groeneTruiEind = new double[groeneTruiEind];
		this.bolletjesTruiEind = new double[bolletjesTruiEind];
		this.witteTruiEind = new double[witteTruiEind];
		this.rodeLantarenEind = new double[rodeLantarenEind];
		this.eersteUitvallerEind = new double[eersteUitvallerEind];
		this.mostCombativeStage = new double[mostCombativeStage];
		this.mostCombativeFinal = new double[mostCombativeFinal];
	}

	/**
	 * @return the bolletjesTrui
	 */
	public double[] getBolletjesTrui() {
		return bolletjesTrui;
	}

	/**
	 * @param bolletjesTrui
	 *            the bolletjesTrui to set
	 */
	public void setBolletjesTrui(final double[] bolletjesTrui) {
		this.bolletjesTrui = bolletjesTrui;
	}

	/**
	 * @return the bolletjesTruiEind
	 */
	public double[] getBolletjesTruiEind() {
		return bolletjesTruiEind;
	}

	/**
	 * @param bolletjesTruiEind
	 *            the bolletjesTruiEind to set
	 */
	public void setBolletjesTruiEind(final double[] bolletjesTruiEind) {
		this.bolletjesTruiEind = bolletjesTruiEind;
	}

	/**
	 * @return the etappe
	 */
	public double[] getEtappe() {
		return etappe;
	}

	/**
	 * @param etappe
	 *            the etappe to set
	 */
	public void setEtappe(final double[] etappe) {
		this.etappe = etappe;
	}

	/**
	 * @return the geleTrui
	 */
	public double[] getGeleTrui() {
		return geleTrui;
	}

	/**
	 * @param geleTrui
	 *            the geleTrui to set
	 */
	public void setGeleTrui(final double[] geleTrui) {
		this.geleTrui = geleTrui;
	}

	/**
	 * @return the geleTruiEind
	 */
	public double[] getGeleTruiEind() {
		return geleTruiEind;
	}

	/**
	 * @param geleTruiEind
	 *            the geleTruiEind to set
	 */
	public void setGeleTruiEind(final double[] geleTruiEind) {
		this.geleTruiEind = geleTruiEind;
	}

	/**
	 * @return the groeneTruiEind
	 */
	public double[] getGroeneTruiEind() {
		return groeneTruiEind;
	}

	/**
	 * @param groeneTruiEind
	 *            the groeneTruiEind to set
	 */
	public void setGroeneTruiEind(final double[] groeneTruiEind) {
		this.groeneTruiEind = groeneTruiEind;
	}

	/**
	 * @return the groeneTrui
	 */
	public double[] getGroeneTrui() {
		return groeneTrui;
	}

	/**
	 * @param groeneTrui
	 *            the groenTrui to set
	 */
	public void setGroeneTrui(final double[] groeneTrui) {
		this.groeneTrui = groeneTrui;
	}

	/**
	 * @return the eersteUitvallerEind
	 */
	public double[] getEersteUitvallerEind() {
		return eersteUitvallerEind;
	}

	/**
	 * @param eersteUitvallerEind
	 *            the eersteUitvallerEind to set
	 */
	public void setEersteUitvallerEind(final double[] eersteUitvallerEind) {
		this.eersteUitvallerEind = eersteUitvallerEind;
	}

	/**
	 * @return the rodeLantarenEind
	 */
	public double[] getRodeLantarenEind() {
		return rodeLantarenEind;
	}

	/**
	 * @param rodeLantarenEind
	 *            the rodeLantarenEind to set
	 */
	public void setRodeLantarenEind(final double[] rodeLantarenEind) {
		this.rodeLantarenEind = rodeLantarenEind;
	}

	/**
	 * @return the witteTruiEind
	 */
	public double[] getWitteTruiEind() {
		return witteTruiEind;
	}

	/**
	 * @param witteTruiEind
	 *            the witteTruiEind to set
	 */
	public void setWitteTruiEind(final double[] witteTruiEind) {
		this.witteTruiEind = witteTruiEind;
	}

	/**
	 * @return the mostCombativeStage
	 */
	public double[] getMostCombativeStage() {
		return mostCombativeStage;
	}

	/**
	 * @param mostCombativeStage
	 *            the mostCombativeStage to set
	 */
	public void setMostCombativeStage(final double[] mostCombativeStage) {
		this.mostCombativeStage = mostCombativeStage;
	}

	/**
	 * @return the mostCombativeFinal
	 */
	public double[] getMostCombativeFinal() {
		return mostCombativeFinal;
	}

	/**
	 * @param mostCombativeFinal
	 *            the mostCombativeFinal to set
	 */
	public void setMostCombativeFinal(final double[] mostCombativeFinal) {
		this.mostCombativeFinal = mostCombativeFinal;
	}

	/**
	 * @return the witteTrui
	 */
	public double[] getWitteTrui() {
		return witteTrui;
	}

	/**
	 * @param witteTrui the witteTrui to set
	 */
	public void setWitteTrui(double[] witteTrui) {
		this.witteTrui = witteTrui;
	}

	/**
	 * @return the rodeLantaren
	 */
	public double[] getRodeLantaren() {
		return rodeLantaren;
	}

	/**
	 * @param rodeLantaren the rodeLantaren to set
	 */
	public void setRodeLantaren(double[] rodeLantaren) {
		this.rodeLantaren = rodeLantaren;
	}

}
