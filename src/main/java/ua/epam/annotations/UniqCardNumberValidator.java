package ua.epam.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ua.epam.repository.interfaces.CreditCardRepository;

public class UniqCardNumberValidator implements
		ConstraintValidator<UniqCardNumber, String> {
	@Autowired
	@Qualifier("jdbcCreditCardRepository")
	CreditCardRepository creditCardRepository;

	@Override
	public void initialize(UniqCardNumber annotation) {

	}

	@Override
	public boolean isValid(String cardNumber, ConstraintValidatorContext context) {
		if (creditCardRepository == null) {
			return true;
		}
		return creditCardRepository.isCardNumberUniq(cardNumber);
	}

}
