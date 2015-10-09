package ua.epam.form;

import javax.validation.Valid;

import ua.epam.domain.CreditCard;
import ua.epam.domain.User;
import ua.epam.domain.UserInformation;

public class UserForm {
	@Valid
	private User user;
	@Valid
	private UserInformation userInformation;
	@Valid
	private CreditCard card;

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

	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}
}
