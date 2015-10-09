package ua.epam.services.interfaces;

import java.util.List;

import ua.epam.controllers.exceptions.NotEnoughFundsException;
import ua.epam.domain.Account;

public interface AccountService {
	Account getAccountById(Long accountId);
	
	void activeAccount(Long accountId);
	
	void deactiveAccount(Long accountId);
	
	List<Account> getAll();

	void update(Account account);

	Account getAccountByUsername(String name);

	void refundAccount(Long cardId, Long accountId, double amountToRefund) throws NotEnoughFundsException;
}
