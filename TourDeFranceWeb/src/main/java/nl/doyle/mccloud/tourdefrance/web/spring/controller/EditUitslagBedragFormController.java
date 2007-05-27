package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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
		
		
		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	/**
	 * Initialiseer het formbackingobject. In dit form zijn dat de UitslagenEnBedragen
	 * 
	 * 
	 */
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException, EditEtappeFormRequestException {
		//Gegevens van mogelijke vorige bezoek in sessie wissen
		dbUitslagBedragen = uitslagBedragDao.loadAllUitslagBedragen();
		UitslagBedragCommand uitslagBedrag = new UitslagBedragCommand();
		//loop door alle uitslagen heen en zet de waardes goed
		for (UitslagBedrag nextUitslagBedrag: dbUitslagBedragen) {
			switch (nextUitslagBedrag.getCategorie()) {
			case Etappe:
				if (nextUitslagBedrag.getPositie() >= uitslagBedrag.getEtappe().length) {
					uitslagBedrag.getEtappe()[nextUitslagBedrag.getPositie()] = nextUitslagBedrag.getBedrag();
				}
				break;
			case GeleTrui:
				if (nextUitslagBedrag.getPositie() >= uitslagBedrag.getGeleTrui().length) {
					uitslagBedrag.getGeleTrui()[nextUitslagBedrag.getPositie()] = nextUitslagBedrag.getBedrag();
				}
				break;
			case GroeneTrui:	
				if (nextUitslagBedrag.getPositie() >= uitslagBedrag.getGroeneTrui().length) {
					uitslagBedrag.getGroeneTrui()[nextUitslagBedrag.getPositie()] = nextUitslagBedrag.getBedrag();
				}
				break;
			case BolletjesTrui:
				if (nextUitslagBedrag.getPositie() >= uitslagBedrag.getBolletjesTrui().length) {
					uitslagBedrag.getBolletjesTrui()[nextUitslagBedrag.getPositie()] = nextUitslagBedrag.getBedrag();
				}
				break;
			case GeleTruiEind:
				if (nextUitslagBedrag.getPositie() >= uitslagBedrag.getGeleTruiEind().length) {
					uitslagBedrag.getGeleTruiEind()[nextUitslagBedrag.getPositie()] = nextUitslagBedrag.getBedrag();
				}
				break;
			case GroeneTruiEind:	
				if (nextUitslagBedrag.getPositie() >= uitslagBedrag.getGroeneTruiEind().length) {
					uitslagBedrag.getGroeneTruiEind()[nextUitslagBedrag.getPositie()] = nextUitslagBedrag.getBedrag();
				}
				break;
			case BolletjesTruiEind:
				if (nextUitslagBedrag.getPositie() >= uitslagBedrag.getBolletjesTruiEind().length) {
					uitslagBedrag.getBolletjesTruiEind()[nextUitslagBedrag.getPositie()] = nextUitslagBedrag.getBedrag();
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
	
	

}
