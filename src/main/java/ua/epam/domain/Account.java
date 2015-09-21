package ua.epam.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account extends BaseEntity {
	private Double balance;
	@Column(name = "is_active")
	private Boolean active;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<CreditCard> creditCards;

	@OneToMany(mappedBy = "payerAccount", fetch = FetchType.EAGER)
	private List<Payments> payers;

	@OneToMany(mappedBy = "receiverAccount", fetch = FetchType.EAGER)
	private List<Payments> receivers;

	public Account() {
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(Set<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public List<Payments> getPayers() {
		return payers;
	}

	public void setPayers(List<Payments> payers) {
		this.payers = payers;
	}

	public List<Payments> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<Payments> receivers) {
		this.receivers = receivers;
	}

}
