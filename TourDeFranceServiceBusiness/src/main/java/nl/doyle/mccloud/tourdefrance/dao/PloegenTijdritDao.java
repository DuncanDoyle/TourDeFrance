package nl.doyle.mccloud.tourdefrance.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;



public interface PloegenTijdritDao {

	public List<PloegenTijdrit> loadAllPloegenTijdritten();
	public PloegenTijdrit loadPloegenTijdrit(final int etappenummer);
	public PloegenTijdrit loadPloegenTijdritWithUitslagEager(final int etappenummer);
	public PloegenTijdrit loadPloegenTijdritWithStartAndFinish(final int etappenummer);
	public void savePloegenTijdrit(final PloegenTijdrit savePloegenTijdrit);
	public List<PloegenTijdrit> loadAllPloegenTijdrittenWithStedenEager();
	public int getHighestEtappeNummer();
	//public void deleteAllPloegenTijdritten();
	
}
