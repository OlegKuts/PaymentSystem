package ua.epam.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import ua.epam.domain.Account;
import ua.epam.domain.CreditCard;
import ua.epam.domain.User;
import ua.epam.repository.interfaces.AccountRepository;
import ua.epam.repository.interfaces.CreditCardRepository;
import ua.epam.repository.interfaces.UserRepository;
import ua.epam.services.interfaces.CreditCardService;

@Service
public class CreditCardServiceImpl implements CreditCardService {
	@Autowired
	@Qualifier("jdbcCreditCardRepository")
	CreditCardRepository creditCardRepository;
	@Autowired
	@Qualifier("jdbcUserRepository")
	UserRepository userRepository;
	@Autowired
	@Qualifier("jdbcAccountRepository")
	AccountRepository accountRepository;

	@Override
	public void registerNew(CreditCard creditCard, String username) {
		User user = userRepository.findByUsername(username);
		Account account = user.getAccount();
		creditCard.setAccount(account);
		creditCard.setAmount(1000.0);
		account.getCreditCards().add(creditCard);
		creditCardRepository.save(creditCard);
	}

	@Override
	public void detach(Long cardId, Long accountId) {
		CreditCard card = creditCardRepository.find(cardId);
		Account account = accountRepository.find(accountId);
		account.getCreditCards().remove(card);
		card.setAccount(null);
		accountRepository.update(account);
		creditCardRepository.update(card);
		creditCardRepository.delete(card);
	}

	@Override
	public List<CreditCard> findAllForAccount(Long accountId) {
		return creditCardRepository.findAllForAccount(accountId);
	}

	@Override
	public List<String> getErrorMessages(BindingResult bindingResult) {
		List<ObjectError> errors = bindingResult.getAllErrors();
		List<String> messages = new ArrayList<String>();
		for (ObjectError error : errors) {
			messages.add(error.getDefaultMessage());
		}
		return messages;
	}
}
