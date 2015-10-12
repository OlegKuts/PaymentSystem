package ua.epam.repository.interfaces;

import java.util.List;

import ua.epam.domain.User;

public interface UserRepository {
	void save(User user);

	void update(User user);

	User findByUsername(String username);
	
	boolean isEmailUniq(String email);

	List<User> findAll();

	User find(Long id);

	boolean isUsernameUniq(String username);
	
	List<User> findAllWithUserRole();
}
