package ua.epam.repository.interfaces;

import java.util.List;

import ua.epam.domain.Account;

public interface AccountRepository {
	void save(Account account);

	void update(Account account);

	Account find(Long id);
	
	List<Account> findAll();
	
	List<Account> findAllIfActive(boolean active);
}
