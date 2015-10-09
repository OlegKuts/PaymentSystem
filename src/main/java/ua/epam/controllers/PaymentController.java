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

import ua.epam.controllers.exceptions.NotEnoughFundsException;
import ua.epam.form.PaymentForm;
import ua.epam.services.interfaces.AccountService;
import ua.epam.services.interfaces.PaymentService;

@Controller
@RequestMapping("/payments")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private AccountService accountService;

	@RequestMapping("/makepayment")
	public String showMakePayment() {
		return "makepayment";
	}

	@RequestMapping(value = "/makepayment", method = RequestMethod.POST)
	public String makePayment(
			@ModelAttribute("paymentForm") @Valid PaymentForm paymentForm,
			BindingResult result, Principal principal, Model model) {
		if (result.hasErrors()) {
			return "makepayment";
		}
		Long payerAccountId = accountService.getAccountByUsername(
				principal.getName()).getId();
		try {
			paymentService.makePayment(payerAccountId,
					paymentForm.getReceiverAccountId(), paymentForm.getAmount());
		} catch (NotEnoughFundsException e) {
			model.addAttribute("exception", e);
			model.addAttribute("exceptionMessage", e.getMessage());
			return "makepayment";
		}
		return "redirect:/account";
	}
}
