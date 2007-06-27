package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.dozer.util.mapping.MapperIF;
import nl.doyle.mccloud.tourdefrance.controller.TourFacade;
import nl.doyle.mccloud.tourdefrance.controller.TourFacadeImpl;
import nl.doyle.mccloud.tourdefrance.dto.DeelnemerWithRennersDto;
import nl.doyle.mccloud.tourdefrance.web.spring.model.DeelnemerRennerModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ListRennersAndDeelnemersController implements Controller {

	private static final Log logger = LogFactory.getLog(ListRennersAndDeelnemersController.class);
	private TourFacade tourFacade;
	private MapperIF mapper;
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, List<DeelnemerRennerModel>> rennersAndDeelnemersModel = new HashMap<String, List<DeelnemerRennerModel>>();
		if (logger.isDebugEnabled()) {
			logger.debug("Putting DeelnemerRennerDto objects in model map.");
		}
		List<DeelnemerWithRennersDto> deelnemerWithRennersDto = tourFacade.getAllDeelnemersAndRenners();
		List<DeelnemerRennerModel> deelnemerRennerModel = new ArrayList<DeelnemerRennerModel>();
		//Kopieer dto in view model
		for (DeelnemerWithRennersDto nextDeelnemer: deelnemerWithRennersDto) {
			List<DeelnemerRennerModel> mappedDeelnemerAndRenners = (List<DeelnemerRennerModel>) mapper.map(nextDeelnemer, java.util.List.class);
			deelnemerRennerModel.addAll(mappedDeelnemerAndRenners);
		}
		
		rennersAndDeelnemersModel.put("rennersAndDeelnemers", deelnemerRennerModel);
		if (logger.isDebugEnabled()) {
			logger.debug("Returning ModelAnView 'listRennersAndDeelnemers' with model 'model'");
			
		}
		return new ModelAndView("listRennersAndDeelnemers", "model", rennersAndDeelnemersModel);
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
	 * @return the mapper
	 */
	public MapperIF getMapper() {
		return mapper;
	}


	/**
	 * @param mapper the mapper to set
	 */
	public void setMapper(MapperIF mapper) {
		this.mapper = mapper;
	}
	

}
