package ua.epam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.epam.domain.User;
import ua.epam.form.UserForm;
import ua.epam.services.interfaces.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@ModelAttribute("userform")
	public UserForm construct() {
		return new UserForm();

	}

	@RequestMapping("/users")
	public String showUsers(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "users";
	}

	@RequestMapping("/register")
	public String showRegister() {
		return "user-register";
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.POST  )
	public String doRegister(@ModelAttribute("userform") UserForm userForm ) {
		userService.registerUser(userForm);
		return "redirect:/index.html?successful=true";
	}
}
