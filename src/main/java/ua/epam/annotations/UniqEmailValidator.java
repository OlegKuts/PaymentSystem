package ua.epam.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import ua.epam.repository.interfaces.UserRepository;

public class UniqEmailValidator implements
		ConstraintValidator<UniqEmail, String> {
	@Autowired
	UserRepository userRepository;

	@Override
	public void initialize(UniqEmail annotation) {

	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (userRepository == null) {
			return true;
		}
		return userRepository.isEmailUniq(email);
	}

}
