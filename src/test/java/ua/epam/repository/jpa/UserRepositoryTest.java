package ua.epam.repository.jpa;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ua.epam.domain.User;
import ua.epam.repository.interfaces.UserRepository;
import ua.epam.repository.template.RepositoryTestTemplate;

public class UserRepositoryTest extends RepositoryTestTemplate {

	@Autowired
	UserRepository userRepository;

	@Test
	public void insertUserTest() {
		User user = new User();
		String username = "Oleg";
		user.setUsername(username);
		String password = "qwerty";
		user.setPassword(password);

		userRepository.save(user);

		User userdb = userRepository.find(user.getId());
		assertNotNull(userdb);
		assertEquals(username, userdb.getUsername());
		assertEquals(password, userdb.getPassword());
	}

	@Test
	public void findAllTest() {
		User user = new User();
		User user2 = new User();
		User user3 = new User();
		userRepository.save(user);
		userRepository.save(user2);
		userRepository.save(user3);
		
		List<User> users =	 userRepository.findAll();
		
		assertNotNull(users);
		assertEquals(3, users.size());
	}
	
	@Test
	public void findByUsernameTest(){
		User user = new User();
		User user2 = new User();
		String username = "Oleg";
		String username2 = "Taras";
		user.setUsername(username);
		user2.setUsername(username2);
		
		userRepository.save(user);
		userRepository.save(user2);
		
		User userdb = userRepository.findByUsername(username);
		User userdb2 = userRepository.findByUsername(username2);
		
		assertNotNull(userdb);
		assertEquals(user, userdb);
		assertEquals(user2, userdb2);
	}
	
	public void isUsernameUniqTest(){
		User user = new User();
		User user2 = new User();
		String username = "Oleg";
		String username2 = "Taras";
		String uniqUsername = "Uniq";
		
		user.setUsername(username);
		user2.setUsername(username2);
		
		userRepository.save(user);
		userRepository.save(user2);
		
		System.out.println(userRepository.isUsernameUniq(username2));
		System.out.println(userRepository.isUsernameUniq(uniqUsername));
		
		assertTrue(userRepository.isUsernameUniq(username2));
		assertFalse(userRepository.isUsernameUniq(username2));
	}

}
