package nl.doyle.mccloud.tourdefrance.web.spring.command;

import java.util.Date;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;
import nl.doyle.mccloud.tourdefrance.web.spring.controller.EditEtappeUitslagFormController;

/**
 * MVC command type for the {@link EditEtappeUitslagFormController}.
 * 
 * @author Duncan Doyle
 * @since 0.1
 */
public class EtappeUitslagCommand {

	/**
	 * Enumeration of the different stage types. Used for example to define what kind of content to display in a view.
	 * 
	 * @author Duncan Doyle
	 * @since 0.1
	 */
	public enum EtappeType {
		Etappe, PloegenTijdrit, EindUitslag
	};

	private List<Renner> renners;

	// private UitslagCommand uitslag;
	// private GeleTruiUitslagCommand geleTruiUitslag;
	// private GroeneTruiUitslagCommand groeneTruiUitslag;
	// private BolletjesTruiUitslagCommand bolletjesTruiUitslag;
	private int[] uitslag;
	private int[] geleTruiUitslag;
	private int[] groeneTruiUitslag;
	private int[] bolletjesTruiUitslag;
	private int[] witteTruiUitslag;
	
	private int rodeLantaren;
	private int eersteUitvaller;
	private int mostCombative;

	private int etappenummer;
	private String omschrijving;
	private Stad startPlaats;
	private Stad finishPlaats;
	private Date datum;

	private EtappeType typeEtappe;

	public EtappeUitslagCommand(final int uitslag, final int geleTruiUitslag, final int groeneTruiUitslag, final int bolletjesTruiUitslag, final int witteTruiUitslag) {
		this.uitslag = new int[uitslag];
		this.geleTruiUitslag = new int[geleTruiUitslag];
		this.groeneTruiUitslag = new int[groeneTruiUitslag];
		this.bolletjesTruiUitslag = new int[bolletjesTruiUitslag];
		this.witteTruiUitslag = new int[witteTruiUitslag];
	}

	/**
	 * @return the renners
	 */
	public List<Renner> getRenners() {
		return renners;
	}

	/**
	 * @param renners
	 *            the renners to set
	 */
	public void setRenners(final List<Renner> renners) {
		this.renners = renners;
	}

	/**
	 * @return the datum
	 */
	public Date getDatum() {
		return datum;
	}

	/**
	 * @param datum
	 *            the datum to set
	 */
	public void setDatum(final Date datum) {
		this.datum = datum;
	}

	/**
	 * @return the etappenummer
	 */
	public int getEtappenummer() {
		return etappenummer;
	}

	/**
	 * @param etappenummer
	 *            the etappenummer to set
	 */
	public void setEtappenummer(final int etappenummer) {
		this.etappenummer = etappenummer;
	}

	/**
	 * @return the finishPlaats
	 */
	public Stad getFinishPlaats() {
		return finishPlaats;
	}

	/**
	 * @param finishPlaats
	 *            the finishPlaats to set
	 */
	public void setFinishPlaats(final Stad finishPlaats) {
		this.finishPlaats = finishPlaats;
	}

	/**
	 * @return the startPlaats
	 */
	public Stad getStartPlaats() {
		return startPlaats;
	}

	/**
	 * @param startPlaats
	 *            the startPlaats to set
	 */
	public void setStartPlaats(final Stad startPlaats) {
		this.startPlaats = startPlaats;
	}

	/**
	 * @return the bolletjesTruiUitslag
	 */
	public int[] getBolletjesTruiUitslag() {
		return bolletjesTruiUitslag;
	}

	/**
	 * @param bolletjesTruiUitslag
	 *            the bolletjesTruiUitslag to set
	 */
	public void setBolletjesTruiUitslag(final int[] bolletjesTruiUitslag) {
		this.bolletjesTruiUitslag = bolletjesTruiUitslag;
	}

	/**
	 * @return the geleTruiUitslag
	 */
	public int[] getGeleTruiUitslag() {
		return geleTruiUitslag;
	}

	/**
	 * @param geleTruiUitslag
	 *            the geleTruiUitslag to set
	 */
	public void setGeleTruiUitslag(final int[] geleTruiUitslag) {
		this.geleTruiUitslag = geleTruiUitslag;
	}

	/**
	 * @return the groenTruiUitslag
	 */
	public int[] getGroeneTruiUitslag() {
		return groeneTruiUitslag;
	}

	/**
	 * @param groenTruiUitslag
	 *            the groenTruiUitslag to set
	 */
	public void setGroeneTruiUitslag(final int[] groeneTruiUitslag) {
		this.groeneTruiUitslag = groeneTruiUitslag;
	}

	/**
	 * @return the uitslag
	 */
	public int[] getUitslag() {
		return uitslag;
	}

	/**
	 * @param uitslag
	 *            the uitslag to set
	 */
	public void setUitslag(final int[] uitslag) {
		this.uitslag = uitslag;
	}

	/**
	 * @return the typeEtappe
	 */
	public EtappeType getTypeEtappe() {
		return typeEtappe;
	}

	/**
	 * @param typeEtappe
	 *            the typeEtappe to set
	 */
	public void setTypeEtappe(final EtappeType typeEtappe) {
		this.typeEtappe = typeEtappe;
	}

	/**
	 * @return the eersteUitvaller
	 */
	public int getEersteUitvaller() {
		return eersteUitvaller;
	}

	/**
	 * @param eersteUitvaller
	 *            the eersteUitvaller to set
	 */
	public void setEersteUitvaller(final int eersteUitvaller) {
		this.eersteUitvaller = eersteUitvaller;
	}

	/**
	 * @return the rodeLantaren
	 */
	public int getRodeLantaren() {
		return rodeLantaren;
	}

	/**
	 * @param rodeLantaren
	 *            the rodeLantaren to set
	 */
	public void setRodeLantaren(final int rodeLantaren) {
		this.rodeLantaren = rodeLantaren;
	}

	/**
	 * @return the witteTrui
	 */
	public int[] getWitteTruiUitslag() {
		return witteTruiUitslag;
	}

	/**
	 * @param witteTruiUitslag the witteTrui to set
	 */
	public void setWitteTruiUitslag(int[] witteTruiUitslag) {
		this.witteTruiUitslag = witteTruiUitslag;
	}

	/**
	 * @return the omschrijving
	 */
	public String getOmschrijving() {
		return omschrijving;
	}

	/**
	 * @param omschrijving
	 *            the omschrijving to set
	 */
	public void setOmschrijving(final String omschrijving) {
		this.omschrijving = omschrijving;
	}

	/**
	 * @return the mostCombative
	 */
	public int getMostCombative() {
		return mostCombative;
	}

	/**
	 * @param mostCombative
	 *            the mostCombative to set
	 */
	public void setMostCombative(final int mostCombative) {
		this.mostCombative = mostCombative;
	}

}
