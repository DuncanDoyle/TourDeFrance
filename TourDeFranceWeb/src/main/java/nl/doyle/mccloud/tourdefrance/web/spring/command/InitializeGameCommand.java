package nl.doyle.mccloud.tourdefrance.web.spring.command;

public class InitializeGameCommand {
	
	private int aantalPloegen;
	private int aantalEtappes;
	private int ploegenTijdritEtappeNummer;
	
	
	/**
	 * Default Constructor
	 */
	public InitializeGameCommand() {
	}


	/**
	 * @return the aantalEtappes
	 */
	public int getAantalEtappes() {
		return aantalEtappes;
	}


	/**
	 * @param aantalEtappes the aantalEtappes to set
	 */
	public void setAantalEtappes(int aantalEtappes) {
		this.aantalEtappes = aantalEtappes;
	}


	/**
	 * @return the aantalPloegen
	 */
	public int getAantalPloegen() {
		return aantalPloegen;
	}


	/**
	 * @param aantalPloegen the aantalPloegen to set
	 */
	public void setAantalPloegen(int aantalPloegen) {
		this.aantalPloegen = aantalPloegen;
	}


	/**
	 * @return the ploegenTijdrit
	 */
	public int getPloegenTijdritEtappeNummer() {
		return ploegenTijdritEtappeNummer;
	}


	/**
	 * @param ploegenTijdrit the ploegenTijdrit to set
	 */
	public void setPloegenTijdritEtappeNummer(int ploegenTijdritEtappeNummer) {
		this.ploegenTijdritEtappeNummer = ploegenTijdritEtappeNummer;
	}
	
	

}
