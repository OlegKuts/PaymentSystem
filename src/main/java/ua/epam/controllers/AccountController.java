package ua.epam.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.epam.domain.Account;
import ua.epam.domain.Payment;
import ua.epam.domain.User;
import ua.epam.repository.interfaces.AccountRepository;
import ua.epam.repository.interfaces.PaymentRepository;
import ua.epam.services.interfaces.AccountService;
import ua.epam.services.interfaces.UserService;

@Controller
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	UserService userService;


	@RequestMapping("/users/{userid}")
	public String showUserAccountByID(@PathVariable("userid") Long userId,
			Model model) {
		User user = userService.find(userId);
		Account account = user.getAccount();
		List<Payment> payers = account.getPayers();
		model.addAttribute(user);
		model.addAttribute(account);
		model.addAttribute("payers", payers);
		return "userprofile";
	}

	@RequestMapping("/account")
	public String showUserAccountByUserName(Principal principal, Model model) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		Account account = user.getAccount();
		List<Payment> payers = account.getPayers();
		model.addAttribute(user);
		model.addAttribute(account);
		model.addAttribute("payers", payers);
		return "userprofile";
	}

	@RequestMapping(value = "/users/block/{accountid}")
	public String blockAccount(@PathVariable("accountid") Long accountId, Model model) {
		accountService.deactiveAccount(accountId);
		return "redirect:/users.html";
	}
	
	@RequestMapping(value = "/users/active/{accountid}")
	public String activeAccount(@PathVariable("accountid") Long accountId, Model model) {
		accountService.activeAccount(accountId);
		return "redirect:/users.html";
	}
}
