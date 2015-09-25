package ua.epam.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import ua.epam.domain.Payment;

@Repository
public interface PaymentRepository {
	void save(Payment payment);

	void update(Payment payment);

	Payment find(Long id);

	List<Payment> findAll();

	Long getAmountForPayerAccount(Long accountId);

	List<Payment> findAllForPayerAccount(Long accountId);

	Long getAmountForReceiverAccount(Long accountId);

	List<Payment> findAllForReceiverAccount(Long accountId);
}
