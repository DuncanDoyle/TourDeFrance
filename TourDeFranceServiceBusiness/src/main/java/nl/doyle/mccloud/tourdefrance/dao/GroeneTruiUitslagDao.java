package nl.doyle.mccloud.tourdefrance.dao;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.GroeneTruiUitslag;

public interface GroeneTruiUitslagDao {
	
	public List<GroeneTruiUitslag> loadAllGroeneTruiUitslagen();
	public GroeneTruiUitslag loadGroeneTruiUitslag(final int etappeNummer, final int positieNummer);
	public void saveGroeneTruiUitslag(final GroeneTruiUitslag saveGroeneTruiUitslag);
	public void deleteGroeneTruiUitslag(final GroeneTruiUitslag delGroeneTruiUitslag);
	//public void deleteAllGroeneTruiUitslagen();
}
