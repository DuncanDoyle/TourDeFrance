package nl.doyle.mccloud.tourdefrance.web.spring.command;

import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag.Categorien;

public class UitslagBedragCommand {
	
	private double[] etappe = new double[15];
	private double[] geleTrui = new double[5];
	private double[] groeneTrui = new  double[3];
	private double[] bolletjesTrui = new double[3];
	private double[] geleTruiEind = new double[15];
	private double[] groeneTruiEind = new double[5];
	private double[] bolletjesTruiEind = new double[5];
	
	/**
	 * Default constructor
	 */
	public UitslagBedragCommand() {
	}

	/**
	 * @return the bolletjesTrui
	 */
	public double[] getBolletjesTrui() {
		return bolletjesTrui;
	}

	/**
	 * @param bolletjesTrui the bolletjesTrui to set
	 */
	public void setBolletjesTrui(double[] bolletjesTrui) {
		this.bolletjesTrui = bolletjesTrui;
	}

	/**
	 * @return the bolletjesTruiEind
	 */
	public double[] getBolletjesTruiEind() {
		return bolletjesTruiEind;
	}

	/**
	 * @param bolletjesTruiEind the bolletjesTruiEind to set
	 */
	public void setBolletjesTruiEind(double[] bolletjesTruiEind) {
		this.bolletjesTruiEind = bolletjesTruiEind;
	}

	/**
	 * @return the etappe
	 */
	public double[] getEtappe() {
		return etappe;
	}

	/**
	 * @param etappe the etappe to set
	 */
	public void setEtappe(double[] etappe) {
		this.etappe = etappe;
	}

	/**
	 * @return the geleTrui
	 */
	public double[] getGeleTrui() {
		return geleTrui;
	}

	/**
	 * @param geleTrui the geleTrui to set
	 */
	public void setGeleTrui(double[] geleTrui) {
		this.geleTrui = geleTrui;
	}

	/**
	 * @return the geleTruiEind
	 */
	public double[] getGeleTruiEind() {
		return geleTruiEind;
	}

	/**
	 * @param geleTruiEind the geleTruiEind to set
	 */
	public void setGeleTruiEind(double[] geleTruiEind) {
		this.geleTruiEind = geleTruiEind;
	}

	/**
	 * @return the groeneTruiEind
	 */
	public double[] getGroeneTruiEind() {
		return groeneTruiEind;
	}

	/**
	 * @param groeneTruiEind the groeneTruiEind to set
	 */
	public void setGroeneTruiEind(double[] groeneTruiEind) {
		this.groeneTruiEind = groeneTruiEind;
	}

	/**
	 * @return the groenTrui
	 */
	public double[] getGroeneTrui() {
		return groeneTrui;
	}

	/**
	 * @param groenTrui the groenTrui to set
	 */
	public void setGroeneTrui(double[] groeneTrui) {
		this.groeneTrui = groeneTrui;
	}
	
	

}
