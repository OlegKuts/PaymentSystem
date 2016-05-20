package ua.epam.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.epam.repository.interfaces.AccountRepository;
import ua.epam.repository.interfaces.UserRepository;
import ua.epam.repository.jdbc.JdbcUserRepository;


public class Main {

	public static void main(String[] args) {

		ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext(
				"repositoryContext.xml");

		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] { "appContext.xml" }, repositoryContext);
		
		AccountRepository repository = (AccountRepository) repositoryContext.getBean("jdbcAccountRepository");
		System.out.println(repository.find(1L).getId());

	}
}
