package ua.epam.services.interfaces;

import java.util.List;

import ua.epam.domain.CreditCard;

public interface CreditCardService {

	void registerNew(CreditCard creditCard, String name);
	List<CreditCard> findAllForAccount(Long accountId);
}
