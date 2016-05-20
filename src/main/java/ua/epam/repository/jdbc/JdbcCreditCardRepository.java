package ua.epam.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ua.epam.domain.CreditCard;
import ua.epam.repository.interfaces.CreditCardRepository;

@Repository
@Qualifier("jdbcCreditCardRepository")
public class JdbcCreditCardRepository implements CreditCardRepository {
	@Autowired
	@Qualifier("jdbcDataSource")
	DataSource dataSource;

	@Override
	public void save(CreditCard creditCard) {
		String query = "INSERT INTO credit_card (amount, card_number, cvv2, account_id) "
				+ "VALUES (?,?,?,?);";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setDouble(1, creditCard.getAmount());
			statement.setString(2, creditCard.getCardNumber());
			statement.setString(3, creditCard.getCvv2());
			statement.setLong(4, creditCard.getAccount().getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(CreditCard creditCard) {
		//No realization needed for JDBC
	}

	@Override
	public CreditCard find(Long id) {
		String query = "SELECT amount,card_number,cvv2 " + " FROM credit_card "
				+ "WHERE id = ?;";
		CreditCard card = null;
		ResultSet rs = null;
		try (Connection conn = dataSource.getConnection();
				PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setLong(1, id);
			rs = statement.executeQuery();
			while (rs.next()) {
				card = new CreditCard(rs.getString("cvv2"),
						rs.getString("card_number"), rs.getDouble("amount"));
				card.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return card;
	}

	@Override
	public boolean isCardNumberUniq(String cardNumber) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<CreditCard> findAllForAccount(Long accountId) {
		String query = "SELECT id, amount, card_number, cvv2 "
				+ "FROM credit_card WHERE account_id = ?;";
		CreditCard card;
		List<CreditCard> cards = new ArrayList<CreditCard>();
		ResultSet rs;
		try (Connection conn = dataSource.getConnection();
				PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setLong(1, accountId);
			rs = statement.executeQuery();
			while (rs.next()) {
				card = new CreditCard(rs.getString("cvv2"),
						rs.getString("card_number"), rs.getDouble("amount"));
				card.setId(rs.getLong("id"));
				cards.add(card);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cards;
	}

	@Override
	public void delete(CreditCard creditCard) {
		String query = "DELETE FROM credit_card WHERE id = ?";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setLong(1, creditCard.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
