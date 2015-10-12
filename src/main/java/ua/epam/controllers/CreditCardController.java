package ua.epam.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.epam.domain.CreditCard;
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

	@RequestMapping(value = "/addnewcard", method = RequestMethod.POST)
	public String addNew(
			@ModelAttribute("newCard") @Valid CreditCard creditCard,
			BindingResult bindingResult, Principal principal, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errorMessages",
					creditCardService.getErrorMessages(bindingResult));
			return accountService.showUserAccount(principal, model);
		}
		creditCardService.registerNew(creditCard, principal.getName());
		return "redirect:/account";
	}

	@RequestMapping(value = "/detach", method = RequestMethod.POST)
	public String detachCard(@RequestParam("cardId") Long cardId,
			@RequestParam("accountId") Long accountId) {
		creditCardService.detach(cardId, accountId);
		return "redirect:/account";
	}

	/*
	 * private String showUserAccount(Principal principal, Model model) { String
	 * username = principal.getName(); User user =
	 * userService.findByUsername(username); Account account =
	 * user.getAccount(); List<Payment> payments = paymentService
	 * .getAllPaymentsForPayerAccount(account.getId()); List<Payment> receives =
	 * paymentService .getAllReceivesForPayerAccount(account.getId());
	 * model.addAttribute("user", user); model.addAttribute("account", account);
	 * model.addAttribute("payments", payments); model.addAttribute("receives",
	 * receives); return "userprofile"; }
	 */
}
