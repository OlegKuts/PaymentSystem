package ua.epam.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
		@NamedQuery(name = "Account.findAllActive", query = "SELECT a FROM Account a WHERE a.active = :active"),
		@NamedQuery(name = "Account.findByUserId", query = "SELECT a FROM Account a WHERE a.user.id = :userId"),
		@NamedQuery(name = "Account.findByUsername", query = "SELECT a FROM Account a WHERE a.user.username = :username")})
@Entity
@Table(name = "account")
public class Account extends BaseEntity {
	private Double balance;
	@Column(name = "is_active")
	private Boolean active;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CreditCard> creditCards;

	@OneToMany(mappedBy = "payerAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Payment> payed;

	@OneToMany(mappedBy = "receiverAccount", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Payment> received;

	public Account() {
	}

	public Account(Double balance, Boolean active, User user) {
		super();
		this.balance = balance;
		this.active = active;
		this.user = user;
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
		if (creditCards == null) {
			creditCards = new HashSet<CreditCard>();
		}
		return creditCards;
	}

	public void setCreditCards(Set<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public List<Payment> getPayed() {
		if (payed == null) {
			payed = new ArrayList<Payment>();
		}
		return payed;
	}

	public void setPayed(List<Payment> payed) {
		this.payed = payed;
	}

	public List<Payment> getReceived() {
		if (received == null) {
			received = new ArrayList<Payment>();
		}
		return received;
	}

	public void setReceived(List<Payment> received) {
		this.received = received;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Account other = (Account) obj;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
