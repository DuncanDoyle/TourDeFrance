package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login.htm")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public void handleRequest() {
}

}
