package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Defines the winning amount for the specified categorie and position.
 * 
 * @author Duncan Doyle
 * @since 0.1
 * @see Categorien
 */
@Entity
@Table(name = "TOUR.UITSLAG_BEDRAG")
public final class UitslagBedrag implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * Categories in result (both stage and final result).
	 * 
	 * @author Duncan Doyle
	 * @since 0.1
	 */
	public enum Categorien {
		Etappe,
		GeleTrui,
		GroeneTrui,
		BolletjesTrui,
		GeleTruiEind,
		GroeneTruiEind,
		BolletjesTruiEind,
		WitteTruiEind,
		RodeLantarenEind,
		EersteUitvallerEind,
		MostCombativeStage,
		MostCombativeFinal
	};

	/**
	 * The class's composite primary key object. 
	 */
	@EmbeddedId
	private UitslagBedragPk uitslagBedragPk = new UitslagBedragPk();

	/**
	 * The amount.
	 */
	@Column(name = "BEDRAG")
	private double bedrag;

	/**
	 * Default constructor.
	 */
	public UitslagBedrag() {
	}

	/**
	 * @return the bedrag
	 */
	public double getBedrag() {
		return bedrag;
	}

	/**
	 * @param bedrag
	 *            the bedrag to set
	 */
	public void setBedrag(final double bedrag) {
		this.bedrag = bedrag;
	}

	/**
	 * @return the categorie
	 */
	public Categorien getCategorie() {
		return uitslagBedragPk.getCategorie();
	}

	/**
	 * @param categorie
	 *            the categorie to set
	 */
	public void setCategorie(final Categorien categorie) {
		uitslagBedragPk.setCategorie(categorie);
	}

	/**
	 * @return the positie
	 */
	public int getPositie() {
		return uitslagBedragPk.getPositie();
	}

	/**
	 * @param positie
	 *            the positie to set
	 */
	public void setPositie(final int positie) {
		uitslagBedragPk.setPositie(positie);
	}

}
