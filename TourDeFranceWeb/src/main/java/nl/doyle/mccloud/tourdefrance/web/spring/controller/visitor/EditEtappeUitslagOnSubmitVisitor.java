package nl.doyle.mccloud.tourdefrance.web.spring.controller.visitor;

import java.util.Iterator;

import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.AbstractEtappeAndEindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.BolletjesTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.EindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.EtappeUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.GeleTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.GroeneTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.visitor.ValueObjectVisitor;
import nl.doyle.mccloud.tourdefrance.web.spring.command.EtappeUitslagCommand;
import nl.doyle.mccloud.tourdefrance.web.spring.controller.EditEtappeFormController;
import nl.doyle.mccloud.tourdefrance.web.spring.controller.EditEtappeUitslagFormController;

/**
 * Visitor for the {@link EditEtappeFormController} which sets the values from the {@link EtappeUitslagCommand| on the value objects of the business
 * layer.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class EditEtappeUitslagOnSubmitVisitor implements ValueObjectVisitor {

	/**
	 * The stage result command. Used in the {@link EditEtappeUitslagFormController}.
	 */
	private EtappeUitslagCommand stageResultCommand;
	
	/**
	 * The renner dao.
	 */
	private RennerDao rennerDao = null;

	/**
	 * Constructor which sets the stage result command object to be used when the {@link AbstractEtappeAndEindUitslag} is sets.
	 * 
	 * @param stageResultCommand
	 *            the stage result command to be used
	 */
	public EditEtappeUitslagOnSubmitVisitor(EtappeUitslagCommand stageResultCommand, RennerDao rennerDao) {
		this.stageResultCommand = stageResultCommand;
		this.rennerDao = rennerDao;
	}
	
	/**
	 * Visits a {@link StandaardEtappe} value object.
	 * 
	 * @param stage
	 *            the {@link StandaardEtappe} to visit
	 */
	public void visit(StandaardEtappe stage) {
		for (int teller = 0; teller < stageResultCommand.getUitslag().length; teller++) {
			// Check nu of de waarde niet op 0 is gezet
			if (stageResultCommand.getUitslag()[teller] != 0) {
				// zoek de juiste uitslag op
				boolean found = false;
				Iterator<EtappeUitslag> dbEtappeUitslagIter = stage.getEtappeUitslag().iterator();
				while (dbEtappeUitslagIter.hasNext() && !found) {
					EtappeUitslag nextUitslag = dbEtappeUitslagIter.next();
					if (nextUitslag.getPositie() == teller + 1) {
						found = true;
						if (nextUitslag.getRenner().getNummer() != stageResultCommand.getUitslag()[teller]) {
							// Check nu of de waarde niet op 0 is gezet
							if (stageResultCommand.getUitslag()[teller] != 0) {
								nextUitslag.setRenner(rennerDao.loadRenner(stageResultCommand.getUitslag()[teller]));
							} else {
								// TODO Nog iets verzinnen om uitslagen te verwijderen als ze op 0 gezet zijn.
							}
						}
					}
				}
				// als de uitslag niet is gevonden maken we een nieuwe aan
				if (!found) {
					EtappeUitslag nieuweUitslag = new EtappeUitslag();
					nieuweUitslag.setEtappenummer(stage.getEtappenummer());
					nieuweUitslag.setRenner(rennerDao.loadRenner(stageResultCommand.getUitslag()[teller]));
					nieuweUitslag.setPositie(teller + 1);
					stage.getEtappeUitslag().add(nieuweUitslag);
				}
			}
		}
		//Save values common to endresults and stages
		setValuesForAll(stage, stageResultCommand);
	}

	/**
	 * Visits a {@link PloegenTijdrit} value object.
	 * 
	 * @param stage
	 *            the {@link PloegenTijdrit} to visit
	 */
	public void visit(PloegenTijdrit stage) {
		//Save values common to endresults and stages
		setValuesForAll(stage, stageResultCommand);
	}

	/**
	 * Visits a {@link EindUitslag} value object.
	 * 
	 * @param endResult
	 *            the {@link EindUitslag} to visit
	 */
	public void visit(EindUitslag endResult) {
		endResult.setWitteTrui(rennerDao.loadRenner(stageResultCommand.getWitteTrui()));
		endResult.setRodeLantaren(rennerDao.loadRenner(stageResultCommand.getRodeLantaren()));
		endResult.setEersteUitvaller(rennerDao.loadRenner(stageResultCommand.getEersteUitvaller()));
		//Save values common to endresults and stages
		setValuesForAll(endResult, stageResultCommand);

	}

	private void setValuesForAll(final AbstractEtappeAndEindUitslag stageOrEndResult, final EtappeUitslagCommand currentStageResultCommand) {
		//GeleTruiUitslag
		for (int teller = 0; teller < currentStageResultCommand.getGeleTruiUitslag().length; teller++) {
			// Check nu of de waarde niet op 0 is gezet
			if (currentStageResultCommand.getGeleTruiUitslag()[teller] != 0) {
				// zoek de juiste uitslag op
				boolean found = false;
				Iterator<GeleTruiUitslag> dbEtappeUitslagIter = stageOrEndResult.getGeleTruiUitslag().iterator();
				while (dbEtappeUitslagIter.hasNext() && !found) {
					GeleTruiUitslag nextUitslag = dbEtappeUitslagIter.next();
					if (nextUitslag.getPositie() == teller + 1) {
						found = true;
						if (nextUitslag.getRenner().getNummer() != currentStageResultCommand.getGeleTruiUitslag()[teller]) {
							// Check nu of de waarde niet op 0 is gezet
							if (currentStageResultCommand.getGeleTruiUitslag()[teller] != 0) {
								nextUitslag.setRenner(rennerDao.loadRenner(currentStageResultCommand.getGeleTruiUitslag()[teller]));
							} else {
								// TODO Nog iets verzinnen om uitslagen te verwijderen als ze op 0 gezet zijn.
							}
						}
					}
				}
				// als de uitslag niet is gevonden maken we een nieuwe aan
				if (!found) {
					GeleTruiUitslag nieuweUitslag = new GeleTruiUitslag();
					nieuweUitslag.setEtappenummer(stageOrEndResult.getEtappenummer());
					nieuweUitslag.setRenner(rennerDao.loadRenner(currentStageResultCommand.getGeleTruiUitslag()[teller]));
					nieuweUitslag.setPositie(teller + 1);
					stageOrEndResult.getGeleTruiUitslag().add(nieuweUitslag);
				}
			}
		}
		// GroeneTrui
		for (int teller = 0; teller < currentStageResultCommand.getGroeneTruiUitslag().length; teller++) {
			// Check nu of de waarde niet op 0 is gezet
			if (currentStageResultCommand.getGroeneTruiUitslag()[teller] != 0) {
				// zoek de juiste uitslag op
				boolean found = false;
				Iterator<GroeneTruiUitslag> dbEtappeUitslagIter = stageOrEndResult.getGroeneTruiUitslag().iterator();
				while (dbEtappeUitslagIter.hasNext() && !found) {
					GroeneTruiUitslag nextUitslag = dbEtappeUitslagIter.next();
					if (nextUitslag.getPositie() == teller + 1) {
						found = true;
						if (nextUitslag.getRenner().getNummer() != currentStageResultCommand.getGroeneTruiUitslag()[teller]) {
							// Check nu of de waarde niet op 0 is gezet
							if (currentStageResultCommand.getGroeneTruiUitslag()[teller] != 0) {
								nextUitslag.setRenner(rennerDao.loadRenner(currentStageResultCommand.getGroeneTruiUitslag()[teller]));
							} else {
								// TODO Nog iets verzinnen om uitslagen te verwijderen als ze op 0 gezet zijn.
							}
						}
					}
				}
				// als de uitslag niet is gevonden maken we een nieuwe aan
				if (!found) {
					GroeneTruiUitslag nieuweUitslag = new GroeneTruiUitslag();
					nieuweUitslag.setEtappenummer(stageOrEndResult.getEtappenummer());
					nieuweUitslag.setRenner(rennerDao.loadRenner(currentStageResultCommand.getGroeneTruiUitslag()[teller]));
					nieuweUitslag.setPositie(teller + 1);
					stageOrEndResult.getGroeneTruiUitslag().add(nieuweUitslag);
				}
			}
		}

		// BolletjesTrui
		for (int teller = 0; teller < currentStageResultCommand.getBolletjesTruiUitslag().length; teller++) {
			// Check nu of de waarde niet op 0 is gezet
			if (currentStageResultCommand.getBolletjesTruiUitslag()[teller] != 0) {
				// zoek de juiste uitslag op
				boolean found = false;
				Iterator<BolletjesTruiUitslag> dbEtappeUitslagIter = stageOrEndResult.getBolletjesTruiUitslag().iterator();
				while (dbEtappeUitslagIter.hasNext() && !found) {
					BolletjesTruiUitslag nextUitslag = dbEtappeUitslagIter.next();
					if (nextUitslag.getPositie() == teller + 1) {
						found = true;
						if (nextUitslag.getRenner().getNummer() != currentStageResultCommand.getBolletjesTruiUitslag()[teller]) {

							nextUitslag.setRenner(rennerDao.loadRenner(currentStageResultCommand.getBolletjesTruiUitslag()[teller]));
						}
					}
				}
				// als de uitslag niet is gevonden maken we een nieuwe aan
				if (!found) {
					BolletjesTruiUitslag nieuweUitslag = new BolletjesTruiUitslag();
					nieuweUitslag.setEtappenummer(stageOrEndResult.getEtappenummer());
					nieuweUitslag.setRenner(rennerDao.loadRenner(currentStageResultCommand.getBolletjesTruiUitslag()[teller]));
					nieuweUitslag.setPositie(teller + 1);
					stageOrEndResult.getBolletjesTruiUitslag().add(nieuweUitslag);
				}
			}
		}
		// Most Combative
		stageOrEndResult.setMostCombativeRacer(rennerDao.loadRenner(stageResultCommand.getMostCombative()));

	}
}
