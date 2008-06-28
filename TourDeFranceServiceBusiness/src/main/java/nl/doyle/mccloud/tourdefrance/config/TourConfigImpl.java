package nl.doyle.mccloud.tourdefrance.config;

import org.apache.commons.configuration.Configuration;


/**
 * Implementatie klasse van de configuratie van deze Tour applicatie. Gebruikt het Commons Configuration attribute
 * om de configuraties uit te lezen.
 * 
 * 
 * @author idxdoadmin
 *
 */
public class TourConfigImpl implements TourConfig {
	
	private Configuration config;
	
	/**
	 * Default constructor
	 */
	public TourConfigImpl() {
	}

	
	
	/* (non-Javadoc)
	 * @see nl.doyle.mccloud.tourdefrance.config.TourConfig#getAantalEinduitslagBolletjesTruiUitslagen()
	 */
	public int getAantalEinduitslagBolletjesTruiUitslagen() {
		return config.getInt("einduitslag.bolletjestrui");
	}

	/* (non-Javadoc)
	 * @see nl.doyle.mccloud.tourdefrance.config.TourConfig#getAantalEinduitslagGeleTruiUitslagen()
	 */
	public int getAantalEinduitslagGeleTruiUitslagen() {
		return config.getInt("einduitslag.geletrui");
	}

	/* (non-Javadoc)
	 * @see nl.doyle.mccloud.tourdefrance.config.TourConfig#getAantalEinduitslagGroeneTruiUitslagen()
	 */
	public int getAantalEinduitslagGroeneTruiUitslagen() {
		return config.getInt("einduitslag.groenetrui");
	}

	/* (non-Javadoc)
	 * @see nl.doyle.mccloud.tourdefrance.config.TourConfig#getAantalEtappeBolletjesTruiUitslagen()
	 */
	public int getAantalEtappeBolletjesTruiUitslagen() {
		return config.getInt("etappe.bolletjestrui");
	}

	/* (non-Javadoc)
	 * @see nl.doyle.mccloud.tourdefrance.config.TourConfig#getAantalEtappeGeleTruiUitslagen()
	 */
	public int getAantalEtappeGeleTruiUitslagen() {
		return config.getInt("etappe.geletrui");
	}
	
	/* (non-Javadoc)
	 * @see nl.doyle.mccloud.tourdefrance.config.TourConfig#getAantalEtappeGroeneTruiUitslagen()
	 */
	public int getAantalEtappeGroeneTruiUitslagen() {
		return config.getInt("etappe.groenetrui");
	}

	/* (non-Javadoc)
	 * @see nl.doyle.mccloud.tourdefrance.config.TourConfig#getAantalEtappeUitslagen()
	 */
	public int getAantalEtappeUitslagen() {
		return config.getInt("etappe.uitslag");
	}
	
	public int getNumberOfStageMostCombative() {
		return config.getInt("etappe.mostcombative");
	}

	/* (non-Javadoc)
	 * @see nl.doyle.mccloud.tourdefrance.config.TourConfig#getAantalEinduitslagEersteUitvaller()
	 */
	public int getAantalEinduitslagEersteUitvaller() {
		return config.getInt("einduitslag.eersteuitvaller");
	}

	/* (non-Javadoc)
	 * @see nl.doyle.mccloud.tourdefrance.config.TourConfig#getAantalEinduitslagRodeLantaren()
	 */
	public int getAantalEinduitslagRodeLantaren() {
		return config.getInt("einduitslag.rodelantaren");
	}



	/* (non-Javadoc)
	 * @see nl.doyle.mccloud.tourdefrance.config.TourConfig#getAantalEinduitslagWitteTrui()
	 */
	public int getAantalEinduitslagWitteTrui() {
		return config.getInt("einduitslag.wittetrui");
	}
	
	public int getNumberOfFinalMostCombative() {
		return config.getInt("einduitslag.mostcombative");
	}

	/**
	 * @return the config
	 */
	public Configuration getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(Configuration config) {
		this.config = config;
	}



	
	
	
	
	

}
