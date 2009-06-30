package nl.doyle.mccloud.tourdefrance.dao;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.WitteTruiUitslag;

public interface WitteTruiUitslagDao {
		public List<WitteTruiUitslag> loadAllWitteTruiUitslagen();
		public WitteTruiUitslag loadWitteTruiUitslag(final int etappenummer, final int positienummer);
		public void saveWitteTruiUitslag(final WitteTruiUitslag saveWitteTruiUitslag);
		public void deleteWitteTruiUitslag(final WitteTruiUitslag delWitteTruiUitslag);
		//public void deleteAllBolletjesTruiUitslagen();
}
