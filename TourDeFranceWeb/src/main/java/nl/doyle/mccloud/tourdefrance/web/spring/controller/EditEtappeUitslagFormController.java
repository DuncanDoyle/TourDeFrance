package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import nl.doyle.mccloud.tourdefrance.config.TourConfig;
import nl.doyle.mccloud.tourdefrance.controller.TourFacade;
import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.AbstractEtappeAndEindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.BolletjesTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.EindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.EtappeUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.GeleTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.GroeneTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;
import nl.doyle.mccloud.tourdefrance.web.spring.command.EtappeUitslagCommand;
import nl.doyle.mccloud.tourdefrance.web.spring.command.EtappeUitslagCommand.EtappeType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class EditEtappeUitslagFormController extends SimpleFormController {

	private static final Log logger = LogFactory.getLog(EditEtappeUitslagFormController.class);

	private TourConfig config;
	
	private RennerDao rennerDao;
	private AbstractEtappeAndEindUitslag dbEtappe;
	private TourFacade tourFacade;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(java.lang.Object)
	 */
	@Override
	protected ModelAndView onSubmit(Object command) throws Exception {
		//Command object casten
		EtappeUitslagCommand etappeUitslag = (EtappeUitslagCommand) command;
		
		if (logger.isDebugEnabled()) {
			for(int teller = 0; teller < etappeUitslag.getUitslag().length; teller++) {
				logger.debug("Uitslag " + (teller + 1) + " = " + etappeUitslag.getUitslag()[teller]);
			}
		}
		//GeleTruiUitslag
		for(int teller = 0; teller < etappeUitslag.getGeleTruiUitslag().length; teller++) {
			//Check nu of de waarde niet op 0 is gezet
			if (etappeUitslag.getGeleTruiUitslag()[teller] != 0) {
				//zoek de juiste uitslag op
				boolean found = false;
				Iterator<GeleTruiUitslag> dbEtappeUitslagIter = dbEtappe.getGeleTruiUitslag().iterator();
				while (dbEtappeUitslagIter.hasNext() && !found) {
					GeleTruiUitslag nextUitslag = dbEtappeUitslagIter.next();
					if (nextUitslag.getPositie() == teller + 1) {
						found = true;
						if (nextUitslag.getRenner().getNummer() != etappeUitslag.getGeleTruiUitslag()[teller]) {
							//Check nu of de waarde niet op 0 is gezet
							if (etappeUitslag.getGeleTruiUitslag()[teller] != 0) {
								nextUitslag.setRenner(rennerDao.loadRenner(etappeUitslag.getGeleTruiUitslag()[teller]));
							} else {
								//TODO Nog iets verzinnen om uitslagen te verwijderen als ze op 0 gezet zijn.
							}
						}
					}
				}
				//als de uitslag niet is gevonden maken we een nieuwe aan
				if (!found) {
					GeleTruiUitslag nieuweUitslag = new GeleTruiUitslag();
					nieuweUitslag.setEtappenummer(dbEtappe.getEtappenummer());
					nieuweUitslag.setRenner(rennerDao.loadRenner(etappeUitslag.getGeleTruiUitslag()[teller]));
					nieuweUitslag.setPositie(teller + 1);
					dbEtappe.getGeleTruiUitslag().add(nieuweUitslag);
				}
			}
		}
		//GroeneTrui
		for(int teller = 0; teller < etappeUitslag.getGroeneTruiUitslag().length; teller++) {
			//Check nu of de waarde niet op 0 is gezet
			if (etappeUitslag.getGroeneTruiUitslag()[teller] != 0) {
				//zoek de juiste uitslag op
				boolean found = false;
				Iterator<GroeneTruiUitslag> dbEtappeUitslagIter = dbEtappe.getGroeneTruiUitslag().iterator();
				while (dbEtappeUitslagIter.hasNext() && !found) {
					GroeneTruiUitslag nextUitslag = dbEtappeUitslagIter.next();
					if (nextUitslag.getPositie() == teller + 1) {
						found = true;
						if (nextUitslag.getRenner().getNummer() != etappeUitslag.getGroeneTruiUitslag()[teller]) {
							//Check nu of de waarde niet op 0 is gezet
							if (etappeUitslag.getGroeneTruiUitslag()[teller] != 0) {
								nextUitslag.setRenner(rennerDao.loadRenner(etappeUitslag.getGroeneTruiUitslag()[teller]));
							} else {
								//TODO Nog iets verzinnen om uitslagen te verwijderen als ze op 0 gezet zijn.
							}
						}
					}
				}
				//als de uitslag niet is gevonden maken we een nieuwe aan
				if (!found) {
					GroeneTruiUitslag nieuweUitslag = new GroeneTruiUitslag();
					nieuweUitslag.setEtappenummer(dbEtappe.getEtappenummer());
					nieuweUitslag.setRenner(rennerDao.loadRenner(etappeUitslag.getGroeneTruiUitslag()[teller]));
					nieuweUitslag.setPositie(teller + 1);
					dbEtappe.getGroeneTruiUitslag().add(nieuweUitslag);
				}
			}
		}
		
		//BolletjesTrui
		for(int teller = 0; teller < etappeUitslag.getBolletjesTruiUitslag().length; teller++) {
			//Check nu of de waarde niet op 0 is gezet
			if (etappeUitslag.getBolletjesTruiUitslag()[teller] != 0) {
				//zoek de juiste uitslag op
				boolean found = false;
				Iterator<BolletjesTruiUitslag> dbEtappeUitslagIter = dbEtappe.getBolletjesTruiUitslag().iterator();
				while (dbEtappeUitslagIter.hasNext() && !found) {
					BolletjesTruiUitslag nextUitslag = dbEtappeUitslagIter.next();
					if (nextUitslag.getPositie() == teller + 1) {
						found = true;
						if (nextUitslag.getRenner().getNummer() != etappeUitslag.getBolletjesTruiUitslag()[teller]) {
							
							nextUitslag.setRenner(rennerDao.loadRenner(etappeUitslag.getBolletjesTruiUitslag()[teller]));
						}
					}
				}
				//als de uitslag niet is gevonden maken we een nieuwe aan
				if (!found) {
					BolletjesTruiUitslag nieuweUitslag = new BolletjesTruiUitslag();
					nieuweUitslag.setEtappenummer(dbEtappe.getEtappenummer());
					nieuweUitslag.setRenner(rennerDao.loadRenner(etappeUitslag.getBolletjesTruiUitslag()[teller]));
					nieuweUitslag.setPositie(teller + 1);
					dbEtappe.getBolletjesTruiUitslag().add(nieuweUitslag);
				}
			}
		}
				
		//Nu voor de gewone uitslag. Check eerst of dit een standaardetappe of een ploegentijdrit is.
		if (dbEtappe instanceof StandaardEtappe) {
			for(int teller = 0; teller < etappeUitslag.getUitslag().length; teller++) {
				//Check nu of de waarde niet op 0 is gezet
				if (etappeUitslag.getUitslag()[teller] != 0) {
					//zoek de juiste uitslag op
					boolean found = false;
					Iterator<EtappeUitslag> dbEtappeUitslagIter = ((StandaardEtappe)dbEtappe).getEtappeUitslag().iterator();
					while (dbEtappeUitslagIter.hasNext() && !found) {
						EtappeUitslag nextUitslag = dbEtappeUitslagIter.next();
						if (nextUitslag.getPositie() == teller + 1) {
							found = true;
							if (nextUitslag.getRenner().getNummer() != etappeUitslag.getUitslag()[teller]) {
								//Check nu of de waarde niet op 0 is gezet
								if (etappeUitslag.getUitslag()[teller] != 0) {
									nextUitslag.setRenner(rennerDao.loadRenner(etappeUitslag.getUitslag()[teller]));
								} else {
									//TODO Nog iets verzinnen om uitslagen te verwijderen als ze op 0 gezet zijn.
								}
							}
						}
					}
					//als de uitslag niet is gevonden maken we een nieuwe aan
					if (!found) {
						EtappeUitslag nieuweUitslag = new EtappeUitslag();
						nieuweUitslag.setEtappenummer(dbEtappe.getEtappenummer());
						nieuweUitslag.setRenner(rennerDao.loadRenner(etappeUitslag.getUitslag()[teller]));
						nieuweUitslag.setPositie(teller + 1);
						((StandaardEtappe) dbEtappe).getEtappeUitslag().add(nieuweUitslag);
					}
				}
			}
		} else if (dbEtappe instanceof EindUitslag) {
			logger.debug("Saving EindUitslag data.");
			((EindUitslag) dbEtappe).setWitteTrui(rennerDao.loadRenner(etappeUitslag.getWitteTrui()));
			((EindUitslag) dbEtappe).setRodeLantaren(rennerDao.loadRenner(etappeUitslag.getRodeLantaren()));
			((EindUitslag) dbEtappe).setEersteUitvaller(rennerDao.loadRenner(etappeUitslag.getEersteUitvaller()));
		}
		tourFacade.saveEtappe(dbEtappe);
				
		// TODO Auto-generated method stub
		return new ModelAndView(new RedirectView(getSuccessView() + "?etappe=" + etappeUitslag.getEtappenummer()));
	}

	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		logger.debug("Instantiating formBackingObject");
		//Zet het Etappe object op null zodat de waarde uit hetzelfde scherm in dezelfde sessie verwijderd wordt.
		dbEtappe = null;
		
		
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
    	dbEtappe = tourFacade.getEtappeWithUitslag(nummer);
    	EtappeUitslagCommand etappe = null;
    	
    	if (dbEtappe != null) {
    		//Maak een nieuw FormBackingObject aan
        	logger.debug("Instantiating new EtappeUitslagCommand.");
        	if (dbEtappe instanceof StandaardEtappe || dbEtappe instanceof PloegenTijdrit) {
        		etappe = new EtappeUitslagCommand(config.getAantalEtappeUitslagen(), config.getAantalEtappeGeleTruiUitslagen(), config.getAantalEtappeGroeneTruiUitslagen(), config.getAantalEtappeBolletjesTruiUitslagen());
        		etappe.setStartPlaats(((Etappe) dbEtappe).getStartplaats());
	    		etappe.setFinishPlaats(((Etappe) dbEtappe).getFinishplaats());
	    		etappe.setDatum(((Etappe) dbEtappe).getDatum());
	    		//Als het een standaardEtappe is dan moet ook de normale uitslag gevuld worden.
	    		if (dbEtappe instanceof StandaardEtappe) {
	    			etappe.setTypeEtappe(EtappeType.Etappe);
	    			etappe.setUitslag(setUitslag(etappe.getRenners(), ((StandaardEtappe) dbEtappe).getEtappeUitslag(), etappe.getUitslag()));
	    		} else {
	    			etappe.setTypeEtappe(EtappeType.PloegenTijdrit);
	    		}
        	} else {
        		etappe = new EtappeUitslagCommand(0, config.getAantalEinduitslagGeleTruiUitslagen(), config.getAantalEinduitslagGroeneTruiUitslagen(), config.getAantalEinduitslagBolletjesTruiUitslagen());
        		if (((EindUitslag) dbEtappe).getWitteTrui() != null) {
        			etappe.setWitteTrui(((EindUitslag) dbEtappe).getWitteTrui().getNummer());
        		}
        		if (((EindUitslag)dbEtappe).getRodeLantaren() != null) {
        			etappe.setRodeLantaren(((EindUitslag)dbEtappe).getRodeLantaren().getNummer());
        		}
        		if (((EindUitslag)dbEtappe).getEersteUitvaller() != null) {
        			etappe.setEersteUitvaller(((EindUitslag)dbEtappe).getEersteUitvaller().getNummer());
        		}
        		etappe.setTypeEtappe(EtappeType.EindUitslag);
        	}
        	//TODO Als de etappe geen StandaardEtappe, PloegenTijdrit of EindUitslag is gaat dit fout. Kan 'by contract' eigenlijk nooit gebeuren, maar toch.
        	//Haal de renners op uit de DB
        	etappe.setRenners(rennerDao.loadAllRennersOrdered());
        	etappe.setEtappenummer(dbEtappe.getEtappenummer());
        	etappe.setOmschrijving(dbEtappe.getOmschrijving());
    		//Zet nu de geleTruiUitslag rennernummers goed in het command object
    		etappe.setGeleTruiUitslag(setGeleTruiUitslag(etappe.getRenners(), dbEtappe.getGeleTruiUitslag(), etappe.getGeleTruiUitslag()));
    		etappe.setGroeneTruiUitslag(setGroeneTruiUitslag(etappe.getRenners(), dbEtappe.getGroeneTruiUitslag(), etappe.getGroeneTruiUitslag()));
    		etappe.setBolletjesTruiUitslag(setBolletjesTruiUitslag(etappe.getRenners(), dbEtappe.getBolletjesTruiUitslag(), etappe.getBolletjesTruiUitslag()));
    	} else {
    		throw new EditEtappeUitslagFormRequestException("Etappe niet gevonden in database.");
    	}
    	logger.debug("FormBackingObject geinstantieerd.");
    	return etappe;
    }
	
	private int[] setGeleTruiUitslag(final List<Renner> renners, final Set<GeleTruiUitslag> geleTruiUitslag, final int[] currentUitslag) {
		int[] uitslag = currentUitslag;  
		int uitslagArrayLength = uitslag.length;
		for (GeleTruiUitslag nextGeleTrui: geleTruiUitslag) {
			//Bepaal of de uitslag wel in het array past.
			if (nextGeleTrui.getPositie() <= uitslagArrayLength) {
				uitslag[nextGeleTrui.getPositie() - 1] = nextGeleTrui.getRenner().getNummer();
			}
		}
		return uitslag;
	}
	
	private int[] setGroeneTruiUitslag(final List<Renner> renners, final Set<GroeneTruiUitslag> groeneTruiUitslag, final int[] currentUitslag) {
		int[] uitslag = currentUitslag;  
		int uitslagArrayLength = uitslag.length;
		for (GroeneTruiUitslag nextGroeneTrui: groeneTruiUitslag) {
			//Bepaal of de uitslag wel in het array past.
			if (nextGroeneTrui.getPositie() <= uitslagArrayLength) {
				uitslag[nextGroeneTrui.getPositie() - 1] = nextGroeneTrui.getRenner().getNummer();
			}
		}
		return uitslag;
	}
	
	private int[] setBolletjesTruiUitslag(final List<Renner> renners, final Set<BolletjesTruiUitslag> bolletjesTruiUitslag, final int[] currentUitslag) {
		int[] uitslag = currentUitslag;  
		int uitslagArrayLength = uitslag.length;
		for (BolletjesTruiUitslag nextBolletjesTrui: bolletjesTruiUitslag) {
			//Bepaal of de uitslag wel in het array past.
			if (nextBolletjesTrui.getPositie() <= uitslagArrayLength) {
				uitslag[nextBolletjesTrui.getPositie() - 1] = nextBolletjesTrui.getRenner().getNummer();
			}
		}
		return uitslag;
	}
	
	private int[] setUitslag(final List<Renner> renners, final Set<EtappeUitslag> etappeUitslag, final int[] currentUitslag) {
		int[] uitslag = currentUitslag;  
		int uitslagArrayLength = uitslag.length;
		for (EtappeUitslag nextEtappeUitslagi: etappeUitslag) {
			//Bepaal of de uitslag wel in het array past.
			if (nextEtappeUitslagi.getPositie() <= uitslagArrayLength) {
				uitslag[nextEtappeUitslagi.getPositie() - 1] = nextEtappeUitslagi.getRenner().getNummer();
			}
		}
		return uitslag;
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
	public AbstractEtappeAndEindUitslag getDbEtappe() {
		return dbEtappe;
	}

	/**
	 * @param dbEtappe the dbEtappe to set
	 */
	public void setDbEtappe(AbstractEtappeAndEindUitslag dbEtappe) {
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


	/**
	 * @return the config
	 */
	public TourConfig getConfig() {
		return config;
	}


	/**
	 * @param config the config to set
	 */
	public void setConfig(TourConfig config) {
		this.config = config;
	}
	
	


	
	
	
	
	
	
	
	
	
}
