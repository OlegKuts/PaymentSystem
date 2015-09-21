package ua.epam.domain;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext(
				"repositoryContext.xml");

		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] { "appContext.xml" }, repositoryContext);

	}

}
