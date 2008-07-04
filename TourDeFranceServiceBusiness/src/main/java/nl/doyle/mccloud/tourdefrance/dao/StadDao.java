package nl.doyle.mccloud.tourdefrance.dao;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;

public interface StadDao {
	public List<Stad> loadAllSteden();
	public List<Stad> loadStad(final String stad, final String land);
	public Stad loadStad(int id);
	public void saveOrUpdateStad(final Stad saveStad);
	public void deleteStad(final Stad delStad);
}
