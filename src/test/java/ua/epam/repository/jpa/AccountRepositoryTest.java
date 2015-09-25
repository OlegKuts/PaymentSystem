package ua.epam.repository.jpa;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ua.epam.domain.Account;
import ua.epam.repository.interfaces.AccountRepository;
import ua.epam.repository.template.RepositoryTestTemplate;

public class AccountRepositoryTest extends RepositoryTestTemplate {

	@Autowired
	AccountRepository accountRepository;

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
	public void findAllTest() {
		Account account = new Account();
		Account account2 = new Account();
		Account account3 = new Account();
		accountRepository.save(account);
		accountRepository.save(account2);
		accountRepository.save(account3);

		List<Account> accounts = accountRepository.findAll();

		assertNotNull(accounts);
		assertEquals(3, accounts.size());
		assertTrue(accounts.contains(account));
	}

	@Test
	public void findAllTestActive() {
		Account account = new Account();
		Account account2 = new Account();
		Account account3 = new Account();
		account.setActive(true);
		account2.setActive(true);
		account3.setActive(false);
		
		accountRepository.save(account);
		accountRepository.save(account2);
		accountRepository.save(account3);
		
		List<Account> activeAccounts = accountRepository.findAllIfActive(true);
		List<Account> dissactiveAccounts = accountRepository.findAllIfActive(false); 
		
		assertNotNull(activeAccounts);
		assertNotNull(dissactiveAccounts);
		assertEquals(2, activeAccounts.size());
		assertEquals(1, dissactiveAccounts.size());
		assertTrue(activeAccounts.get(1).getActive());
		assertFalse(dissactiveAccounts.get(0).getActive());
		
	}
}
