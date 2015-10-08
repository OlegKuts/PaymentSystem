package ua.epam.controllers.exceptions;

public class NotEnoughFunds extends RuntimeException {

	public NotEnoughFunds(String message) {
		super(message);
	}

}
