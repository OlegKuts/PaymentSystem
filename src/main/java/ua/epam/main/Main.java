package ua.epam.main;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import ua.epam.domain.User;
import ua.epam.repository.interfaces.UserRepository;

@Component
public class Main {

	@Autowired
	UserRepository userRepository;

	void saveUser(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		userRepository.save(user);
	}

	private  void findUser() {
		User user = userRepository.find((long)1);
		System.out.println(user);
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext(
				"repositoryContext.xml");

		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
				new String[] { "appContext.xml" }, repositoryContext);

		Main main = repositoryContext.getBean(Main.class);
		main.saveUser("Oleg", "asdfgh");
		main.findUser();
	}


}
