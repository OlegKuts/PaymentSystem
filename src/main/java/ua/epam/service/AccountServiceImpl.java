package ua.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.epam.domain.Account;
import ua.epam.repository.interfaces.AccountRepository;
import ua.epam.service.exceptions.AccountNotFoundException;
import ua.epam.service.interfaces.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account getAccountById(Long accountId) {
		Account account = accountRepository.find(accountId);
		if (account == null) {
			throw new AccountNotFoundException();
		} else {
			return account;
		}
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
}
