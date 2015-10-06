package ua.epam.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.epam.domain.Account;
import ua.epam.domain.Payment;
import ua.epam.domain.User;
import ua.epam.services.interfaces.AccountService;
import ua.epam.services.interfaces.PaymentService;
import ua.epam.services.interfaces.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	UserService userService;

	@Autowired
	AccountService accountService;

	@Autowired
	PaymentService paymentService;

	@RequestMapping("/users")
	public String listUsers(Model model) {
		List<User> users = userService.getAllUsersWithUserRole();
		model.addAttribute("users", users);
		return "users";
	}

	@RequestMapping(value = "/users/block/{accountid}")
	public String blockAccountByAdmin(
			@PathVariable("accountid") Long accountId, Model model) {
		accountService.deactiveAccount(accountId);
		return "redirect:/admin/users.html";
	}

	@RequestMapping(value = "/users/active/{accountid}")
	public String activeAccountByAdmin(
			@PathVariable("accountid") Long accountId, Model model) {
		accountService.activeAccount(accountId);
		return "redirect:/admin/users.html";
	}

	@RequestMapping("/users/{userid}")
	public String showUserAccountByID(@PathVariable("userid") Long userId,
			Model model) {
		User user = userService.find(userId);
		Account account = user.getAccount();
		List<Payment> payers = paymentService
				.getAllPaymentsForPayerAccount(account.getId());
		model.addAttribute(user);
		model.addAttribute(account);
		model.addAttribute("payers", payers);
		return "userprofile";
	}
}
