package pl.nonamesevent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		System.out.println("/index");
		return "home";
	}

	@RequestMapping(value = { "/home", "/" }, method = RequestMethod.GET)
	public ModelAndView home() {
		System.out.println("/home");
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/addEvent", method = RequestMethod.GET)
	public ModelAndView addEvent() {
		System.out.println("/addEvent");
		return new ModelAndView("addEvent");
	}

}
