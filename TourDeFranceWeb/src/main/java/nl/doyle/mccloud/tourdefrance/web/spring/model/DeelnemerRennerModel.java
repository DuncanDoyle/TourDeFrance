package nl.doyle.mccloud.tourdefrance.web.spring.model;

import nl.doyle.mccloud.tourdefrance.dto.RennerDto;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DeelnemerRennerModel {
	
	
private static final Log logger = LogFactory.getLog(DeelnemerRennerModel.class);
	
	private RennerModel renner;
	private DeelnemerModel deelnemer;
	
	/**
	 * Default Constructor
	 */
	public DeelnemerRennerModel(){
	}

	/**
	 * @return the deelnemer
	 */
	public DeelnemerModel getDeelnemer() {
		return deelnemer;
	}

	/**
	 * @param deelnemer the deelnemer to set
	 */
	public void setDeelnemer(DeelnemerModel deelnemer) {
		this.deelnemer = deelnemer;
	}

	/**
	 * @return the renner
	 */
	public RennerModel getRenner() {
		return renner;
	}

	/**
	 * @param renner the renner to set
	 */
	public void setRenner(RennerModel renner) {
		this.renner = renner;
	}




	public String toString() {
		
		return new ToStringBuilder(this)
			.append("Rennernummer", renner.getNummer())
			.append("RennerVoornaam", renner.getVoornaam())
			.append("RennerAchternaam", renner.getAchternaam())
			.append("DeelnemerNummer", deelnemer.getNummer())
			.append("DeelnemerVoornaam", deelnemer.getVoornaam())
			.append("DeelnemerAchternaam", deelnemer.getAchternaam())
			.append("DeelnemerEmail", deelnemer.getEmail())
			.append("DeelnemerRekeningnummer", deelnemer.getRekeningnummer())
			.toString();
	}
	
	
	
	

}
