package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Primary key class of the {@link Uitslag} class. Needed for JPA.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
@Embeddable
public class UitslagPk implements Serializable {
	
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 2L;

	@Column(name="ETAPPENUMMER", nullable=false)
	private int etappenummer;
	
	@Column(name="POSITIE", nullable=false)
	private int positie;

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
	 * @return the positie
	 */
	public int getPositie() {
		return positie;
	}

	/**
	 * @param positie the positie to set
	 */
	public void setPositie(int positie) {
		this.positie = positie;
	}
	
}
