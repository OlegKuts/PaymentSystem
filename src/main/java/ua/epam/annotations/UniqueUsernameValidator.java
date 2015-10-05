package ua.epam.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ua.epam.repository.interfaces.UserRepository;

public class UniqueUsernameValidator implements
		ConstraintValidator<UniqueUsername, String> {
	@Autowired
	UserRepository userRepository;

	@Override
	public void initialize(UniqueUsername annotation) {

	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		/*
		 * for database population purposes
		 */
		if (userRepository == null) {
			return true;
		}
		return userRepository.isUsernameUniq(username);
	}

}
