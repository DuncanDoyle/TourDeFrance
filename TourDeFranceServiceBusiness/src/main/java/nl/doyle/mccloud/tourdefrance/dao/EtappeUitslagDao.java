package nl.doyle.mccloud.tourdefrance.dao;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.EtappeUitslag;

public interface EtappeUitslagDao {
	public List<EtappeUitslag> loadAllEtappeUitslagen();
	public EtappeUitslag loadDEtappeUitslag(final int etappeNummer, final int positieNummer);
	public void saveEtappeUitslag(final EtappeUitslag saveEtappeUitslag);
	public void deleteEtappeUitslag(final EtappeUitslag delEtappeUitslag);
	//public void deleteAllEtappeUitslagen();
}
