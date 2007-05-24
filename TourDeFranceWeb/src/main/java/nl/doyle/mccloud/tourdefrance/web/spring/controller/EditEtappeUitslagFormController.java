package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import nl.doyle.mccloud.tourdefrance.controller.TourFacade;
import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.BolletjesTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.EtappeUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.GeleTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.GroeneTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;
import nl.doyle.mccloud.tourdefrance.web.spring.command.BolletjesTruiUitslagCommand;
import nl.doyle.mccloud.tourdefrance.web.spring.command.EtappeUitslagCommand;
import nl.doyle.mccloud.tourdefrance.web.spring.command.GeleTruiUitslagCommand;
import nl.doyle.mccloud.tourdefrance.web.spring.command.GroeneTruiUitslagCommand;
import nl.doyle.mccloud.tourdefrance.web.spring.command.UitslagCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class EditEtappeUitslagFormController extends SimpleFormController {

	private static final Log logger = LogFactory.getLog(EditEtappeUitslagFormController.class);

	private RennerDao rennerDao;
	private Etappe dbEtappe;
	private TourFacade tourFacade;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(java.lang.Object)
	 */
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		// TODO Auto-generated method stub
		return super.onSubmit(command);
	}

	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		logger.debug("Instantiating formBackingObject");
		//Zet het Etappe object op null zodat de waarde uit hetzelfde scherm in dezelfde sessie verwijderd wordt.
		dbEtappe = null;
		//Maak een nieuw FormBackingObject aan
		EtappeUitslagCommand etappeUitslag = new EtappeUitslagCommand();
		//TODO Dit moet goed geimplementeerd worden zodat er geen verkeerde waardes ingevuld kunnen worden. Security
    	Integer etappeNummer = ServletRequestUtils.getIntParameter(request,"etappe");
    	//check of deze parameter wel is ingevuld. Zoniet dan is 'nummer' 'null'.
    	int nummer;
    	if (etappeNummer != null) {
    		nummer  = etappeNummer;
    	} else {
    		throw new EditEtappeFormRequestException("Etappenummer niet opgegeven.");
    	}
    	logger.debug("Etappenummer = " + nummer);
    
    	//Haal de etappe op uit de database
    	EtappeUitslagCommand etappe = new EtappeUitslagCommand();
    	dbEtappe = tourFacade.getEtappeWithUitslag(nummer);
    	if (dbEtappe != null) {
    		//Haal de renners op uit de DB
    		etappe.setRenners(rennerDao.loadAllRenners());
    		etappe.setDatum(dbEtappe.getDatum());
    		etappe.setEtappenummer(dbEtappe.getEtappenummer());
    		etappe.setStartPlaats(dbEtappe.getStartplaats());
    		etappe.setFinishPlaats(dbEtappe.getFinishplaats());
    		//Zet nu de geleTruiUitslag rennernummers goed in het command object
    		
    		etappe.setGeleTruiUitslag(setGeleTruiUitslagCommand(etappe.getRenners(), dbEtappe.getGeleTruiUitslag()));
    		etappe.setGroeneTruiUitslag(setGroeneTruiUitslagCommand(etappe.getRenners(), dbEtappe.getGroeneTruiUitslag()));
    		etappe.setBolletjesTruiUitslag(setBolletjesTruiUitslagCommand(etappe.getRenners(), dbEtappe.getBolletjesTruiUitslag()));
    		
    		//Als het een standaardEtappe is dan moet ook de normale uitslag gevuld worden.
    		if (dbEtappe instanceof StandaardEtappe) {
    			etappe.setPloegenTijdrit(false);
    			etappe.setUitslag(setUitslagCommand(etappe.getRenners(), ((StandaardEtappe) dbEtappe).getEtappeUitslag()));
    		} else {
    			etappe.setPloegenTijdrit(true);
    		}
    	} else {
    		throw new EditEtappeUitslagFormRequestException("Etappe niet gevonden in database.");
    	}
    	logger.debug("FormBackingObject geinstantieerd.");
    	return etappe;
	}
	
	private GeleTruiUitslagCommand setGeleTruiUitslagCommand(List<Renner> renners, Set<GeleTruiUitslag> geleTruiUitslag) {
		GeleTruiUitslagCommand geleTrui = new GeleTruiUitslagCommand();
		for (GeleTruiUitslag nextUitslag: geleTruiUitslag) {
			int rennernummer = nextUitslag.getRenner().getNummer();
			switch (nextUitslag.getPositie()) {
			case 1:
				geleTrui.setEerste(rennernummer);
				break;
			case 2:
				geleTrui.setTweede(rennernummer);
				break;
			case 3:
				geleTrui.setDerde(rennernummer);
				break;
			case 4:
				geleTrui.setVierde(rennernummer);
				break;
			case 5:
				geleTrui.setVijfde(rennernummer);
				break;
			case 6:
				geleTrui.setZesde(rennernummer);
				break;
			case 7:
				geleTrui.setZevende(rennernummer);
				break;
			case 8:
				geleTrui.setAchtste(rennernummer);
				break;
			case 9:
				geleTrui.setNegende(rennernummer);
				break;
			case 10:
				geleTrui.setTiende(rennernummer);
				break;
			case 11:
				geleTrui.setElfde(rennernummer);
				break;
			case 12:
				geleTrui.setTwaalfde(rennernummer);
				break;
			case 13:
				geleTrui.setDertiende(rennernummer);
				break;
			case 14:
				geleTrui.setVeertiende(rennernummer);
				break;
			case 15:
				geleTrui.setVijftiende(rennernummer);
				break;
			}
		
		}
		return geleTrui;
	}
	
	private GroeneTruiUitslagCommand setGroeneTruiUitslagCommand(List<Renner> renners, Set<GroeneTruiUitslag> groeneTruiUitslag) {
		GroeneTruiUitslagCommand groeneTrui = new GroeneTruiUitslagCommand();
		for (GroeneTruiUitslag nextUitslag: groeneTruiUitslag) {
			int rennernummer = nextUitslag.getRenner().getNummer();
			switch (nextUitslag.getPositie()) {
			case 1:
				groeneTrui.setEerste(rennernummer);
				break;
			case 2:
				groeneTrui.setTweede(rennernummer);
				break;
			case 3:
				groeneTrui.setDerde(rennernummer);
				break;
			case 4:
				groeneTrui.setVierde(rennernummer);
				break;
			case 5:
				groeneTrui.setVijfde(rennernummer);
				break;
			case 6:
				groeneTrui.setZesde(rennernummer);
				break;
			case 7:
				groeneTrui.setZevende(rennernummer);
				break;
			case 8:
				groeneTrui.setAchtste(rennernummer);
				break;
			case 9:
				groeneTrui.setNegende(rennernummer);
				break;
			case 10:
				groeneTrui.setTiende(rennernummer);
				break;
			case 11:
				groeneTrui.setElfde(rennernummer);
				break;
			case 12:
				groeneTrui.setTwaalfde(rennernummer);
				break;
			case 13:
				groeneTrui.setDertiende(rennernummer);
				break;
			case 14:
				groeneTrui.setVeertiende(rennernummer);
				break;
			case 15:
				groeneTrui.setVijftiende(rennernummer);
				break;
			}
		
		}
		return groeneTrui;
	}
	
	private BolletjesTruiUitslagCommand setBolletjesTruiUitslagCommand(List<Renner> renners, Set<BolletjesTruiUitslag> bolletjesTruiUitslag) {
		BolletjesTruiUitslagCommand bolletjesTrui = new BolletjesTruiUitslagCommand();
		for (BolletjesTruiUitslag nextUitslag: bolletjesTruiUitslag) {
			int rennernummer = nextUitslag.getRenner().getNummer();
			switch (nextUitslag.getPositie()) {
			case 1:
				bolletjesTrui.setEerste(rennernummer);
				break;
			case 2:
				bolletjesTrui.setTweede(rennernummer);
				break;
			case 3:
				bolletjesTrui.setDerde(rennernummer);
				break;
			case 4:
				bolletjesTrui.setVierde(rennernummer);
				break;
			case 5:
				bolletjesTrui.setVijfde(rennernummer);
				break;
			case 6:
				bolletjesTrui.setZesde(rennernummer);
				break;
			case 7:
				bolletjesTrui.setZevende(rennernummer);
				break;
			case 8:
				bolletjesTrui.setAchtste(rennernummer);
				break;
			case 9:
				bolletjesTrui.setNegende(rennernummer);
				break;
			case 10:
				bolletjesTrui.setTiende(rennernummer);
				break;
			case 11:
				bolletjesTrui.setElfde(rennernummer);
				break;
			case 12:
				bolletjesTrui.setTwaalfde(rennernummer);
				break;
			case 13:
				bolletjesTrui.setDertiende(rennernummer);
				break;
			case 14:
				bolletjesTrui.setVeertiende(rennernummer);
				break;
			case 15:
				bolletjesTrui.setVijftiende(rennernummer);
				break;
			}
		
		}
		return bolletjesTrui;
	}
	
	private UitslagCommand setUitslagCommand(List<Renner> renners, Set<EtappeUitslag> uitslag) {
		UitslagCommand uitslagCommand = new UitslagCommand();
		for (EtappeUitslag nextUitslag: uitslag) {
			int rennernummer = nextUitslag.getRenner().getNummer();
			switch (nextUitslag.getPositie()) {
			case 1:
				uitslagCommand.setEerste(rennernummer);
				break;
			case 2:
				uitslagCommand.setTweede(rennernummer);
				break;
			case 3:
				uitslagCommand.setDerde(rennernummer);
				break;
			case 4:
				uitslagCommand.setVierde(rennernummer);
				break;
			case 5:
				uitslagCommand.setVijfde(rennernummer);
				break;
			case 6:
				uitslagCommand.setZesde(rennernummer);
				break;
			case 7:
				uitslagCommand.setZevende(rennernummer);
				break;
			case 8:
				uitslagCommand.setAchtste(rennernummer);
				break;
			case 9:
				uitslagCommand.setNegende(rennernummer);
				break;
			case 10:
				uitslagCommand.setTiende(rennernummer);
				break;
			case 11:
				uitslagCommand.setElfde(rennernummer);
				break;
			case 12:
				uitslagCommand.setTwaalfde(rennernummer);
				break;
			case 13:
				uitslagCommand.setDertiende(rennernummer);
				break;
			case 14:
				uitslagCommand.setVeertiende(rennernummer);
				break;
			case 15:
				uitslagCommand.setVijftiende(rennernummer);
				break;
			}
		
		}
		return uitslagCommand;
	}
 


	/**
	 * @return the rennerDao
	 */
	public RennerDao getRennerDao() {
		return rennerDao;
	}

	/**
	 * @param rennerDao the rennerDao to set
	 */
	public void setRennerDao(RennerDao rennerDao) {
		this.rennerDao = rennerDao;
	}


	/**
	 * @return the dbEtappe
	 */
	public Etappe getDbEtappe() {
		return dbEtappe;
	}

	/**
	 * @param dbEtappe the dbEtappe to set
	 */
	public void setDbEtappe(Etappe dbEtappe) {
		this.dbEtappe = dbEtappe;
	}

	/**
	 * @return the tourFacade
	 */
	public TourFacade getTourFacade() {
		return tourFacade;
	}


	/**
	 * @param tourFacade the tourFacade to set
	 */
	public void setTourFacade(TourFacade tourFacade) {
		this.tourFacade = tourFacade;
	}
	
	


	
	
	
	
	
	
	
	
	
}
