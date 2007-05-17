package nl.doyle.mccloud.tourdefrance.setup.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class GameSetupDaoImpl extends JdbcDaoSupport implements GameSetupDao {

	/**
	 * @author mccloud
	 * 
	 * Verwijdert alle data uit de database.
	 * Via Spring AOP wordt er een JDBC transactie om deze SQL commando's heen gezet.
	 */
	public void deleteAllRecordsFromDatabase() {
		String sqlDeelnemerRenner = "delete from deelnemer_renner";
		getJdbcTemplate().execute(sqlDeelnemerRenner);
		
		String sqlTeamRenner = "delete from team_renner";
		getJdbcTemplate().execute(sqlTeamRenner);
		
		//Maak daarna de rest van de tabellen leeg
		
		String sqlEtappeUitslag = "delete from etappeuitslag";
		getJdbcTemplate().execute(sqlEtappeUitslag);
				
		String sqlGeleTruiUitslag = "delete from geletruiuitslag";
		getJdbcTemplate().execute(sqlGeleTruiUitslag);
				
		String sqlGroeneTruiUitslag = "delete from groenetruiuitslag";
		getJdbcTemplate().execute(sqlGroeneTruiUitslag);
				
		String sqlBolletjesTruiUitslag = "delete from bolletjestruiuitslag";
		getJdbcTemplate().execute(sqlBolletjesTruiUitslag);
		
		String sqlTeams = "delete from team";
		getJdbcTemplate().execute(sqlTeams);
		
		String sqlDeelnemer = "delete from deelnemer";
		getJdbcTemplate().execute(sqlDeelnemer);
		
		String sqlRenner = "delete from renner";
		getJdbcTemplate().execute(sqlRenner);
		
		String sqlStandaardEtappes = "delete from standaardetappe";
		getJdbcTemplate().execute(sqlStandaardEtappes);
		
		String sqlPloegentijdritten = "delete from ploegentijdrit";
		getJdbcTemplate().execute(sqlPloegentijdritten);
		
		String sqlEtappes = "delete from etappe";
		getJdbcTemplate().execute(sqlEtappes);
		
		String sqlSteden = "delete from stad";
		getJdbcTemplate().execute(sqlSteden);
		
	}

}
