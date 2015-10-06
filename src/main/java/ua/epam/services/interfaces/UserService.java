package ua.epam.services.interfaces;

import java.util.List;

import ua.epam.domain.User;
import ua.epam.form.UserForm;

public interface UserService {
	public void registerUser(UserForm userForm);

	public User getUserByName(String username);

	List<User> getAllUsers();

	public User find(Long userId);

	public User findByUsername(String username);
	
	List<User> getAllUsersWithUserRole();
}
