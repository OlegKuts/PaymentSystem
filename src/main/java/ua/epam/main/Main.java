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

		PaymentRepository repository = (PaymentRepository) repositoryContext
				.getBean("jdbcPaymentRepository");
		/*List<Payment> list = repository.findAllForReceiverAccount(1L);
		for (Payment p : list) {
			System.out.println(p.getReceiverAccount().getId());
		}*/
		Account account = new Account();
		Account account2 = new Account();
		account.setId(1L);
		account2.setId(15L);
		Payment p = new Payment(333D, account, account2);
		repository.save(p);

	}
}
