package ua.epam.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.epam.domain.Account;
import ua.epam.domain.CreditCard;
import ua.epam.domain.Payment;
import ua.epam.domain.User;
import ua.epam.services.interfaces.AccountService;
import ua.epam.services.interfaces.CreditCardService;
import ua.epam.services.interfaces.PaymentService;
import ua.epam.services.interfaces.UserService;

@Controller
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	UserService userService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	CreditCardService creditCardService;



	@RequestMapping("/account")
	public String showUserAccountByUserName(Principal principal, Model model) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		Account account = user.getAccount();
		List<Payment> payers = paymentService
				.getAllPaymentsForPayerAccount(account.getId());
		model.addAttribute("user", user);
		model.addAttribute("account", account);
		model.addAttribute("payers", payers);
		return "userprofile";
	}

	@RequestMapping(value = "/account/block/{accountid}")
	public String blockAccountByUser(@PathVariable("accountid") Long accountId,
			Model model) {
		accountService.deactiveAccount(accountId);
		return "redirect:/account.html";
	}

	@RequestMapping(value = "/account/refund/{accountid}")
	public String showRefundAccountBalance(
			@PathVariable("accountid") Long accountId, Model model) {
		// Account account = accountService.getAccountById(accountId);
		List<CreditCard> cards = creditCardService.findAllForAccount(accountId);
		model.addAttribute("cards", cards);
		return "addfunds";
	}

	@RequestMapping(value = "/account/refund/{accountid}", method = RequestMethod.POST)
	public String refundAccountBalance(
			@ModelAttribute("card") CreditCard creditCard) {
		return "addfunds";
	}
}
