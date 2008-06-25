package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.io.Serializable;


/**
 * Defines the winning amount for the specified categorie and position.
 * 
 * @author Duncan Doyle
 * @since 0.1
 * @see Categorien
 */
public class UitslagBedrag implements Serializable {

	
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	
	public enum Categorien{Etappe,GeleTrui,GroeneTrui,BolletjesTrui,GeleTruiEind, GroeneTruiEind, BolletjesTruiEind, WitteTruiEind, RodeLantarenEind, EersteUitvallerEind};
	
	/**
	 * The categorie of the amount.
	 */
	private Categorien categorie;
	
	/**
	 * The position within the categorie.
	 */
	private int positie;
	
	/**
	 * The amount.
	 */
	private double bedrag;
	
	
	
	public UitslagBedrag() {
	}
	
	
	/**
	 * @return the bedrag
	 */
	public double getBedrag() {
		return bedrag;
	}
	/**
	 * @param bedrag the bedrag to set
	 */
	public void setBedrag(double bedrag) {
		this.bedrag = bedrag;
	}
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
