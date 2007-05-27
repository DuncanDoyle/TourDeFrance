package nl.doyle.mccloud.tourdefrance.dao;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag.Categorien;

public interface UitslagBedragDao {

	
	public List<UitslagBedrag> loadAllUitslagBedragen();
	public List<UitslagBedrag> loadAllUitslagBedragenPerCategorie(final Categorien categorie);
	public UitslagBedrag loadUitslagBedrag(final Categorien categorie, final int positie);
	public void saveUitslagBedrag(UitslagBedrag saveUitslagBedrag);
	
	
}
