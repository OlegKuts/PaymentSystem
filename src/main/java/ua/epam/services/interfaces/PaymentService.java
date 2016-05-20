package ua.epam.services.interfaces;

import java.util.List;

import ua.epam.domain.Payment;
import ua.epam.services.exceptions.NotEnoughFundsException;
import ua.epam.services.exceptions.SameAccountException;

public interface PaymentService {

	List<Payment> getAllPaymentsForPayerAccount(Long accountId);

	List<Payment> getAllReceivesForPayerAccount(Long accountId);

	void makePayment(Long payerAccountId, Long receiverAccountId, double amount)
			throws NotEnoughFundsException, SameAccountException;

}
