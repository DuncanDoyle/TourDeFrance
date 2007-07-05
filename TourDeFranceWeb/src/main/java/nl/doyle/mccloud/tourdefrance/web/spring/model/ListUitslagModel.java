package nl.doyle.mccloud.tourdefrance.web.spring.model;

import nl.doyle.mccloud.tourdefrance.dto.AbstractEtappeAndEindUitslagDto;
import nl.doyle.mccloud.tourdefrance.valueobjects.AbstractEtappeAndEindUitslag;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ListUitslagModel {
	
	public static final Log logger = LogFactory.getLog(ListUitslagModel.class);
	//TODO Deze public enum moet waarschijnlijke ergens anders gedefinieerd worden
	public enum EtappeType{Etappe, PloegenTijdrit, EindUitslag};

	private EtappeType typeEtappe;
	private AbstractEtappeAndEindUitslagDto etappe;
	
	public ListUitslagModel() {
	}

	/**
	 * @return the etappe
	 */
	public AbstractEtappeAndEindUitslagDto getEtappe() {
		return etappe;
	}

	/**
	 * @param etappe the etappe to set
	 */
	public void setEtappe(AbstractEtappeAndEindUitslagDto etappe) {
		this.etappe = etappe;
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
	
	
	
	
	
	
	
	
}
