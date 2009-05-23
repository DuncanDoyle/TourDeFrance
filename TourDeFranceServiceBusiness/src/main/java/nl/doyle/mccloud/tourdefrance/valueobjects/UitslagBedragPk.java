package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag.Categorien;

/**
 * Primary key of the {@link UitslagBedrag} class. Needed for JPA implementation. 
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
@Embeddable
public class UitslagBedragPk implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 2L;


	/**
	 * The categorie of the amount.
	 */
	@Column(name = "CATEGORIE", nullable = false)
	private Categorien categorie;
	

	/**
	 * The position within the categorie.
	 */
	@Column(name = "POSITIE", nullable = false)
	private int positie;

	/**
	 * @return the categorie
	 */
	public Categorien getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorien categorie) {
		this.categorie = categorie;
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
