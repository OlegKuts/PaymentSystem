package ua.epam.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import ua.epam.domain.Account;
import ua.epam.domain.CreditCard;
import ua.epam.domain.Payment;
import ua.epam.domain.User;
import ua.epam.repository.interfaces.AccountRepository;
import ua.epam.repository.interfaces.CreditCardRepository;
import ua.epam.repository.interfaces.PaymentRepository;
import ua.epam.repository.interfaces.UserRepository;
import ua.epam.services.exceptions.NotEnoughFundsException;
import ua.epam.services.interfaces.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CreditCardRepository creditCardRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public Account getAccountById(Long accountId) {
		Account account = accountRepository.find(accountId);
		return account;
	}

	@Override
	public void activeAccount(Long accountId) {
		Account account = getAccountById(accountId);
		account.setActive(Boolean.TRUE);
		accountRepository.update(account);
	}

	@Override
	public void deactiveAccount(Long accountId) {
		Account account = getAccountById(accountId);
		account.setActive(Boolean.FALSE);
		accountRepository.update(account);
	}

	@Override
	public List<Account> getAll() {
		return accountRepository.findAll();
	}

	@Override
	public void update(Account account) {
		accountRepository.update(account);

	}

	@Override
	public Account getAccountByUsername(String username) {
		return accountRepository.findByUsername(username);
	}
	
	

	@Override
	public void refundAccount(Long cardId, Long accountId, double amountToRefund)
			throws NotEnoughFundsException {
		// Account account = accountRepository.find(accountId);
		CreditCard creditCard = creditCardRepository.find(cardId);
		Account account = creditCard.getAccount();
		double creditCardBalance = creditCard.getAmount();
		if (creditCardBalance < amountToRefund) {
			throw new NotEnoughFundsException(
					"Not enough funds on choosen credit card ");
		}
		double amountLeftOnCard = creditCardBalance - amountToRefund;
		creditCard.setAmount(amountLeftOnCard);
		account.setBalance(account.getBalance() + amountToRefund);
		accountRepository.update(account);
	}
	
	@Override
	public String showUserAccount(Principal principal, Model model) {
		String username = principal.getName();
		User user = userRepository.findByUsername(username);
		Account account = user.getAccount();
		List<Payment> payments = paymentRepository.findAllForPayerAccount(account.getId());
		List<Payment> receives = paymentRepository.findAllForReceiverAccount(account.getId());
		boolean hasAnyCard = account.getCreditCards().size() != 0;
		model.addAttribute("user", user);
		model.addAttribute("account", account);
		model.addAttribute("payments", payments);
		model.addAttribute("receives", receives);
		model.addAttribute("hasAnyCard", hasAnyCard);
		return "userprofile";
	}
}
