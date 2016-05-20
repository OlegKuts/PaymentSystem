package ua.epam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.epam.services.interfaces.UserService;

@Controller
public class IndexController {
	@Autowired
	UserService userService;

	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
