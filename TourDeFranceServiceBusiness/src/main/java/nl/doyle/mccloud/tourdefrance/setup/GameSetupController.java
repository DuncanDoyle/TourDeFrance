package nl.doyle.mccloud.tourdefrance.setup;

public interface GameSetupController {

	public void initializeGame(final int aantalPloegen, final int aantalEtappes, final int ploegenTijdrit);
	public void generateDeelnemerTeams();
	public void generateTestData();
	

}
