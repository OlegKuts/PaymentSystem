package ua.epam.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ua.epam.repository.interfaces.CreditCardRepository;

public class UniqCardNumberValidator implements
		ConstraintValidator<UniqCardNumber, String> {
	@Autowired
	CreditCardRepository creditCardRepository;

	@Override
	public void initialize(UniqCardNumber annotation) {

	}

	@Override
	public boolean isValid(String cardNumber, ConstraintValidatorContext context) {
		return creditCardRepository.isCardNumberUniq(cardNumber);
	}

}
