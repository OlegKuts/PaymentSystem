package ua.epam.repository.interfaces;

import java.util.List;

import ua.epam.domain.CreditCard;

public interface CreditCardRepository {
	void save(CreditCard creditCard);

	void update(CreditCard creditCard);

	CreditCard find(Long id);

	List<CreditCard> findAll();

	boolean isCardNumberUniq(String cardNumber);

	Long getAmountForAccount(Long accountId);

	List<CreditCard> findAllForAccount(Long accountId);

	void delete(CreditCard creditCard);
}
