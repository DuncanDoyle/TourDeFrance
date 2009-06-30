package nl.doyle.mccloud.tourdefrance.valueobjects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Defines the white jersey ranking. Defines the racer's position in the white jersey ranking after the specified stage.
 * 
 * @see Uitslag
 * @author Duncan Doyle
 * @since 0.1
 */
@Entity
@Table(name="WITTETRUIUITSLAG")
public class WitteTruiUitslag extends Uitslag {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UitslagPk uitslagPk = new UitslagPk();
	
	@ManyToOne
	@JoinColumn(name="RENNER")
	private Renner renner;

	
	public int getPositie() {
		return uitslagPk.getPositie();
	}

	public void setPositie(final int positie) {
		uitslagPk.setPositie(positie);
	}
	
	public int getEtappenummer() {
		return uitslagPk.getEtappenummer();
	}

	public void setEtappenummer(final int etappenummer) {
		uitslagPk.setEtappenummer(etappenummer);
	}
	
	public Renner getRenner() {
		return renner;
	}

	public void setRenner(final Renner renner) {
		this.renner = renner;
	}

}
