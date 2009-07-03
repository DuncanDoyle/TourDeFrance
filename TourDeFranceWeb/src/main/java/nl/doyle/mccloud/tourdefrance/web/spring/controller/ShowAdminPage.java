package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller class for the admin page.
 * 
 * @author Duncan Doyle
 * @since 0.1
 * 
 */
@Controller
@RequestMapping("/adminPage.htm")
public final class ShowAdminPage {

	/**
	 * The name of the admin page view.
	 */
	private static final String VIEW = "adminPage";

	/**
	 * @return the name of the admin page view
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String handleRequest() {
		return VIEW;
	}

}
