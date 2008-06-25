package nl.doyle.mccloud.tourdefrance.web.spring.command;

import java.util.Date;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;

public class EtappeUitslagCommand {

	public enum EtappeType {Etappe, PloegenTijdrit, EindUitslag};
	
	private List<Renner> renners;
	
	//private UitslagCommand uitslag;
	//private GeleTruiUitslagCommand geleTruiUitslag;
	//private GroeneTruiUitslagCommand groeneTruiUitslag;
	//private BolletjesTruiUitslagCommand bolletjesTruiUitslag;
	private int[] uitslag;
	private int[] geleTruiUitslag;
	private int[] groeneTruiUitslag;
	private int[] bolletjesTruiUitslag;
	
	private int witteTrui;
	private int rodeLantaren;
	private int eersteUitvaller;
	
	private int etappenummer;
	private String omschrijving;
	private Stad startPlaats;
	private Stad finishPlaats;
	private Date datum;
	
	private EtappeType typeEtappe;
	
	public EtappeUitslagCommand(int uitslag, int geleTruiUitslag, int groeneTruiUitslag, int bolletjesTruiUitslag) {
		this.uitslag = new int[uitslag];
		this.geleTruiUitslag = new int[geleTruiUitslag];
		this.groeneTruiUitslag = new int[groeneTruiUitslag];
		this.bolletjesTruiUitslag = new int[bolletjesTruiUitslag];
	}
	/**
	 * @return the renners
	 */
	public List<Renner> getRenners() {
		return renners;
	}

	/**
	 * @param renners the renners to set
	 */
	public void setRenners(List<Renner> renners) {
		this.renners = renners;
	}
	
	/**
	 * @return the datum
	 */
	public Date getDatum() {
		return datum;
	}

	/**
	 * @param datum the datum to set
	 */
	public void setDatum(Date datum) {
		this.datum = datum;
	}

	/**
	 * @return the etappenummer
	 */
	public int getEtappenummer() {
		return etappenummer;
	}

	/**
	 * @param etappenummer the etappenummer to set
	 */
	public void setEtappenummer(int etappenummer) {
		this.etappenummer = etappenummer;
	}

	/**
	 * @return the finishPlaats
	 */
	public Stad getFinishPlaats() {
		return finishPlaats;
	}

	/**
	 * @param finishPlaats the finishPlaats to set
	 */
	public void setFinishPlaats(Stad finishPlaats) {
		this.finishPlaats = finishPlaats;
	}

	/**
	 * @return the startPlaats
	 */
	public Stad getStartPlaats() {
		return startPlaats;
	}

	/**
	 * @param startPlaats the startPlaats to set
	 */
	public void setStartPlaats(Stad startPlaats) {
		this.startPlaats = startPlaats;
	}

	/**
	 * @return the bolletjesTruiUitslag
	 */
	public int[] getBolletjesTruiUitslag() {
		return bolletjesTruiUitslag;
	}

	/**
	 * @param bolletjesTruiUitslag the bolletjesTruiUitslag to set
	 */
	public void setBolletjesTruiUitslag(int[] bolletjesTruiUitslag) {
		this.bolletjesTruiUitslag = bolletjesTruiUitslag;
	}

	/**
	 * @return the geleTruiUitslag
	 */
	public int[] getGeleTruiUitslag() {
		return geleTruiUitslag;
	}

	/**
	 * @param geleTruiUitslag the geleTruiUitslag to set
	 */
	public void setGeleTruiUitslag(int[] geleTruiUitslag) {
		this.geleTruiUitslag = geleTruiUitslag;
	}

	/**
	 * @return the groenTruiUitslag
	 */
	public int[] getGroeneTruiUitslag() {
		return groeneTruiUitslag;
	}

	/**
	 * @param groenTruiUitslag the groenTruiUitslag to set
	 */
	public void setGroeneTruiUitslag(int[] groeneTruiUitslag) {
		this.groeneTruiUitslag = groeneTruiUitslag;
	}

	/**
	 * @return the uitslag
	 */
	public int[] getUitslag() {
		return uitslag;
	}

	/**
	 * @param uitslag the uitslag to set
	 */
	public void setUitslag(int[] uitslag) {
		this.uitslag = uitslag;
	}
	/**
	 * @return the typeEtappe
	 */
	public EtappeType getTypeEtappe() {
		return typeEtappe;
	}
	/**
	 * @param typeEtappe the typeEtappe to set
	 */
	public void setTypeEtappe(EtappeType typeEtappe) {
		this.typeEtappe = typeEtappe;
	}
	/**
	 * @return the eersteUitvaller
	 */
	public int getEersteUitvaller() {
		return eersteUitvaller;
	}
	/**
	 * @param eersteUitvaller the eersteUitvaller to set
	 */
	public void setEersteUitvaller(int eersteUitvaller) {
		this.eersteUitvaller = eersteUitvaller;
	}
	/**
	 * @return the rodeLantaren
	 */
	public int getRodeLantaren() {
		return rodeLantaren;
	}
	/**
	 * @param rodeLantaren the rodeLantaren to set
	 */
	public void setRodeLantaren(int rodeLantaren) {
		this.rodeLantaren = rodeLantaren;
	}
	/**
	 * @return the witteTrui
	 */
	public int getWitteTrui() {
		return witteTrui;
	}
	/**
	 * @param witteTrui the witteTrui to set
	 */
	public void setWitteTrui(int witteTrui) {
		this.witteTrui = witteTrui;
	}
	/**
	 * @return the omschrijving
	 */
	public String getOmschrijving() {
		return omschrijving;
	}
	/**
	 * @param omschrijving the omschrijving to set
	 */
	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	
	
	
	
	
	
}
