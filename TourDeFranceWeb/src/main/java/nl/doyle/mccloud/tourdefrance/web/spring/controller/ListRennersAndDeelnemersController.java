package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dozer.util.mapping.MapperIF;
import nl.doyle.mccloud.tourdefrance.controller.TourFacade;
import nl.doyle.mccloud.tourdefrance.dto.DeelnemerWithRennersDto;
import nl.doyle.mccloud.tourdefrance.web.spring.model.DeelnemerRennerModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/listRennersAndDeelnemers.htm")

public final class ListRennersAndDeelnemersController {

	private static final Log logger = LogFactory.getLog(ListRennersAndDeelnemersController.class);
	private TourFacade tourFacade;
	private MapperIF mapper;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		
		Map<String, List<DeelnemerRennerModel>> rennersAndDeelnemersModel = new HashMap<String, List<DeelnemerRennerModel>>();
		if (logger.isDebugEnabled()) {
			logger.debug("Putting DeelnemerRennerDto objects in model map.");
		}
		List<DeelnemerWithRennersDto> deelnemerWithRennersDto = tourFacade.getAllDeelnemersAndRenners();
		List<DeelnemerRennerModel> deelnemerRennerModel = new ArrayList<DeelnemerRennerModel>();
		//Kopieer dto in view model
		for (DeelnemerWithRennersDto nextDeelnemer : deelnemerWithRennersDto) {
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
	public void setTourFacade(final TourFacade tourFacade) {
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
	public void setMapper(final MapperIF mapper) {
		this.mapper = mapper;
	}
	

}
