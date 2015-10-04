package ua.epam.form;

public class PaymentForm {
	private double amount;
	private Long receiverAccountId;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Long getReceiverAccountId() {
		return receiverAccountId;
	}

	public void setReceiverAccountId(Long receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}

}
