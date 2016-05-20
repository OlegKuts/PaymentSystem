package ua.epam.main;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.epam.domain.Account;
import ua.epam.domain.CreditCard;
import ua.epam.domain.Payment;
import ua.epam.repository.interfaces.AccountRepository;
import ua.epam.repository.interfaces.CreditCardRepository;
import ua.epam.repository.interfaces.PaymentRepository;
import ua.epam.repository.interfaces.UserRepository;
import ua.epam.repository.jdbc.JdbcUserRepository;

public class Main {

	public static void main(String[] args) {

		ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext(
				"repositoryContext.xml");

		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] { "appContext.xml" }, repositoryContext);

		UserRepository repository = (UserRepository) repositoryContext
				.getBean("jdbcUserRepository");
		CreditCardRepository repository2 = (CreditCardRepository) repositoryContext
				.getBean("jdbcCreditCardRepository");
		System.out.println(repository.isUsernameUniq("user3"));
		System.out.println(repository2.isCardNumberUniq("123456789222"));

	}
}
