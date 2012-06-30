package nl.doyle.mccloud.tourdefrance.dao;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.GeleTruiUitslag;

public interface GeleTruiUitslagDao {

	public List<GeleTruiUitslag> loadAllGeleTruiUitslagen();
	public GeleTruiUitslag loadGeleTruiUitslag(final int etappeNummer, final int positieNummer);
	public void saveGeleTruiUitslag(final GeleTruiUitslag saveGeleTruiUitslag);
	public void deleteGeleTruiUitslag(final GeleTruiUitslag delGeleTruiUitslag);
	//public void deleteAllGeleTruiUitslagen();
	
}
