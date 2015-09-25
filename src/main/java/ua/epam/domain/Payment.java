package ua.epam.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "payment")
@NamedQueries({
	@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p"),
	@NamedQuery(name="Payment.getAmountForAccount", query="SELECT p FROM Payment p WHERE p.payer_account_id.id = :accountId")
})
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
		super();
		// TODO Auto-generated constructor stub
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

}
