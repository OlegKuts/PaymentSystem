package ua.epam.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.epam.domain.CreditCard;
import ua.epam.form.PaymentForm;
import ua.epam.form.RefundBalanceForm;
import ua.epam.form.UserForm;

@ControllerAdvice
public class ControllerAdvisor {
	@ModelAttribute("newCard")
	public CreditCard addNewCreditCard() {
		return new CreditCard();
	}

	@ModelAttribute("userform")
	public UserForm constructUserForm() {
		return new UserForm();
	}

	@ModelAttribute("refundForm")
	public RefundBalanceForm getRefundForm() {
		return new RefundBalanceForm();
	}

	@ModelAttribute("paymentForm")
	public PaymentForm getPaymentForm() {
		return new PaymentForm();
	}

}
