package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SpringappController implements Controller {

	/** 
	 * @author mccloud
	 * Logger for this class and subclasses */
    protected final Logger logger = Logger.getLogger(SpringappController.class);

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String now = (new java.util.Date()).toString(); 
        logger.info("returning hello view with " + now);

        return new ModelAndView("index", "now", now);
    }
}