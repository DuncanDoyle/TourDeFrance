package nl.doyle.mccloud.tourdefrance.dao;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;

public interface DeelnemerDao {
	
	
	public List<Deelnemer> loadAllDeelnemers();
	public Deelnemer loadDeelnemer(final int deelnemerNummer);
	public Deelnemer loadDeelnemerEager(int nummer);
	public List<Deelnemer> loadAllDeelnemersEager();
	public void saveDeelnemer(final Deelnemer saveDeelnemer);
	public void deleteDeelnemer(final Deelnemer delDeelnemer);
	public void saveDeelnemerDataExcludingForeignKeys(final int nummer, final String voornaam, final String achternaam, final String email, final String rekeningnummer);
	
}
