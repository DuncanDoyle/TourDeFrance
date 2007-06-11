package nl.doyle.mccloud.tourdefrance.web.spring.model;

import nl.doyle.mccloud.tourdefrance.valueobjects.AbstractEtappeAndEindUitslag;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ListUitslagModel {
	
	public static final Log logger = LogFactory.getLog(ListUitslagModel.class);
	//TODO Deze public enum moet waarschijnlijke ergens anders gedefinieerd worden
	public enum EtappeType{Etappe, PloegenTijdrit, EindUitslag};

	private EtappeType typeEtappe;
	private AbstractEtappeAndEindUitslag etappe;
	
	public ListUitslagModel() {
	}

	/**
	 * @return the etappe
	 */
	public AbstractEtappeAndEindUitslag getEtappe() {
		return etappe;
	}

	/**
	 * @param etappe the etappe to set
	 */
	public void setEtappe(AbstractEtappeAndEindUitslag etappe) {
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
