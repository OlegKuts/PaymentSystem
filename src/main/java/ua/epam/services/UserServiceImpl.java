package ua.epam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.epam.domain.Account;
import ua.epam.domain.Role;
import ua.epam.domain.User;
import ua.epam.domain.UserAuthorization;
import ua.epam.domain.UserInformation;
import ua.epam.form.UserForm;
import ua.epam.repository.interfaces.UserRepository;
import ua.epam.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	@Qualifier("jdbcUserRepository")
	UserRepository userRepository;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	@Transactional
	public void registerUser(UserForm userForm) {
		User user = new User();
		String encodedPassword = encoder.encode(userForm.getUser()
				.getPassword());
		user.setEnabled(Boolean.TRUE);
		user.setUsername(userForm.getUser().getUsername());
		user.setPassword(encodedPassword);
		user.getRoles().add(new UserAuthorization(Role.ROLE_USER, user));
		//user.getRoles().add(new UserAuthorization(Role.ROLE_ADMIN, user));// for
																			// testing
																			// purposes.;
		user.setUserInformation(new UserInformation(userForm
				.getUserInformation().getFirstName(), userForm
				.getUserInformation().getLastName(), userForm
				.getUserInformation().getEmail(), user));
		user.setAccount(new Account(0.0, true, user));
		userRepository.save(user);
	}

	

	@Override
	public User find(Long userId) {
		return userRepository.find(userId);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public List<User> getAllUsersWithUserRole() {
		return userRepository.findAllWithUserRole();
	}
}
