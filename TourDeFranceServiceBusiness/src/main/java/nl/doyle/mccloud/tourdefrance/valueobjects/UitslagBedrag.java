package nl.doyle.mccloud.tourdefrance.valueobjects;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UitslagBedrag implements Serializable {

	public enum Categorien{Etappe,GeleTrui,GroeneTrui,BolletjesTrui,GeleTruiEind, GroeneTruiEind, BolletjesTruiEind, WitteTruiEind, RodeLantarenEind, EersteUitvallerEind};
	private static final Log logger = LogFactory.getLog(UitslagBedrag.class);
	
	private Categorien categorie;
	private int positie;
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
