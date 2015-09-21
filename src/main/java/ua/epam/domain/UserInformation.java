package ua.epam.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class UserInformation extends BaseEntity {
	private String firstName;
	private String lastName;
	private String email;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public UserInformation() {

	}

	
}
