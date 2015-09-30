package ua.epam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.epam.domain.Account;
import ua.epam.domain.CreditCard;
import ua.epam.domain.User;
import ua.epam.repository.interfaces.CreditCardRepository;
import ua.epam.repository.interfaces.UserRepository;
import ua.epam.services.interfaces.CreditCardService;

@Service
public class CreditCardServiceImpl implements CreditCardService {
	@Autowired
	CreditCardRepository creditCardRepository;

	@Autowired
	UserRepository userRepository;

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
	public List<CreditCard> findAllForAccount(Long accountId) {
		return creditCardRepository.findAllForAccount(accountId);
	}
}
