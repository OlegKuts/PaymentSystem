package ua.epam.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_authentication")
public class UserAuthentication extends BaseEntity {
	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public UserAuthentication() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
