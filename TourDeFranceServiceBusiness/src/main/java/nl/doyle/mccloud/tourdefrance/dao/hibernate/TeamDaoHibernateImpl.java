package nl.doyle.mccloud.tourdefrance.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.dao.TeamDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Team;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TeamDaoHibernateImpl extends HibernateDaoSupport implements TeamDao {

	public void deleteTeam(Team delTeam) {
		getHibernateTemplate().delete(delTeam);
	}

	/**
	 * Laadt alle teams uit de database.
	 * 
	 * @return lijst met alle teams
	 * @see Team
	 */
	@SuppressWarnings("unchecked")
	public List<Team> loadAllTeams() {
		return getHibernateTemplate().loadAll(Team.class);
	}

	@SuppressWarnings("unchecked")
	public Team loadTeam(int teamNummer) {
		List<Team> result = getHibernateTemplate().find("from Team team where team.nummer=?", new Integer(teamNummer));
		//	We hebben op primary key gezocht, dus er is maar 1 ploegentijdrit (of geen) in de lijst
		Team returnTeam = null;
		Iterator<Team> listIterator = result.iterator();
		if (listIterator.hasNext()) {
			returnTeam = (Team) listIterator.next();
		}
		return returnTeam;
	}

	public void saveTeam(Team saveTeam) {
		getHibernateTemplate().save(saveTeam);		
	}

}