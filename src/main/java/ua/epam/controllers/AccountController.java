package ua.epam.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.epam.domain.Account;
import ua.epam.domain.CreditCard;
import ua.epam.domain.Payment;
import ua.epam.domain.User;
import ua.epam.form.RefundBalanceForm;
import ua.epam.services.interfaces.AccountService;
import ua.epam.services.interfaces.CreditCardService;
import ua.epam.services.interfaces.PaymentService;
import ua.epam.services.interfaces.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	UserService userService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	CreditCardService creditCardService;

	@RequestMapping("")
	public String showUserAccount(Principal principal, Model model) {
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

	@RequestMapping(value = "/block")
	public String blockAccountByUser(	Model model, Principal principal) {
		Account account = accountService.getAccountByUsername(principal.getName());
		accountService.deactiveAccount(account.getId());
		return "redirect:/account";
	}

	@RequestMapping(value = "/refund", method = RequestMethod.GET)
	public String showRefundAccountBalance( Model model, Principal principal) {
		Account account = accountService.getAccountByUsername(principal.getName());
		Long accountId = account.getId();
		List<CreditCard> cards = creditCardService.findAllForAccount(accountId);
		model.addAttribute("cards", cards);
		model.addAttribute("accountId", accountId);
		return "addfunds";
	}
	
	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	public String refundAccountBalance(@ModelAttribute("refundForm") RefundBalanceForm refundForm) {
		accountService.refundAccount(refundForm.getCardId(),refundForm.getAccountId(),refundForm.getAmount());
		return "redirect:/account";
	}
}
