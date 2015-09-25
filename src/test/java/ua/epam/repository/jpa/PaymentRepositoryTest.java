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
	public void insertAndFindPaymentTest() {
		Payment payment = new Payment();
		Double amount = 42.0;
		payment.setAmount(amount);
		paymentRepository.save(payment);

		Payment paymentdb = paymentRepository.find(payment.getId());

		assertNotNull(paymentdb);
		assertEquals(payment, paymentdb);
		assertEquals(amount, paymentdb.getAmount());
	}

	@Test
	public void findAllTest() {
		Payment payment = new Payment();
		Payment payment2 = new Payment();
		paymentRepository.save(payment);
		paymentRepository.save(payment2);

		List<Payment> payments = paymentRepository.findAll();

		assertNotNull(payments);
		assertEquals(2, payments.size());
		assertTrue(payments.contains(payment));
		assertTrue(payments.contains(payment2));
	}

	@Test
	public void getAmountForPayerAccountTest() {
		Account account = new Account();
		Payment payment = new Payment();
		Payment payment2 = new Payment();
		Long expectedAmount = 2L;
		account.getPayers().add(payment);
		account.getPayers().add(payment2);
		payment.setPayerAccount(account);
		payment2.setPayerAccount(account);
		accountRepository.save(account);

		Long ammount = paymentRepository.getAmountForPayerAccount(account
				.getId());

		assertNotNull(ammount);
		assertEquals(expectedAmount, ammount);

	}

	@Test
	public void findAllForPayerAccountTest() {
		Account account = new Account();
		Payment payment = new Payment();
		Payment payment2 = new Payment();
		int expectedSize = 2;
		account.getPayers().add(payment);
		account.getPayers().add(payment2);
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
	public void getAmountForReceiverAccountTest() {
		Account account = new Account();
		Payment payment = new Payment();
		Payment payment2 = new Payment();
		Long expectedAmount = 2L;
		account.getReceivers().add(payment);
		account.getReceivers().add(payment2);
		payment.setReceiverAccount(account);
		payment2.setReceiverAccount(account);
		accountRepository.save(account);

		Long ammount = paymentRepository.getAmountForReceiverAccount(account
				.getId());

		assertNotNull(ammount);
		assertEquals(expectedAmount, ammount);
	}
	
	@Test
	public void findAllForReceiverAccountTest() {
		Account account = new Account();
		Payment payment = new Payment();
		Payment payment2 = new Payment();
		int expectedSize = 2;
		account.getReceivers().add(payment);
		account.getReceivers().add(payment2);
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
