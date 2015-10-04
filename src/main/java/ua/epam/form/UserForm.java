package ua.epam.form;

import javax.validation.Valid;

import ua.epam.domain.User;
import ua.epam.domain.UserInformation;

public class UserForm {
	@Valid
	private User user;
	private UserInformation userInformation;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserInformation getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}
}
