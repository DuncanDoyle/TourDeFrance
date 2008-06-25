package nl.doyle.mccloud.tourdefrance.dao;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;

public interface StandaardEtappeDao {

	public List<StandaardEtappe> loadAllStandaardEtappes();
	public StandaardEtappe loadStandaardEtappe(final int etappenummer);
	public StandaardEtappe loadStandaardEtappeWithStartAndFinish(final int etappenummer);
	public void saveStandaardEtappe(final StandaardEtappe saveStandaardEtappe);
	public StandaardEtappe loadStandaardEtappeWithUitslagEager(final int etappenummer);
	public List<StandaardEtappe> loadAllStandaardEtappesWithStedenEager();
	public int getHighestEtappeNummer();
	//public void deleteAllStandaardEtappes();
	
}
