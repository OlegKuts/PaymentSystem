package ua.epam.services.interfaces;

import java.util.List;

import org.springframework.validation.BindingResult;

import ua.epam.domain.CreditCard;

public interface CreditCardService {

	void registerNew(CreditCard creditCard, String name);
	
	void detach(Long cardId, Long accountId);
	
	List<CreditCard> findAllForAccount(Long accountId);
	
	List<String> getErrorMessages(BindingResult bindingResult);
	
}
