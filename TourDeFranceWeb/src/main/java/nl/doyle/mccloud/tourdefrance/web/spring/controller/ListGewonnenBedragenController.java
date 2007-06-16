package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.doyle.mccloud.tourdefrance.calculator.Calculator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ListGewonnenBedragenController implements Controller {

	private static final Log logger = LogFactory.getLog(ListGewonnenBedragenController.class);
	
	private Calculator tourCalculator;
	
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		Integer etappeNummer = ServletRequestUtils.getIntParameter(arg0,"etappe");
		Map<String, Object> listGewonnenBedragenModel = new HashMap<String, Object>();
		listGewonnenBedragenModel.put("deelnemersAndBedragen", tourCalculator.getAllDeelnemersAndBedragAfterEtappe(etappeNummer));
		listGewonnenBedragenModel.put("etappenummer", etappeNummer);
		return new ModelAndView("listGewonnenBedragen", "model", listGewonnenBedragenModel);
	}

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

	
	
	
}
