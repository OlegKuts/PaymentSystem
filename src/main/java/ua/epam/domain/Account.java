package ua.epam.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Account extends BaseEntity {
	private Double balance;
	private Boolean active;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy="account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<CreditCard> creditCards;

	public Account() {
	}

}
