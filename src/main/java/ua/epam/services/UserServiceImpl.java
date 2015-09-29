package ua.epam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.epam.domain.Account;
import ua.epam.domain.Role;
import ua.epam.domain.User;
import ua.epam.domain.UserAuthentication;
import ua.epam.domain.UserInformation;
import ua.epam.form.UserForm;
import ua.epam.repository.interfaces.UserRepository;
import ua.epam.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public void registerUser(UserForm userForm) {
		User user = new User();
		user.setUsername(userForm.getUser().getUsername());
		user.setPassword(userForm.getUser().getPassword());
		user.getRoles().add(new UserAuthentication(Role.ROLE_USER, user));
		user.setUserInformation(new UserInformation(userForm
				.getUserInformation().getFirstName(), userForm
				.getUserInformation().getLastName(), userForm
				.getUserInformation().getEmail(), user));
		user.setAccount(new Account(0.0, true, user));
		userRepository.save(user);
	}

	@Override
	public User getUserByName(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
