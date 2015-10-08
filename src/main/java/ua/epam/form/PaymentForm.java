package ua.epam.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import ua.epam.annotations.AccountExists;

public class PaymentForm {
	@Min(value = 1, message = "amount should be not less than {value}")
	@NotNull(message = "enter amount please")
	private Double amount;
	@NotNull(message="account number can not be empty")
	@AccountExists(message="such account doesn't exist")
	private Long receiverAccountId;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getReceiverAccountId() {
		return receiverAccountId;
	}

	public void setReceiverAccountId(Long receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}

}
