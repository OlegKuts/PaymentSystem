package ua.epam.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.epam.repository.interfaces.UserRepository;
import ua.epam.repository.jdbc.JdbcUserRepository;


public class Main {

	public static void main(String[] args) {

		ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext(
				"repositoryContext.xml");

		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] { "appContext.xml" }, repositoryContext);
		
		UserRepository repository = (UserRepository) repositoryContext.getBean("jdbcUserRepository");
		System.out.println(repository.findByUsername("user").getAccount().getBalance());

	}
}
