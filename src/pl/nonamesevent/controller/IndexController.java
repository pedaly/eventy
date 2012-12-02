package pl.nonamesevent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
public class IndexController {

	@RequestMapping(value = { "/index", "/" }, method = RequestMethod.GET)
	public String index() {
	 	UserService userService = UserServiceFactory.getUserService();
		
		if( userService.getCurrentUser() != null ){
			return "eventsList";
		}else
			return "index";
		
	}
}
