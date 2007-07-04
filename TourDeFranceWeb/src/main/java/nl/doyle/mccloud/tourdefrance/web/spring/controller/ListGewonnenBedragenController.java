package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.dozer.util.mapping.MapperIF;
import nl.doyle.mccloud.tourdefrance.calculator.Calculator;
import nl.doyle.mccloud.tourdefrance.dto.DeelnemerBedragDto;
import nl.doyle.mccloud.tourdefrance.web.spring.model.DeelnemerAndBedragModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ListGewonnenBedragenController implements Controller {

	private static final Log logger = LogFactory.getLog(ListGewonnenBedragenController.class);
	
	private Calculator tourCalculator;
	private MapperIF mapper;
	
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		Integer etappeNummer = ServletRequestUtils.getIntParameter(arg0,"etappe");
		Map<String, Object> listGewonnenBedragenModel = new HashMap<String, Object>();
		List<DeelnemerBedragDto> deelnemerAndBedrag = tourCalculator.getAllDeelnemersAndGewonnenBedragAfterEtappe(etappeNummer);
		List<DeelnemerAndBedragModel> deelnemerAndBedragModel = new ArrayList<DeelnemerAndBedragModel>();
		logger.debug("Mapping objects to view model.");
		for (DeelnemerBedragDto dbDto: deelnemerAndBedrag) {
			 DeelnemerAndBedragModel dbModel =  (DeelnemerAndBedragModel) mapper.map(dbDto, DeelnemerAndBedragModel.class);
			 logger.debug("Adding model entry to view model.");
			 deelnemerAndBedragModel.add(dbModel);
		}
		logger.debug("View model constructed.");
		listGewonnenBedragenModel.put("deelnemersAndBedragen", deelnemerAndBedragModel);
		listGewonnenBedragenModel.put("etappenummer", etappeNummer);
		return new ModelAndView("listGewonnenBedragen", "model", listGewonnenBedragenModel);
	}
	
	
//private MapperIF mapper;
//	
//	public ModelAndView handleRequest(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		
//		Map<String, List<DeelnemerRennerModel>> rennersAndDeelnemersModel = new HashMap<String, List<DeelnemerRennerModel>>();
//		if (logger.isDebugEnabled()) {
//			logger.debug("Putting DeelnemerRennerDto objects in model map.");
//		}
//		List<DeelnemerWithRennersDto> deelnemerWithRennersDto = tourFacade.getAllDeelnemersAndRenners();
//		List<DeelnemerRennerModel> deelnemerRennerModel = new ArrayList<DeelnemerRennerModel>();
//		//Kopieer dto in view model
//		for (DeelnemerWithRennersDto nextDeelnemer: deelnemerWithRennersDto) {
//			List<DeelnemerRennerModel> mappedDeelnemerAndRenners = (List<DeelnemerRennerModel>) mapper.map(nextDeelnemer, java.util.List.class);
//			deelnemerRennerModel.addAll(mappedDeelnemerAndRenners);
//		}
//		
//		rennersAndDeelnemersModel.put("rennersAndDeelnemers", deelnemerRennerModel);
//		if (logger.isDebugEnabled()) {
//			logger.debug("Returning ModelAnView 'listRennersAndDeelnemers' with model 'model'");
//			
//		}
//		return new ModelAndView("listRennersAndDeelnemers", "model", rennersAndDeelnemersModel);
//	}

	/**
	 * @return the tourCalculator
	 */
	public Calculator getTourCalculator() {
		return tourCalculator;
	}

	/**
	 * @param tourCalculator the tourCalculator to set
	 */
	public void setTourCalculator(Calculator tourCalculator) {
		this.tourCalculator = tourCalculator;
	}


	public MapperIF getMapper() {
		return mapper;
	}


	public void setMapper(MapperIF mapper) {
		this.mapper = mapper;
	}

	
	
	
}
