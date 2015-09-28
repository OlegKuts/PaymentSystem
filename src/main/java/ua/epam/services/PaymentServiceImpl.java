package ua.epam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.epam.domain.Payment;
import ua.epam.repository.interfaces.PaymentRepository;
import ua.epam.services.exceptions.PaymentNotFoundException;
import ua.epam.services.interfaces.PaymentService;

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

	@Override
	public List<Payment> getAllPaymentsForUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
