package ua.epam.repository.jpa;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ua.epam.domain.Account;
import ua.epam.domain.User;
import ua.epam.repository.interfaces.AccountRepository;
import ua.epam.repository.interfaces.UserRepository;
import ua.epam.repository.template.RepositoryTestTemplate;

public class AccountRepositoryTest extends RepositoryTestTemplate {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	UserRepository userRepository;

	@Test
	public void insertAndFindAccountTest() {
		Account account = new Account();

		accountRepository.save(account);
		Account accountdb = accountRepository.find(account.getId());

		assertNotNull(accountdb);
		assertEquals(account, accountdb);
	}

	@Test
	public void updateAccountTest() {
		Account account = new Account();
		double balance = 3.14;
		double updatedBalance = 14.3;
		account.setBalance(balance);

		accountRepository.save(account);
		Account accountToUpdate = accountRepository.find(account.getId());
		accountToUpdate.setBalance(updatedBalance);

		accountRepository.update(accountToUpdate);
		Account accountUpdated = accountRepository.find(account.getId());

		assertNotNull(accountUpdated);
		assertEquals(accountUpdated, accountToUpdate);
	}

	@Test
	public void findByUsernameTest() {
		User user = new User();
		String username = "user";
		user.setUsername(username);
		Account account = new Account();
		account.setUser(user);
		user.setAccount(account);
		userRepository.save(user);
		Account accountdb = accountRepository.findByUsername(username);

		assertEquals(account, accountdb);
		assertEquals(accountdb.getUser().getUsername(), username);
	}
}
