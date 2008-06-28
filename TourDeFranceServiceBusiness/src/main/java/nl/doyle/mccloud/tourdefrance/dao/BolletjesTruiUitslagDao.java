package nl.doyle.mccloud.tourdefrance.dao;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.BolletjesTruiUitslag;

public interface BolletjesTruiUitslagDao {
	public List<BolletjesTruiUitslag> loadAllBolletjesTruiUitslagen();
	public BolletjesTruiUitslag loadBolletjesTruiUitslag(final int etappenummer, final int positienummer);
	public void saveBolletjesTruiUitslag(final BolletjesTruiUitslag saveBolletjesTruiUitslag);
	public void deleteBolletjesTruiUitslag(final BolletjesTruiUitslag delBolletjesTruiUitslag);
	//public void deleteAllBolletjesTruiUitslagen();
}
