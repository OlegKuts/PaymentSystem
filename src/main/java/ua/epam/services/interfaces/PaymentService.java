package ua.epam.services.interfaces;

import java.util.List;

import ua.epam.domain.Payment;

public interface PaymentService {
	Payment getPaymnetById(Long paymentId);
	
	List<Payment> getAllPaymentsForPayerAccount(Long accountId);

	void makePayment(Long payerAccountId, Long receiverAccountId, double amount);
}
