package ua.epam.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.epam.form.UserForm;
import ua.epam.services.interfaces.AccountService;
import ua.epam.services.interfaces.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	AccountService accountService;

	@RequestMapping("/register")
	public String showRegister() {
		return "user-register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(
			@ModelAttribute("userform") @Valid UserForm userForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "user-register";
		}
		userService.registerUser(userForm);
		return "redirect:/login?successful=true";
	}

}
