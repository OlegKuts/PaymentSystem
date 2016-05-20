package ua.epam.repository.interfaces;

import java.util.List;

import ua.epam.domain.CreditCard;

public interface CreditCardRepository {
	void save(CreditCard creditCard);

	void update(CreditCard creditCard);

	CreditCard find(Long id);

	boolean isCardNumberUniq(String cardNumber);

	List<CreditCard> findAllForAccount(Long accountId);

	void delete(CreditCard creditCard);
}
