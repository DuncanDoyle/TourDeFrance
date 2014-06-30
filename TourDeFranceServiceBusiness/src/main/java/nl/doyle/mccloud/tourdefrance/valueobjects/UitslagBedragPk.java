package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
	 * Default constructor.
	 */
	public UitslagBedragPk() {
	}

	/**
	 * Constructor which initializes the category and position.
	 * 
	 * @param categorie
	 *            the @link {@link Categorien categorie}.
	 * @param positie
	 *            the position.
	 */
	public UitslagBedragPk(final Categorien categorie, final int positie) {
		this.categorie = categorie;
		this.positie = positie;
	}

	/**
	 * @return the categorie
	 */
	public Categorien getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie
	 *            the categorie to set
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
	 * @param positie
	 *            the positie to set
	 */
	public void setPositie(int positie) {
		this.positie = positie;
	}
	
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UitslagBedragPk)) {
			return false;
		}
		if (this == object) {
			return true;
		}
		UitslagBedragPk rhs = (UitslagBedragPk) object;
		return new EqualsBuilder().append(getCategorie(), rhs.getCategorie()).append(getPositie(), rhs.getPositie()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getCategorie()).append(getPositie()).toHashCode();
	}

}
