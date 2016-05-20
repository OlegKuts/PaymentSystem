package ua.epam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ua.epam.domain.Account;
import ua.epam.domain.Payment;
import ua.epam.repository.interfaces.AccountRepository;
import ua.epam.repository.interfaces.PaymentRepository;
import ua.epam.services.exceptions.NotEnoughFundsException;
import ua.epam.services.exceptions.SameAccountException;
import ua.epam.services.interfaces.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	@Qualifier("jdbcPaymentRepository")
	private PaymentRepository paymentRepository;
	@Autowired
	@Qualifier("jdbcAccountRepository")
	private AccountRepository accountRepository;

	@Override
	public List<Payment> getAllPaymentsForPayerAccount(Long accountId) {
		return paymentRepository.findAllForPayerAccount(accountId);
	}

	@Override
	public List<Payment> getAllReceivesForPayerAccount(Long accountId) {
		return paymentRepository.findAllForReceiverAccount(accountId);
	}

	@Override
	public void makePayment(Long payerAccountId, Long receiverAccountId,
			double amount) throws NotEnoughFundsException, SameAccountException {
		Account payerAccount = accountRepository.find(payerAccountId);
		Account receiverAccount = accountRepository.find(receiverAccountId);
		Double payerAccountBalance = payerAccount.getBalance();
		Double receiverAccountBalance = receiverAccount.getBalance();
		if (payerAccountBalance < amount) {
			throw new NotEnoughFundsException("Not enough funds on account");
		}
		if (payerAccountId == receiverAccountId) {
			throw new SameAccountException("You cannot choose your own account");
		}
		Payment payment = new Payment(amount, payerAccount, receiverAccount);
		payerAccount.setBalance(payerAccountBalance - amount);
		receiverAccount.setBalance(receiverAccountBalance + amount);
		paymentRepository.save(payment);
		accountRepository.update(payerAccount);
		accountRepository.update(receiverAccount);
	}

}
