package ua.epam.repository.jpa;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ua.epam.domain.Account;
import ua.epam.domain.Payment;
import ua.epam.repository.interfaces.AccountRepository;
import ua.epam.repository.interfaces.PaymentRepository;
import ua.epam.repository.template.RepositoryTestTemplate;

public class PaymentRepositoryTest extends RepositoryTestTemplate {
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	AccountRepository accountRepository;

	@Test
	public void findAllForPayerAccountTest() {
		Account account = new Account();
		Payment payment = new Payment();
		Payment payment2 = new Payment();
		int expectedSize = 2;
		account.getPayed().add(payment);
		account.getPayed().add(payment2);
		payment.setPayerAccount(account);
		payment2.setPayerAccount(account);
		accountRepository.save(account);

		List<Payment> payments = paymentRepository
				.findAllForPayerAccount(account.getId());

		assertNotNull(payments);
		assertEquals(expectedSize, payments.size());
		assertEquals(payment, payments.get(0));
	}

	@Test
	public void findAllForReceiverAccountTest() {
		Account account = new Account();
		Payment payment = new Payment();
		Payment payment2 = new Payment();
		int expectedSize = 2;
		account.getReceived().add(payment);
		account.getReceived().add(payment2);
		payment.setReceiverAccount(account);
		payment2.setReceiverAccount(account);
		accountRepository.save(account);

		List<Payment> payments = paymentRepository
				.findAllForReceiverAccount(account.getId());

		assertNotNull(payments);
		assertEquals(expectedSize, payments.size());
		assertEquals(payment, payments.get(0));
	}
}
