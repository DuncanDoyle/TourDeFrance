package nl.doyle.mccloud.tourdefrance.dao.jpa;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.TeamDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Team;

import org.springframework.orm.jpa.support.JpaDaoSupport;

public class TeamDaoJpaImpl extends JpaDaoSupport implements TeamDao {

	/**
	 * The name of the {@link Team} class as a {@link String}.
	 */
	private static final String TEAM_CLASS_NAME = Team.class.getName();

	/**
	 * Removes the given {@link Team} from the database.
	 * 
	 * @param the
	 *            {@link Team} to be removed.
	 */
	public void deleteTeam(final Team delTeam) {
		getJpaTemplate().remove(delTeam);
	}

	/**
	 * Laadt alle teams uit de database.
	 * 
	 * @return lijst met alle teams
	 * @see Team
	 */
	@SuppressWarnings("unchecked")
	public List<Team> loadAllTeams() {
		return getJpaTemplate().find("from " + TEAM_CLASS_NAME);
	}

	/**
	 * Loads the {@link Team} with the given number from the database.
	 * 
	 * @param teamNummer
	 *            the {@link Team} number.
	 * 
	 * @return the {@link Team} with the given number.
	 */
	public Team loadTeam(final int teamNummer) {
		return getJpaTemplate().find(Team.class, teamNummer);
	}

	/**
	 * Stores or updates the given {@link Team} in the database.
	 * 
	 * @param saveTeam
	 *            the {@link Team} to be stored.
	 */
	public void saveTeam(final Team saveTeam) {
		getJpaTemplate().merge(saveTeam);
	}

}