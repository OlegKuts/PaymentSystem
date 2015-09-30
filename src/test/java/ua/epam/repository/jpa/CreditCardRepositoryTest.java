package ua.epam.repository.jpa;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ua.epam.domain.Account;
import ua.epam.domain.CreditCard;
import ua.epam.repository.interfaces.AccountRepository;
import ua.epam.repository.interfaces.CreditCardRepository;
import ua.epam.repository.template.RepositoryTestTemplate;

public class CreditCardRepositoryTest extends RepositoryTestTemplate {

	@Autowired
	CreditCardRepository creditCardRepository;
	@Autowired
	AccountRepository accountRepository;

	@Test
	public void insertAndFindTest() {
		CreditCard creditCard = new CreditCard();
		creditCardRepository.save(creditCard);
		CreditCard creditCarddb = creditCardRepository.find(creditCard.getId());

		assertNotNull(creditCarddb);
		assertEquals(creditCard, creditCarddb);
	}

	@Test
	public void updateTest() {
		CreditCard creditCard = new CreditCard();
		String oldNumber = "123456789123";
		String newNumber = "321123456789";
		creditCard.setCardNumber(oldNumber);
		creditCardRepository.save(creditCard);

		CreditCard creditCardToUpdate = creditCardRepository.find(creditCard
				.getId());
		creditCardToUpdate.setCardNumber(newNumber);
		creditCardRepository.update(creditCardToUpdate);

		CreditCard updatedCreditCard = creditCardRepository.find(creditCard
				.getId());

		assertNotNull(updatedCreditCard);
		assertEquals(newNumber, updatedCreditCard.getCardNumber());
		assertEquals(newNumber, creditCard.getCardNumber());
		assertNotEquals(oldNumber, updatedCreditCard.getCardNumber());
	}

	@Test
	public void findAllTest() {
		CreditCard creditCard = new CreditCard();
		CreditCard creditCard2 = new CreditCard();
		creditCardRepository.save(creditCard);
		creditCardRepository.save(creditCard2);

		List<CreditCard> cards = creditCardRepository.findAll();

		assertNotNull(cards);
		assertEquals(2, cards.size());
		assertEquals(creditCard, cards.get(0));

	}

	@Test
	public void isCardNumberUniqTest() {
		CreditCard creditCard = new CreditCard();
		String notUniqCardNumber = "123456789123";
		String uniqCardNumber = "123123456789";
		creditCard.setCardNumber(notUniqCardNumber);

		creditCardRepository.save(creditCard);
		boolean uniq = creditCardRepository.isCardNumberUniq(uniqCardNumber);
		boolean notUniq = creditCardRepository
				.isCardNumberUniq(notUniqCardNumber);

		assertTrue(uniq);
		assertFalse(notUniq);
	}

	@Test
	public void getAmountForAccount() {
		Account account = new Account();
		Set<CreditCard> cards = account.getCreditCards();
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
		Long expectedSize = 2L;

		assertNotNull(accountdb.getCreditCards());
		assertFalse(accountdb.getCreditCards().isEmpty());
		assertEquals(expectedSize, size);

	}

	@Test
	public void findAllForAccountTest() {
		Account account = new Account();
		Set<CreditCard> cards = account.getCreditCards();
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
		List<CreditCard> cardsdb = creditCardRepository.findAllForAccount(accountdb.getId());
		
		assertNotNull(accountdb);
		assertEquals(2, cardsdb.size());
		assertTrue(cardsdb.contains(creditCard));
	}
}
