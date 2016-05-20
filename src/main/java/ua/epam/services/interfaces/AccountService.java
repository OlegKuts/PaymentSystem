package ua.epam.services.interfaces;

import java.security.Principal;

import org.springframework.ui.Model;

import ua.epam.domain.Account;
import ua.epam.services.exceptions.NotEnoughFundsException;

public interface AccountService {
	Account getAccountById(Long accountId);

	void activeAccount(Long accountId);

	void deactiveAccount(Long accountId);

	void update(Account account);

	Account getAccountByUsername(String name);

	void refundAccount(Long cardId, Long accountId, double amountToRefund)
			throws NotEnoughFundsException;

	String showUserAccount(Principal principal, Model model);

	void withdrawFromAccount(Long cardId, Long accountId,
			double amountToWithdraw) throws NotEnoughFundsException;
}
