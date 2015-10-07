package ua.epam.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ua.epam.annotations.UniqueUsername;

@Entity
@Table(name = "user_authentication")
@NamedQueries({
		@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findAllUserRole", query = "SELECT u FROM User u WHERE"
				+ " u.id  IN(SELECT  a.user.id FROM UserAuthorization a WHERE a.role = 'ROLE_USER')"
				+ " and u.id  NOT IN(SELECT  a.user.id FROM UserAuthorization a WHERE a.role = 'ROLE_ADMIN')"),
		@NamedQuery(name = "User.findByUsername", query = "SELECT  u FROM User u WHERE u.username = :username") })
public class User extends BaseEntity {
	@NotNull
	@Size(min = 4, max = 20, message = "Username must be {min} to {max}")
	@UniqueUsername(message = "Username already exists")
	private String username;
	@NotNull
	@Size(min = 4, max = 20, message = "Password  must contain {min} to {max} characters")
	private String password;
	private Boolean enabled;
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UserInformation userInformation;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Account account;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserAuthorization> roles;

	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public UserInformation getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Set<UserAuthorization> getRoles() {
		if (roles == null) {
			roles = new HashSet<UserAuthorization>();
		}
		return roles;
	}

	public void setRoles(Set<UserAuthorization> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "username=" + username + ", password=" + password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
