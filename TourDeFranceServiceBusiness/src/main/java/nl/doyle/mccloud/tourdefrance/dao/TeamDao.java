package nl.doyle.mccloud.tourdefrance.dao;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.valueobjects.Team;

public interface TeamDao {
	
	public List<Team> loadAllTeams();
	public Team loadTeam(final int teamNummer);
	public void saveTeam(final Team saveTeam);
	public void deleteTeam(final Team delTeam);
	//public void deleteAllTeams();
}
