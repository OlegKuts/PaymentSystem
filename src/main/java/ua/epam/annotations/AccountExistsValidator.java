package ua.epam.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ua.epam.repository.interfaces.AccountRepository;

public class AccountExistsValidator implements
		ConstraintValidator<AccountExists, Long> {
	@Autowired
	@Qualifier("jdbcAccountRepository")
	AccountRepository accountRepository;

	@Override
	public void initialize(AccountExists arg0) {

	}

	@Override
	public boolean isValid(Long accountId, ConstraintValidatorContext arg1) {
		if(accountRepository == null) {
			return true;
		}
		if( accountId == null){
			return false;
		}
		return accountRepository.find(accountId) != null;
	}

}
