package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.doyle.mccloud.tourdefrance.controller.TourFacade;
import nl.doyle.mccloud.tourdefrance.valueobjects.AbstractEtappeAndEindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;
import nl.doyle.mccloud.tourdefrance.web.spring.model.ListUitslagModel;
import nl.doyle.mccloud.tourdefrance.web.spring.model.ListUitslagModel.EtappeType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class ListEtappeUitslagController extends AbstractController {

	private static final Log logger = LogFactory.getLog(ListEtappeUitslagController.class);
	
	private TourFacade tourFacade;
			
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//Haal de etappe met uitslagen op m.b.v. de facade
		//TODO Hier moet nong gechecked worden op correcte invoer. Geen correcte invoer -> Exception. Misschien moeten we dit toch wel met een commandobject doen. Die heeft namelijk faciliteit voor validatie
		//int etappeNummer = (Integer) request.getAttribute("etappe");
		int etappeNummer = ServletRequestUtils.getIntParameter(request, "etappe");
		if (logger.isDebugEnabled()) {
			logger.debug("Etappenummer = " + etappeNummer);
		}
		AbstractEtappeAndEindUitslag etappe = tourFacade.getEtappeWithUitslag(etappeNummer);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Etappenummer = " + etappe.getEtappenummer());
			//logger.debug("Datum = " + etappe.getDatum());
			//logger.debug("Startplaats = " + etappe.getStartplaats());
			//logger.debug("Finishplaats = " + etappe.getFinishplaats());
		}
		
		Map<String, Object> etappeUitslagModel = new HashMap<String, Object>();
		//etappeUitslagModel.put("etappe", etappe);
		ListUitslagModel uitslagModel = new ListUitslagModel();
		uitslagModel.setEtappe(etappe);
		if (etappe instanceof StandaardEtappe) {
			uitslagModel.setTypeEtappe(EtappeType.Etappe);
			//etappeUitslagModel.put("isStandaardEtappe", new Boolean(true));
		} else  if (etappe instanceof PloegenTijdrit) {
			uitslagModel.setTypeEtappe(EtappeType.PloegenTijdrit);
			//etappeUitslagModel.put("isStandaardEtappe", new Boolean(false));
		} else {
			uitslagModel.setTypeEtappe(EtappeType.EindUitslag);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Putting Etappe Uitslag in model map.");
		}
		etappeUitslagModel.put("uitslagmodel", uitslagModel);
		return new ModelAndView("listEtappeUitslag", "model", etappeUitslagModel);
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
