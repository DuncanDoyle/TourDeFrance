package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.doyle.mccloud.tourdefrance.controller.TourFacadeImpl;
import nl.doyle.mccloud.tourdefrance.dto.DeelnemerRennerDto;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ListRennersAndDeelnemersController implements Controller {

	private static final Log logger = LogFactory.getLog(ListRennersAndDeelnemersController.class);
	private TourFacadeImpl tourFacade;
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, List<DeelnemerRennerDto>> rennersAndDeelnemersModel = new HashMap<String, List<DeelnemerRennerDto>>();
		if (logger.isDebugEnabled()) {
			logger.debug("Putting DeelnemerRennerDto objects in model map.");
		}
		rennersAndDeelnemersModel.put("rennersAndDeelnemers", tourFacade.getAllDeelnemersAndRenners());
		if (logger.isDebugEnabled()) {
			logger.debug("Returning ModelAnView 'listRennersAndDeelnemers' with model 'model'");
			
		}
		return new ModelAndView("listRennersAndDeelnemers", "model", rennersAndDeelnemersModel);
	}

	/**
	 * @return the tourFacade
	 */
	public TourFacadeImpl getTourFacade() {
		return tourFacade;
	}

	/**
	 * @param tourFacade the tourFacade to set
	 */
	public void setTourFacade(TourFacadeImpl tourFacade) {
		this.tourFacade = tourFacade;
	}
	
	

}
