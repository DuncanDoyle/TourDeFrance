package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

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

	@Column(name = "ETAPPENUMMER", nullable = false)
	private int etappenummer;

	@Column(name = "POSITIE", nullable = false)
	private int positie;

	/**
	 * Default constructor.
	 */
	public UitslagPk() {
	}

	/**
	 * Constructor which initializes the stage number and position.
	 * 
	 * @param etappenummer
	 *            the stage number.
	 * @param positie
	 *            the position.
	 */
	public UitslagPk(final int etappenummer, final int positie) {
		this.etappenummer = etappenummer;
		this.positie = positie;
	}

	/**
	 * @return the etappenummer
	 */
	public int getEtappenummer() {
		return etappenummer;
	}

	/**
	 * @param etappenummer
	 *            the etappenummer to set
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
	 * @param positie
	 *            the positie to set
	 */
	public void setPositie(int positie) {
		this.positie = positie;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("etappenummer", etappenummer).append("positie", positie).toString();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UitslagPk)) {
			return false;
		}
		if (this == object) {
			return true;
		}
		UitslagPk rhs = (UitslagPk) object;
		return new EqualsBuilder().append(getEtappenummer(), rhs.getEtappenummer()).append(getPositie(), rhs.getPositie()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getEtappenummer()).append(getPositie()).toHashCode();
	}
	
}
