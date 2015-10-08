package ua.epam.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class RefundBalanceForm {
	private Long cardId;
	private Long accountId;
	@Min(value=1, message="amount should be not less than {value}")
	@NotNull(message="enter amount please")
	private Double amount;

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
