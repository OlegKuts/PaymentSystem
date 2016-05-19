package ua.epam.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ua.epam.annotations.UniqCardNumber;

@Entity
@Table(name = "credit_card")
@NamedQueries({
		@NamedQuery(name = "CreditCard.findAll", query = "SELECT c FROM CreditCard c "),
		@NamedQuery(name = "CreditCard.findByCardNumber", query = "SELECT c FROM CreditCard c WHERE c.cardNumber = :cardNumber"),
		@NamedQuery(name = "CreditCard.getAmountForAccount", query = "SELECT count(c) FROM CreditCard c WHERE c.account.id = :accountId"),
		@NamedQuery(name = "CreditCard.findAllForAccount", query = "SELECT c FROM CreditCard c WHERE c.account.id = :accountId") })
public class CreditCard extends BaseEntity {
	@Size(min=3, max=3,message="cvv2 size must be {min}" )
	@Pattern(regexp="[\\d]*", message="cvv2 must consist of numbers only")
	private String cvv2;
	@Column(name = "card_number")
	@Size(min=12, max=12, message="Card number must contain {min} numbers")
	@UniqCardNumber(message = "Credit card with this number is already registered")
	private String cardNumber;
	private Double amount;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	public CreditCard() {
		super();
	}

	public CreditCard(String cvv2, String cardNumber, Double amount) {
		super();
		this.cvv2 = cvv2;
		this.cardNumber = cardNumber;
		this.amount = amount;
	}

	public String getCvv2() {
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((cvv2 == null) ? 0 : cvv2.hashCode());
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
		CreditCard other = (CreditCard) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (cvv2 == null) {
			if (other.cvv2 != null)
				return false;
		} else if (!cvv2.equals(other.cvv2))
			return false;
		return true;
	}

}
