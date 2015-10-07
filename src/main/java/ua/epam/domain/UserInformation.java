package ua.epam.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "user_information")
public class UserInformation extends BaseEntity {
	@Column(name = "firstname")
	@Size(min = 1, max = 20, message = "First name")
	private String firstName;
	@Column(name = "lastname")
	@Size(min = 1, max = 20, message = "Last name")
	private String lastName;
	@Email(message="ivaild email")
	@Size(min=4)
	private String email;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public UserInformation() {

	}

	public UserInformation(String firstName, String lastName, String email,
			User user) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
