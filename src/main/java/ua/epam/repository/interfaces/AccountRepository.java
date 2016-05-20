package ua.epam.repository.interfaces;

import ua.epam.domain.Account;

public interface AccountRepository {
	void save(Account account);

	void update(Account account);

	Account find(Long id);

	Account findByUsername(String username);

}
