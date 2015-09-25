package ua.epam.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import ua.epam.domain.Account;
import ua.epam.domain.CreditCard;
import ua.epam.domain.User;
import ua.epam.repository.interfaces.AccountRepository;
import ua.epam.repository.interfaces.CreditCardRepository;
import ua.epam.repository.interfaces.UserRepository;

@Component
public class Main {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CreditCardRepository creditCardRepository;
	
	public void getAmountForAccount() {
		Account account = new Account();
		Set<CreditCard>  cards = account.getCreditCards();
		CreditCard creditCard = new CreditCard();
		CreditCard creditCard2 = new CreditCard();
		String cardNumber = "123456789123";
		String cardNumber2 = "321123456789";
		creditCard.setCardNumber(cardNumber);
		creditCard2.setCardNumber(cardNumber2);
		creditCard.setAccount(account);
		creditCard2.setAccount(account);
		cards.add(creditCard);
		cards.add(creditCard2);
		
		accountRepository.save(account);
		Account accountdb = accountRepository.find(account.getId());
		Long size = creditCardRepository.getAmountForAccount(account.getId());
		Long  expectedSize= 2L;
		System.out.println(size);
	}
	void exception() {
		Account account = new Account();
		accountRepository.save(account);
		Account account2 = accountRepository.find(account.getId());
		System.out.println(account.getId() + " " + account2.getId());
		accountRepository.save(account2);
	}

	void saveUser(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		userRepository.save(user);
	}

	private void findUser() {
		User user = userRepository.find((long) 1);
		System.out.println(user);
	}

	public void uniq() {
		System.out.println(userRepository.isUsernameUniq("Oleg"));
		System.out.println(userRepository.isUsernameUniq("Uniq"));
		System.out.println(userRepository.isUsernameUniq("Taras"));
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext(
				"repositoryContext.xml");

		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] { "appContext.xml" }, repositoryContext);

		Main main = repositoryContext.getBean(Main.class);
		// main.saveUser("Oleg", "asdfgh");
		// main.findUser();
		// main.uniq();
		//main.exception();
		main.getAmountForAccount();

	}

}
