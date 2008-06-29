package nl.doyle.mccloud.tourdefrance.web.spring.controller.visitor;

import java.util.Set;

import nl.doyle.mccloud.tourdefrance.config.TourConfig;
import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.AbstractEtappeAndEindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.BolletjesTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.EindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.EtappeUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.GeleTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.GroeneTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.visitor.ValueObjectVisitor;
import nl.doyle.mccloud.tourdefrance.web.spring.command.EtappeUitslagCommand;
import nl.doyle.mccloud.tourdefrance.web.spring.command.EtappeUitslagCommand.EtappeType;
import nl.doyle.mccloud.tourdefrance.web.spring.controller.EditEtappeFormController;
import nl.doyle.mccloud.tourdefrance.web.spring.controller.EditEtappeUitslagFormController;

/**
 * Visitor for the {@link EditEtappeFormController} which gets the values from the value objects of the business
 * layer and sets them on the {@link EtappeUitslagCommand}.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class EditEtappeUitslagFormBackingObjectVisitor implements ValueObjectVisitor {

	/**
	 * The stage result command. Used in the {@link EditEtappeUitslagFormController}.
	 */
	private EtappeUitslagCommand stageResultCommand;

	/**
	 * The renner dao.
	 */
	private RennerDao rennerDao = null;

	/**
	 * The tour config object.
	 */
	private TourConfig config = null;

	/**
	 * Constructor which sets the {@link #stageResultCommand}.
	 * 
	 * @param stageResultCommand
	 *            the command object to set
	 */
	public EditEtappeUitslagFormBackingObjectVisitor(RennerDao rennerDao, TourConfig config) {
		this.rennerDao = rennerDao;
		this.config = config;
	}

	/**
	 * Visits a {@link StandaardEtappe} value object.
	 * 
	 * @param stage
	 *            the {@link StandaardEtappe} to visit
	 */
	public void visit(StandaardEtappe stage) {
		stageResultCommand = new EtappeUitslagCommand(config.getAantalEtappeUitslagen(), config.getAantalEtappeGeleTruiUitslagen(), config
				.getAantalEtappeGroeneTruiUitslagen(), config.getAantalEtappeBolletjesTruiUitslagen());
		// Set the values
		setValuesForAll(stageResultCommand, stage);
		setValuesForStandaardEtappeAndPloegenTijdrit(stageResultCommand, stage);

		stageResultCommand.setTypeEtappe(EtappeType.Etappe);
		stageResultCommand.setUitslag(setUitslag(stage.getEtappeUitslag(), stageResultCommand.getUitslag()));

	}

	/**
	 * Visits a {@link PloegenTijdrit} value object.
	 * 
	 * @param stage
	 *            the {@link PloegenTijdrit} to visit
	 */
	public void visit(PloegenTijdrit stage) {
		stageResultCommand = new EtappeUitslagCommand(config.getAantalEtappeUitslagen(), config.getAantalEtappeGeleTruiUitslagen(), config
				.getAantalEtappeGroeneTruiUitslagen(), config.getAantalEtappeBolletjesTruiUitslagen());
		setValuesForAll(stageResultCommand, stage);
		setValuesForStandaardEtappeAndPloegenTijdrit(stageResultCommand, stage);
		stageResultCommand.setTypeEtappe(EtappeType.PloegenTijdrit);

	}

	/**
	 * Visits a {@link EindUitslag} value object.
	 * 
	 * @param endResult
	 *            the {@link EindUitslag} to visit
	 */
	public void visit(EindUitslag endResult) {
		stageResultCommand = new EtappeUitslagCommand(0, config.getAantalEinduitslagGeleTruiUitslagen(), config
				.getAantalEinduitslagGroeneTruiUitslagen(), config.getAantalEinduitslagBolletjesTruiUitslagen());
		// set the values
		setValuesForAll(stageResultCommand, endResult);

		if (endResult.getWitteTrui() != null) {
			stageResultCommand.setWitteTrui(endResult.getWitteTrui().getNummer());
		}
		if (endResult.getRodeLantaren() != null) {
			stageResultCommand.setRodeLantaren(endResult.getRodeLantaren().getNummer());
		}
		if (endResult.getEersteUitvaller() != null) {
			stageResultCommand.setEersteUitvaller(endResult.getEersteUitvaller().getNummer());
		}
		stageResultCommand.setTypeEtappe(EtappeType.EindUitslag);

	}

	/**
	 * Method which sets values which are common for a {@link StandaardEtappe} and {@link PloegenTijdrit}.
	 * 
	 * @param currentStageResultCommand
	 *            the stage result command object
	 * @param stage
	 *            the stage
	 */
	private void setValuesForStandaardEtappeAndPloegenTijdrit(final EtappeUitslagCommand currentStageResultCommand, final Etappe stage) {
		currentStageResultCommand.setStartPlaats(stage.getStartplaats());
		currentStageResultCommand.setFinishPlaats(stage.getFinishplaats());
		currentStageResultCommand.setDatum(stage.getDatum());
	}

	/**
	 * Method which sets values which are common for all {@link AbstractEtappeAndEindUitslag}.
	 * 
	 * @param currentStageResultCommand
	 *            the stage result command object
	 * @param stageOrEndResult
	 *            the stage or endresult
	 */
	private void setValuesForAll(final EtappeUitslagCommand currentStageResultCommand, final AbstractEtappeAndEindUitslag stageOrEndResult) {
		if (stageOrEndResult.getMostCombativeRacer() != null) {
			currentStageResultCommand.setMostCombative(stageOrEndResult.getMostCombativeRacer().getNummer());
		}

		// Retrieve racers from DB.
		currentStageResultCommand.setRenners(rennerDao.loadAllRennersOrdered());
		currentStageResultCommand.setEtappenummer(stageOrEndResult.getEtappenummer());
		currentStageResultCommand.setOmschrijving(stageOrEndResult.getOmschrijving());
		// Zet nu de geleTruiUitslag rennernummers goed in het command object
		currentStageResultCommand.setGeleTruiUitslag(setGeleTruiUitslag(stageOrEndResult.getGeleTruiUitslag(), currentStageResultCommand
				.getGeleTruiUitslag()));
		currentStageResultCommand.setGroeneTruiUitslag(setGroeneTruiUitslag(stageOrEndResult.getGroeneTruiUitslag(),
				currentStageResultCommand.getGroeneTruiUitslag()));
		currentStageResultCommand.setBolletjesTruiUitslag(setBolletjesTruiUitslag(stageOrEndResult.getBolletjesTruiUitslag(),
				currentStageResultCommand.getBolletjesTruiUitslag()));
	}

	/**
	 * @return the stageResultCommand
	 */
	public EtappeUitslagCommand getStageResultCommand() {
		return stageResultCommand;
	}

	/**
	 * Returns an integer array with the racer numbers of this yellow jersey result.
	 * 
	 * @param geleTruiUitslag
	 *            the set which contains the yellow jersey results
	 * @param currentUitslag the int array with the current results. Needed to determine the lengt of the array that will be returned 
	 *            
	 * @return the int array with the racer numbers of the result
	 */
	private int[] setGeleTruiUitslag(final Set<GeleTruiUitslag> geleTruiUitslag, final int[] currentUitslag) {
		int[] uitslag = new int[currentUitslag.length];
		int uitslagArrayLength = uitslag.length;
		for (GeleTruiUitslag nextGeleTrui : geleTruiUitslag) {
			// Bepaal of de uitslag wel in het array past.
			if (nextGeleTrui.getPositie() <= uitslagArrayLength) {
				uitslag[nextGeleTrui.getPositie() - 1] = nextGeleTrui.getRenner().getNummer();
			}
		}
		return uitslag;
	}

	/**
	 * Returns an integer array with the racer numbers of this green jersey result.
	 * 
	 * @param geleTruiUitslag
	 *            the set which contains the yellow jersey results
	 * @param currentUitslag the int array with the current results. Needed to determine the lengt of the array that will be returned 
	 *            
	 * @return the int array with the racer numbers of the result
	 */
	private int[] setGroeneTruiUitslag(final Set<GroeneTruiUitslag> groeneTruiUitslag, final int[] currentUitslag) {
		int[] uitslag = new int[currentUitslag.length];
		int uitslagArrayLength = uitslag.length;
		for (GroeneTruiUitslag nextGroeneTrui : groeneTruiUitslag) {
			// Bepaal of de uitslag wel in het array past.
			if (nextGroeneTrui.getPositie() <= uitslagArrayLength) {
				uitslag[nextGroeneTrui.getPositie() - 1] = nextGroeneTrui.getRenner().getNummer();
			}
		}
		return uitslag;
	}

	/**
	 * Returns an integer array with the racer numbers of this polka dot jersey result.
	 * 
	 * @param geleTruiUitslag
	 *            the set which contains the yellow jersey results
	 * @param currentUitslag the int array with the current results. Needed to determine the lengt of the array that will be returned 
	 *            
	 * @return the int array with the racer numbers of the result
	 */
	private int[] setBolletjesTruiUitslag(final Set<BolletjesTruiUitslag> bolletjesTruiUitslag, final int[] currentUitslag) {
		int[] uitslag = new int[currentUitslag.length];
		int uitslagArrayLength = uitslag.length;
		for (BolletjesTruiUitslag nextBolletjesTrui : bolletjesTruiUitslag) {
			// Bepaal of de uitslag wel in het array past.
			if (nextBolletjesTrui.getPositie() <= uitslagArrayLength) {
				uitslag[nextBolletjesTrui.getPositie() - 1] = nextBolletjesTrui.getRenner().getNummer();
			}
		}
		return uitslag;
	}

	/**
	 * Returns an integer array with the racer numbers of this stage result.
	 * 
	 * @param geleTruiUitslag
	 *            the set which contains the yellow jersey results
	 * @param currentUitslag the int array with the current results. Needed to determine the lengt of the array that will be returned 
	 *            
	 * @return the int array with the racer numbers of the result
	 */
	private int[] setUitslag(final Set<EtappeUitslag> etappeUitslag, final int[] currentUitslag) {
		int[] uitslag = new int[currentUitslag.length];
		int uitslagArrayLength = uitslag.length;
		for (EtappeUitslag nextEtappeUitslagi : etappeUitslag) {
			// Bepaal of de uitslag wel in het array past.
			if (nextEtappeUitslagi.getPositie() <= uitslagArrayLength) {
				uitslag[nextEtappeUitslagi.getPositie() - 1] = nextEtappeUitslagi.getRenner().getNummer();
			}
		}
		return uitslag;
	}

}
