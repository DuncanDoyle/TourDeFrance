package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Defines the ranking. Defines the racer's position in the ranking for the specified stage. Subclasses define which ranking.
 * 
 * @author Duncan Doyle
 * @since 0.1
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Uitslag implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 2L;

	/* TODO Maybe change this implementation. The etappe owns its uitslag object, so maybe this should contain an array of renners, no
	 * etappenummer. If we change this, the etappe should contain a result object, not a set of result objects.
	 */
	
	@EmbeddedId
	private UitslagPk uitslagPk = new UitslagPk();
	
	@ManyToOne
	@JoinColumn(name="RENNER")
	private Renner renner;

	/**
	 * @author mccloud
	 * 
	 *         Default constructor
	 */
	public Uitslag() {
	}

	public int getPositie() {
		return uitslagPk.getPositie();
	}

	public void setPositie(final int positie) {
		uitslagPk.setPositie(positie);
	}

	public Renner getRenner() {
		return renner;
	}

	public void setRenner(final Renner renner) {
		this.renner = renner;
	}

	public int getEtappenummer() {
		return uitslagPk.getEtappenummer();
	}

	public void setEtappenummer(final int etappenummer) {
		uitslagPk.setEtappenummer(etappenummer);
	}

	public String toString() {
		return new ToStringBuilder(this).append("etappenummer", this.getEtappenummer()).append("positie", this.getPositie()).append(
				"renner", this.getRenner()).toString();
	}

}
