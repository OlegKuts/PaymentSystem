package ua.epam.service.interfaces;

import ua.epam.domain.Payment;

public interface PaymentService {
	Payment getPaymnetById(Long paymentId);
}
