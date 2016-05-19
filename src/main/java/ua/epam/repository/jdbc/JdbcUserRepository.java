package ua.epam.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ua.epam.domain.Account;
import ua.epam.domain.CreditCard;
import ua.epam.domain.Role;
import ua.epam.domain.User;
import ua.epam.domain.UserAuthorization;
import ua.epam.domain.UserInformation;
import ua.epam.repository.interfaces.UserRepository;

@Repository
@Qualifier("jdbcUserRepository")
public class JdbcUserRepository implements UserRepository {
	@Autowired
	@Qualifier("jdbcDataSource")
	DataSource dataSource;

	@Override
	public void save(User user) {
		Connection conn = null;
		Long userId = null;
		Account account = user.getAccount();
		UserInformation information = user.getUserInformation();
		Set<UserAuthorization> roles = user.getRoles();
		String insertUserQuery = "INSERT INTO user_authentication (enabled, password, username) values (?,?,?);";
		String insertAccountQuery = "INSERT INTO account(is_active, balance, user_id) values(?,?,?);";
		String insertInformationQuery = "INSERT INTO user_information (email, firstname, lastname, user_id) values (?,?,?,?);";
		String insertRolesQuery = "INSERT INTO user_authorization (role, user_id) values (?, ?);";
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			ResultSet rs = null;
			try (PreparedStatement statement = conn.prepareStatement(
					insertUserQuery, Statement.RETURN_GENERATED_KEYS)) {
				statement.setBoolean(1, user.getEnabled());
				statement.setString(2, user.getPassword());
				statement.setString(3, user.getUsername());
				statement.executeUpdate();
				rs = statement.getGeneratedKeys();
				if (rs.next()) {
					userId = rs.getLong(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException(e);
			} finally {
				if (rs != null) {
					rs.close();
				}
			}

			try (PreparedStatement statement = conn
					.prepareStatement(insertAccountQuery)) {
				statement.setBoolean(1, account.getActive());
				statement.setDouble(2, account.getBalance());
				statement.setLong(3, userId);
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException(e);
			}
			try (PreparedStatement statement = conn
					.prepareStatement(insertInformationQuery)) {
				statement.setString(1, information.getEmail());
				statement.setString(2, information.getFirstName());
				statement.setString(3, information.getLastName());
				statement.setLong(4, userId);
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException(e);
			}
			try (PreparedStatement statement = conn
					.prepareStatement(insertRolesQuery)) {
				for (UserAuthorization auth : roles) {
					statement.setString(1, auth.getRole().toString());
					statement.setLong(2, userId);
					statement.addBatch();
				}
				statement.executeBatch();

			} catch (SQLException e) {
				e.printStackTrace();
				throw new SQLException(e);
			}
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByUsername(String username) {
		String userQuery = "SELECT user_authentication.id, enabled, password, username,"
				+ "account.id as account_id, is_active, balance, user_information.id as user_information_id,email, firstname, lastname "
				+ "FROM user_authentication "
				+ "JOIN account "
				+ "ON user_authentication.id = account.user_id "
				+ "JOIN user_information "
				+ "ON user_authentication.id = user_information.user_id "
				+ "WHERE user_authentication.username = ?;";
		String creditCardsQuery = "SELECT * FROM credit_card WHERE account_id = ?;";
		ResultSet rs = null;
		User user = null;
		Account account = null;
		UserInformation userInformation = null;
		Long userId, accountId = null;
		Set<CreditCard> cards = new HashSet<CreditCard>();
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement userStatement = conn
					.prepareStatement(userQuery);) {
				userStatement.setString(1, username);
				rs = userStatement.executeQuery();
				while (rs.next()) {
					userId = rs.getLong("id");
					accountId = rs.getLong("account_id");
					user = new User(rs.getString("username"),
							rs.getString("password"), rs.getBoolean("enabled"));
					account = new Account(rs.getDouble("balance"),
							rs.getBoolean("is_active"), user);
					userInformation = new UserInformation(
							rs.getString("firstname"),
							rs.getString("lastname"), rs.getString("email"),
							user);
					user.setId(userId);
					account.setId(accountId);
					user.setAccount(account);
					user.setUserInformation(userInformation);
				}
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			try (PreparedStatement cardStatement = conn
					.prepareStatement(creditCardsQuery);) {
				cardStatement.setLong(1, accountId);
				rs = cardStatement.executeQuery();
				while (rs.next()) {
					CreditCard card = new CreditCard(rs.getString("cvv2"),
							rs.getString("card_number"), rs.getDouble("amount"));
					card.setId(rs.getLong("id"));
					cards.add(card);
				}
				account.setCreditCards(cards);
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean isEmailUniq(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameUniq(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> findAllWithUserRole() {
		// TODO Auto-generated method stub
		return null;
	}

}
