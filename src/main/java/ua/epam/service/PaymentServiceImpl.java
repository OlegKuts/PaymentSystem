package ua.epam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.epam.domain.Payment;
import ua.epam.repository.interfaces.PaymentRepository;
import ua.epam.service.exceptions.PaymentNotFoundException;
import ua.epam.service.interfaces.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public Payment getPaymnetById(Long paymentId) {
		Payment payment = paymentRepository.find(paymentId);
		if (payment == null) {
			throw new PaymentNotFoundException();
		} else {
			return payment;
		}
	}

}
