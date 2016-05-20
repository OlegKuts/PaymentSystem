package ua.epam.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ua.epam.domain.Account;
import ua.epam.domain.CreditCard;
import ua.epam.repository.interfaces.AccountRepository;

@Repository
@Qualifier("jdbcAccountRepository")
public class JdbcAccountRepository implements AccountRepository {
	@Autowired
	@Qualifier("jdbcDataSource")
	DataSource dataSource;

	@Override
	public void save(Account account) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Account account) {
		String query = "UPDATE account SET is_active = ?, balance = ? WHERE id = ?;";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setBoolean(1, account.getActive());
			statement.setDouble(2, account.getBalance());
			statement.setLong(3, account.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Account find(Long id) {
		String accountQuery = "SELECT * FROM account WHERE id = ?;";
		String cardQuery = "SELECT * FROM credit_card where account_id = ?;";
		Account account = null;
		CreditCard card = null;
		Set<CreditCard> cards = new HashSet<CreditCard>();
		try (Connection conn = dataSource.getConnection();) {
			ResultSet rs = null;
			try (PreparedStatement statement = conn
					.prepareStatement(accountQuery)) {
				statement.setLong(1, id);
				rs = statement.executeQuery();
				while (rs.next()) {
					account = new Account(rs.getDouble("balance"),
							rs.getBoolean("is_active"));
					account.setId(id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					rs.close();
				}
			}
			try (PreparedStatement statement = conn.prepareStatement(cardQuery)) {
				statement.setLong(1, id);
				rs = statement.executeQuery();
				while (rs.next()) {
					card = new CreditCard(rs.getString("cvv2"),
							rs.getString("card_number"), rs.getDouble("amount"));
					card.setId(rs.getLong("id"));
					cards.add(card);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					rs.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public Account findByUsername(String username) {
		String query = "SELECT username, account.id as account_id, is_active, balance "
				+ "FROM user_authentication "
				+ "JOIN account ON user_authentication.id = user_id "
				+ "WHERE username = ?;";
		ResultSet rs = null;
		Account account = null;
		try (Connection conn = dataSource.getConnection();
				PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, username);
			rs = statement.executeQuery();
			while (rs.next()) {
				account = new Account(rs.getDouble("balance"),
						rs.getBoolean("is_active"));
				account.setId(rs.getLong("account_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();

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
		return account;
	}
}
