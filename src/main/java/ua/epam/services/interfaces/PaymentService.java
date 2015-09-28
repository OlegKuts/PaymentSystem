package ua.epam.services.interfaces;

import java.util.List;

import ua.epam.domain.Payment;

public interface PaymentService {
	Payment getPaymnetById(Long paymentId);
	
	List<Payment> getAllPaymentsForUser(Long userId);
}
