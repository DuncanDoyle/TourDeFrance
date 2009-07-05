package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/etappeDetails.htm")
public class EtappeDetailsController {

	@RequestMapping(method = RequestMethod.GET)
	public void handleRequest() {
		//returns "etappeDetails" view by convention.
	}

}
