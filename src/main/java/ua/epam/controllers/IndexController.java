package ua.epam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.epam.domain.User;
import ua.epam.services.interfaces.UserService;

@Controller
public class IndexController {
	@Autowired
	UserService userService;

	@RequestMapping("/index")
	public String index(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "index";
	}
}
