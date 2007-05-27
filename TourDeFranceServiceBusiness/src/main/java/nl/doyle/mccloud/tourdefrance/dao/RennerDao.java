package nl.doyle.mccloud.tourdefrance.dao;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;

public interface RennerDao {
	
	public List<Renner> loadAllRenners();
	public List<Renner> loadAllRennersOrdered();
	public Renner loadRenner(int rennernummer);
	public void saveRenner(Renner saveRenner);
	public void saveOrUpdateRenner(Renner saveRenner);
	public void deleteRenner(Renner deleteRenner);
//	public void deleteAllRenners();
}
