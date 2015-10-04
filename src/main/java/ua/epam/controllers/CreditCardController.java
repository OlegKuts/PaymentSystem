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
@RequestMapping("/creditcard")
public class CreditCardController {
	@Autowired
	CreditCardService creditCardService;
	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;
	@Autowired
	PaymentService paymentService;

	@RequestMapping(value = "/addnew", method = RequestMethod.POST)
	public String addNew(
			@ModelAttribute("newCard") CreditCard creditCard,
			Principal principal) {
		creditCardService.registerNew(creditCard, principal.getName());
		return "redirect:/account.html";
	}

}
