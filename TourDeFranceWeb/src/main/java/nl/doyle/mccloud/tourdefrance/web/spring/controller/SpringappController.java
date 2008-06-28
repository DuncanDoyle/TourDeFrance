package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SpringappController implements Controller {

	/** 
	 * @author mccloud
	 * Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(SpringappController.class);

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	return new ModelAndView("index");
    }
}