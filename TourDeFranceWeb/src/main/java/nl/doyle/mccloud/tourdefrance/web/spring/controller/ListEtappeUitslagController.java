package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.doyle.mccloud.tourdefrance.controller.TourFacade;
import nl.doyle.mccloud.tourdefrance.dto.AbstractEtappeAndEindUitslagDto;
import nl.doyle.mccloud.tourdefrance.dto.PloegenTijdritDto;
import nl.doyle.mccloud.tourdefrance.dto.StandaardEtappeDto;
import nl.doyle.mccloud.tourdefrance.web.spring.model.ListUitslagModel;
import nl.doyle.mccloud.tourdefrance.web.spring.model.ListUitslagModel.EtappeType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/listEtappeUitslag.htm")
public class ListEtappeUitslagController {

	/**
	 * Commons-Logging logger.
	 */
	private static final Log LOG = LogFactory.getLog(ListEtappeUitslagController.class);

	/**
	 * The business layer interface.
	 */
	private TourFacade tourFacade;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequestInternal(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		//TODO replace with @RequestParam
		
		// Haal de etappe met uitslagen op m.b.v. de facade
		// TODO Hier moet nong gechecked worden op correcte invoer. Geen correcte invoer -> Exception. Misschien moeten we dit toch wel met
		// een commandobject doen. Die heeft namelijk faciliteit voor validatie
		// int etappeNummer = (Integer) request.getAttribute("etappe");
		int etappeNummer = ServletRequestUtils.getIntParameter(request, "etappe");
		if (LOG.isDebugEnabled()) {
			LOG.debug("Etappenummer = " + etappeNummer);
		}
		AbstractEtappeAndEindUitslagDto etappe = tourFacade.getEtappeDtoWithUitslag(etappeNummer);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Etappenummer = " + etappe.getEtappenummer());
		}

		Map<String, Object> etappeUitslagModel = new HashMap<String, Object>();
		// etappeUitslagModel.put("etappe", etappe);
		ListUitslagModel uitslagModel = new ListUitslagModel();
		uitslagModel.setEtappe(etappe);
		if (etappe instanceof StandaardEtappeDto) {
			uitslagModel.setTypeEtappe(EtappeType.Etappe);
			// etappeUitslagModel.put("isStandaardEtappe", new Boolean(true));
		} else if (etappe instanceof PloegenTijdritDto) {
			uitslagModel.setTypeEtappe(EtappeType.PloegenTijdrit);
			// etappeUitslagModel.put("isStandaardEtappe", new Boolean(false));
		} else {
			uitslagModel.setTypeEtappe(EtappeType.EindUitslag);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Putting Etappe Uitslag in model map.");
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
	 * @param tourFacade
	 *            the tourFacade to set
	 */
	public void setTourFacade(final TourFacade tourFacade) {
		this.tourFacade = tourFacade;
	}

}
