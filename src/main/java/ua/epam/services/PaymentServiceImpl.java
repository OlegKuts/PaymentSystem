package ua.epam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.epam.domain.Account;
import ua.epam.domain.Payment;
import ua.epam.repository.interfaces.AccountRepository;
import ua.epam.repository.interfaces.PaymentRepository;
import ua.epam.controllers.exceptions.NotEnoughFunds;
import ua.epam.services.exceptions.PaymentNotFoundException;
import ua.epam.services.interfaces.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private AccountRepository accountRepository;

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
	public List<Payment> getAllPaymentsForPayerAccount(Long accountId) {
		return paymentRepository.findAllForPayerAccount(accountId);
	}
	
	@Override
	public List<Payment> getAllReceivesForPayerAccount(Long accountId) {
		return paymentRepository.findAllForReceiverAccount(accountId);
	}

	@Override
	public void makePayment(Long payerAccountId, Long receiverAccountId,
			double amount) {
		Account payerAccount = accountRepository.find(payerAccountId);
		Account receiverAccount = accountRepository.find(receiverAccountId);
		Double payerAccountBalance = payerAccount.getBalance();
		Double receiverAccountBalance = receiverAccount.getBalance();
		if (payerAccountBalance < amount) {
			throw new NotEnoughFunds("Not enough funds on account");
		}
		Payment payment = new Payment(amount, payerAccount, receiverAccount);
		payerAccount.setBalance(payerAccountBalance - amount);
		receiverAccount.setBalance(receiverAccountBalance + amount);
		paymentRepository.save(payment);
		accountRepository.update(payerAccount);
		accountRepository.update(receiverAccount);
	}

}
