package ua.epam.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CreditCard extends BaseEntity {
	private String cvv2;
	private Double amount;
	private String cardNumber;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
}
