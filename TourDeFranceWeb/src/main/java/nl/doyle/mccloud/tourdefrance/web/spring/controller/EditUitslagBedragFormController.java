package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import nl.doyle.mccloud.tourdefrance.config.TourConfig;
import nl.doyle.mccloud.tourdefrance.dao.UitslagBedragDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag.Categorien;
import nl.doyle.mccloud.tourdefrance.web.spring.command.UitslagBedragCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class EditUitslagBedragFormController extends SimpleFormController {
	
	private static final Log logger = LogFactory.getLog(EditUitslagBedragFormController.class);
	
	private TourConfig config;
	
	private List<UitslagBedrag> dbUitslagBedragen;
	private UitslagBedragDao uitslagBedragDao;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(Object command) throws ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug("Saving uitslagBedragen!!");
		}
		//Command object casten
		UitslagBedragCommand uitslagBedrag = (UitslagBedragCommand) command;
		
		if (logger.isDebugEnabled()) {
			for (int counter = 0; counter < uitslagBedrag.getEtappe().length; counter++) {
				logger.debug("UitslagBedrag Etappe positie " + (counter + 1) + "= " + uitslagBedrag.getEtappe()[counter]); 
				
			}
		}
		
		//Eerst voor de gewone etappe
		saveUitslagBedragen(uitslagBedrag.getEtappe(), Categorien.Etappe);
		saveUitslagBedragen(uitslagBedrag.getGeleTrui(), Categorien.GeleTrui);
		saveUitslagBedragen(uitslagBedrag.getGroeneTrui(), Categorien.GroeneTrui);
		saveUitslagBedragen(uitslagBedrag.getBolletjesTrui(), Categorien.BolletjesTrui);
		saveUitslagBedragen(uitslagBedrag.getGeleTruiEind(), Categorien.GeleTruiEind);
		saveUitslagBedragen(uitslagBedrag.getGroeneTruiEind(), Categorien.GroeneTruiEind);
		saveUitslagBedragen(uitslagBedrag.getBolletjesTruiEind(), Categorien.BolletjesTruiEind);
		
		
		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	private void saveUitslagBedragen(final double[] uitslagBedragen, final Categorien categorie) {
		
		for (int teller = 0; teller < uitslagBedragen.length; teller++) {
			//Loop nu door alle uitslagbedragen en kijk of de juiste entry gevonden kan worden.
			boolean found = false;
			for (UitslagBedrag nextUitslagBedrag: dbUitslagBedragen) {
				if (nextUitslagBedrag.getCategorie().equals(categorie) && nextUitslagBedrag.getPositie() == (teller + 1)) {
					found = true;
					//Als de waarde nog niet hetzelfde is als de waarde in de database dan slaan we de gegevens op.
					if (nextUitslagBedrag.getBedrag() != uitslagBedragen[teller]) {
						nextUitslagBedrag.setBedrag(uitslagBedragen[teller]);
						uitslagBedragDao.saveUitslagBedrag(nextUitslagBedrag);
					}
				}
			}
			if (found == false) {
				//entry is nog niet aanwezig in de DB. Nieuwe entry aanmaken en opslaan
				UitslagBedrag nieuwUitslagBedrag = new UitslagBedrag();
				nieuwUitslagBedrag.setCategorie(categorie);
				nieuwUitslagBedrag.setPositie(teller + 1);
				nieuwUitslagBedrag.setBedrag(uitslagBedragen[teller]);
				uitslagBedragDao.saveUitslagBedrag(nieuwUitslagBedrag);
			}
		}
	}
	
	
	
	/**
	 * Initialiseer het formbackingobject. In dit form zijn dat de UitslagenEnBedragen
	 * 
	 * 
	 */
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException, EditEtappeFormRequestException {
		//Gegevens van mogelijke vorige bezoek in sessie wissen
		dbUitslagBedragen = uitslagBedragDao.loadAllUitslagBedragen();
		UitslagBedragCommand uitslagBedrag = new UitslagBedragCommand(config.getAantalEtappeUitslagen(), 
																		config.getAantalEtappeGeleTruiUitslagen(), 
																		config.getAantalEtappeGroeneTruiUitslagen(),
																		config.getAantalEtappeBolletjesTruiUitslagen(),
																		config.getAantalEinduitslagGeleTruiUitslagen(),
																		config.getAantalEinduitslagGroeneTruiUitslagen(),
																		config.getAantalEinduitslagBolletjesTruiUitslagen());
		//loop door alle uitslagen heen en zet de waardes goed
		for (UitslagBedrag nextUitslagBedrag: dbUitslagBedragen) {
			switch (nextUitslagBedrag.getCategorie()) {
			case Etappe:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getEtappe().length) {
					uitslagBedrag.getEtappe()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case GeleTrui:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getGeleTrui().length) {
					uitslagBedrag.getGeleTrui()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case GroeneTrui:	
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getGroeneTrui().length) {
					uitslagBedrag.getGroeneTrui()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case BolletjesTrui:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getBolletjesTrui().length) {
					uitslagBedrag.getBolletjesTrui()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case GeleTruiEind:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getGeleTruiEind().length) {
					uitslagBedrag.getGeleTruiEind()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case GroeneTruiEind:	
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getGroeneTruiEind().length) {
					uitslagBedrag.getGroeneTruiEind()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			case BolletjesTruiEind:
				if (nextUitslagBedrag.getPositie() <= uitslagBedrag.getBolletjesTruiEind().length) {
					uitslagBedrag.getBolletjesTruiEind()[nextUitslagBedrag.getPositie() - 1] = nextUitslagBedrag.getBedrag();
				}
				break;
			}
			
			
		}
		return uitslagBedrag;
	}

	/**
	 * @return the uitslagBedragDao
	 */
	public UitslagBedragDao getUitslagBedragDao() {
		return uitslagBedragDao;
	}

	/**
	 * @param uitslagBedragDao the uitslagBedragDao to set
	 */
	public void setUitslagBedragDao(UitslagBedragDao uitslagBedragDao) {
		this.uitslagBedragDao = uitslagBedragDao;
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
