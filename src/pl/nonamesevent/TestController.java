package pl.nonamesevent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView test() {
		return new ModelAndView("test");
	}
	
}
