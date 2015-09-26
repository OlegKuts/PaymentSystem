package ua.epam.service.interfaces;

import java.util.List;

import ua.epam.domain.User;
import ua.epam.form.UserForm;

public interface UserService {
	public void registerUser(UserForm userForm);

	public User getUserByName(String username);

	List<User> getAllUsers();
}
