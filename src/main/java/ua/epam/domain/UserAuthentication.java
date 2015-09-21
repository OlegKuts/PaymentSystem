package ua.epam.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
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


}
