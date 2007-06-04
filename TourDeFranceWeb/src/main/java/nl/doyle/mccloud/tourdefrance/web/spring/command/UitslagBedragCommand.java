package nl.doyle.mccloud.tourdefrance.web.spring.command;

import nl.doyle.mccloud.tourdefrance.config.TourConfig;

public class UitslagBedragCommand {
	
	
	private double[] etappe;
	private double[] geleTrui;
	private double[] groeneTrui;
	private double[] bolletjesTrui;
	private double[] geleTruiEind;
	private double[] groeneTruiEind;
	private double[] bolletjesTruiEind;
	
	/**
	 * Default constructor
	 */
	
	public UitslagBedragCommand(int etappe, int geleTrui, int groeneTrui, int bolletjesTrui, int geleTruiEind, int groeneTruiEind, int bolletjesTruiEind) {
		this.etappe = new double[etappe];
		this.geleTrui = new double[geleTrui];
		this.groeneTrui = new  double[groeneTrui];
		this.bolletjesTrui = new double[bolletjesTrui];
		this.geleTruiEind = new double[geleTruiEind];
		this.groeneTruiEind = new double[groeneTruiEind];
		this.bolletjesTruiEind = new double[bolletjesTruiEind];
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
