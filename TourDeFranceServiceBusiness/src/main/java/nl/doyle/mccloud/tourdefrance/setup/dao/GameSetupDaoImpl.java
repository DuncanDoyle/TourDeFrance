package nl.doyle.mccloud.tourdefrance.setup.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides game setup utility methods.
 * 
 * @author Duncan Doyle
 * @since 0.1
 */
@Transactional
public class GameSetupDaoImpl extends JdbcDaoSupport implements GameSetupDao {

	/**
	 * @author mccloud
	 * 
	 * Verwijdert alle data uit de database.
	 * Via Spring AOP wordt er een JDBC transactie om deze SQL commando's heen gezet.
	 */
	public void deleteAllRecordsFromDatabase() {
		
		deleteAllTeamRecords();
		
		String sqlStandaardEtappesEtappeUitslag = "delete from standaardetappe_etappeuitslag";
		getJdbcTemplate().execute(sqlStandaardEtappesEtappeUitslag);
		
		String sqlEtappe_BolletjesTruiUitslag = "delete from etappe_bolletjestruiuitslag";
		getJdbcTemplate().execute(sqlEtappe_BolletjesTruiUitslag);
		
		String sqlEtappe_GeleTruiUitslag = "delete from etappe_geletruiuitslag";
		getJdbcTemplate().execute(sqlEtappe_GeleTruiUitslag);
		
		String sqlEtappe_GroeneTruiUitslag = "delete from etappe_groenetruiuitslag";
		getJdbcTemplate().execute(sqlEtappe_GroeneTruiUitslag);
		
		String sqlEtappe_WitteTruiUitslag = "delete from etappe_wittetruiuitslag";
		getJdbcTemplate().execute(sqlEtappe_WitteTruiUitslag);
		
		String sqlTeamRenner = "delete from team_renner";
		getJdbcTemplate().execute(sqlTeamRenner);
		
		String sqlStandaardEtappes = "delete from standaardetappe";
		getJdbcTemplate().execute(sqlStandaardEtappes);
		
		String sqlPloegentijdritten = "delete from ploegentijdrit";
		getJdbcTemplate().execute(sqlPloegentijdritten);
		
		String sqlEtappes = "delete from etappe";
		getJdbcTemplate().execute(sqlEtappes);
		
		//Maak daarna de rest van de tabellen leeg
		String sqlEindUitslag = "delete from einduitslag";
		getJdbcTemplate().execute(sqlEindUitslag);
		
		String sqlEtappeAndEindUitslag = "delete from etappeandeinduitslag";
		getJdbcTemplate().execute(sqlEtappeAndEindUitslag);
		
		String sqlEtappeUitslag = "delete from etappeuitslag";
		getJdbcTemplate().execute(sqlEtappeUitslag);
				
		String sqlGeleTruiUitslag = "delete from geletruiuitslag";
		getJdbcTemplate().execute(sqlGeleTruiUitslag);
				
		String sqlGroeneTruiUitslag = "delete from groenetruiuitslag";
		getJdbcTemplate().execute(sqlGroeneTruiUitslag);
				
		String sqlBolletjesTruiUitslag = "delete from bolletjestruiuitslag";
		getJdbcTemplate().execute(sqlBolletjesTruiUitslag);
		
		String sqlWitteTruiUitslag = "delete from wittetruiuitslag";
		getJdbcTemplate().execute(sqlWitteTruiUitslag);
		
		String sqlTeams = "delete from team";
		getJdbcTemplate().execute(sqlTeams);
		
		String sqlDeelnemer = "delete from deelnemer";
		getJdbcTemplate().execute(sqlDeelnemer);
		
		String sqlRenner = "delete from renner";
		getJdbcTemplate().execute(sqlRenner);
		
		String sqlSteden = "delete from stad";
		getJdbcTemplate().execute(sqlSteden);
		
		String sqlUitslagBedrag = "delete from uitslag_bedrag";
		getJdbcTemplate().execute(sqlUitslagBedrag);
	}
	
	public void deleteAllTeamRecords() {
		String sqlDeelnemerRenner = "delete from deelnemer_renner";
		getJdbcTemplate().execute(sqlDeelnemerRenner);
	}

}
