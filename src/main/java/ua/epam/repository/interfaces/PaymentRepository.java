package ua.epam.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import ua.epam.domain.Payment;

@Repository
public interface PaymentRepository {
	void save(Payment payment);

	List<Payment> findAllForPayerAccount(Long accountId);

	List<Payment> findAllForReceiverAccount(Long accountId);
}
