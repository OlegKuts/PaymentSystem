package ua.epam.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
			BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			return "makepayment";
		}
		Long payerAccountId = accountService.getAccountByUsername(
				principal.getName()).getId();
		paymentService.makePayment(payerAccountId,
				paymentForm.getReceiverAccountId(), paymentForm.getAmount());
		return "redirect:/account";
	}
}
