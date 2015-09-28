package ua.epam.services.interfaces;

import java.util.List;

import ua.epam.domain.Account;

public interface AccountService {
	Account getAccountById(Long accountId);
	
	void activeAccount(Long accountId);
	
	void deactiveAccount(Long accountId);
	
	List<Account> getAll();
}
