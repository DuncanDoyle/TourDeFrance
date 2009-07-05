package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC Controller which handles the index.htm and index.html requests.
 * 
 * @author Duncan Doyle
 * @since 0.1
 */
@Controller
public class SpringappController {
    
    /**
     * Handles the request to <i>index.htm</i>. By convention the returned view name is 'index'.
     */
    @RequestMapping("/index.htm")
    public void handleIndexHtm() {
    }
    
    /**
     * Handles the request to <i>index.html</i>. By convention the returned view name is 'index'.
     */
    @RequestMapping("/index.html")
    public void handleIndexHtml() {
    }
    
}