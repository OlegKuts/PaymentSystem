package ua.epam.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "payment")
@NamedQueries({
		@NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p"),
		@NamedQuery(name = "Payment.findAllForPayerAccount", query = "SELECT p FROM Payment p WHERE p.payerAccount.id = :accountId"),
		@NamedQuery(name = "Payment.findAllForReceiverAccount", query = "SELECT p FROM Payment p WHERE p.receiverAccount.id = :accountId"),
		@NamedQuery(name = "Payment.getAmountForPayerAccount", query = "SELECT count(p) FROM Payment p WHERE p.payerAccount.id = :accountId"),
		@NamedQuery(name = "Payment.getAmountForReceiverAccount", query = "SELECT count(p) FROM Payment p WHERE p.receiverAccount.id = :accountId") })
public class Payment extends BaseEntity {
	private Double amount;
	@Column(name = "payment_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate;

	@ManyToOne
	@JoinColumn(name = "payer_account_id")
	private Account payerAccount;

	@ManyToOne
	@JoinColumn(name = "receiver_account_id")
	private Account receiverAccount;

	public Payment() {
	}

	public Payment(Double amount, Account payerAccount, Account receiverAccount) {
		this.amount = amount;
		this.payerAccount = payerAccount;
		this.receiverAccount = receiverAccount;
	}

	public Payment(Double amount, Date paymentDate, Account payerAccount,
			Account receiverAccount) {
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.payerAccount = payerAccount;
		this.receiverAccount = receiverAccount;
	}

	@PrePersist
	protected void onCreate() {
		paymentDate  = new Date();
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Account getPayerAccount() {
		return payerAccount;
	}

	public void setPayerAccount(Account payerAccount) {
		this.payerAccount = payerAccount;
	}

	public Account getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(Account receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((paymentDate == null) ? 0 : paymentDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (paymentDate == null) {
			if (other.paymentDate != null)
				return false;
		} else if (!paymentDate.equals(other.paymentDate))
			return false;
		return true;
	}

}
