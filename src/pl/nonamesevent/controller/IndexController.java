package pl.nonamesevent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
public class IndexController {

	@RequestMapping(value = { "/index", "/" }, method = RequestMethod.GET)
	public ModelAndView index() {
	 	UserService userService = UserServiceFactory.getUserService();
	 
	 	//w mojej jebanej przegladrce nie chce dzialac redirect i sie pluje o jakies ciasteczka ;/
		
		if (userService.getCurrentUser() != null) {
			ModelAndView mav = new ModelAndView(new RedirectView("/eventsList"));
			return mav;
		} else {
			ModelAndView mav = new ModelAndView("index");
			return mav;
		}
		
	}
}
