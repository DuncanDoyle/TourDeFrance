package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.doyle.mccloud.tourdefrance.dao.RennerDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class EtappeDetailsController implements Controller {

	private static Log logger;
	//Statically initialize logger
	{
		logger = LogFactory.getLog(EtappeDetailsController.class);
	}
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//TODO laden van etappegegevens toevoegen.
		return new ModelAndView("etappeDetails");
	}

}
