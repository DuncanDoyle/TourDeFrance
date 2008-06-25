package nl.doyle.mccloud.tourdefrance.dao;

import nl.doyle.mccloud.tourdefrance.valueobjects.EindUitslag;

public interface EindUitslagDao {
	
	public abstract EindUitslag loadEindUitslag();
	public abstract void saveEindUitslag(final EindUitslag saveEindUitslag);
	public abstract EindUitslag loadEindUitslagWithUitslagEager();

}
