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

	@ModelAttribute("card")
	public CreditCard constructCreditCard() {
		return new CreditCard();
	}

	@RequestMapping(value = "/users/block/{accountid}")
	public String blockAccountByAdmin(
			@PathVariable("accountid") Long accountId, Model model) {
		accountService.deactiveAccount(accountId);
		return "redirect:/users.html";
	}

	@RequestMapping(value = "/users/active/{accountid}")
	public String activeAccountByAdmin(
			@PathVariable("accountid") Long accountId, Model model) {
		accountService.activeAccount(accountId);
		return "redirect:/users.html";
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
