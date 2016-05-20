package ua.epam.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ua.epam.domain.Account;
import ua.epam.domain.Payment;
import ua.epam.domain.User;
import ua.epam.domain.UserInformation;
import ua.epam.repository.interfaces.PaymentRepository;

@Repository
@Qualifier("jdbcPaymentRepository")
public class JdbcPaymentRepository implements PaymentRepository {
	@Autowired
	@Qualifier("jdbcDataSource")
	DataSource dataSource;

	@Override
	public void save(Payment payment) {
		String query = "INSERT INTO payment (amount, payment_date, payer_account_id, receiver_account_id) "
				+ "VALUES(?,?,?,?);";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setDouble(1, payment.getAmount());
			statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			statement.setLong(3, payment.getPayerAccount().getId());
			statement.setLong(4, payment.getReceiverAccount().getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Payment> findAllForPayerAccount(Long accountId) {
		String paymentQuery = "SELECT amount, payment_date, receiver_account_id "
				+ "FROM payment WHERE payer_account_id = ?;";
		String userPaymentInfoQuary = "SELECT firstname, lastname, account.id as account_id FROM user_authentication "
				+ "JOIN user_information ON user_authentication.id = user_information.user_id "
				+ "JOIN account ON user_authentication.id = account.user_id "
				+ "WHERE account.id = ?;";
		List<Payment> payments = new ArrayList<Payment>();
		Payment payment;
		Account receiverAccount;
		ResultSet rs = null;
		User user;
		UserInformation info;
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement statement = conn
					.prepareStatement(paymentQuery)) {
				statement.setLong(1, accountId);
				rs = statement.executeQuery();
				while (rs.next()) {
					receiverAccount = new Account();
					receiverAccount.setId(rs.getLong("receiver_account_id"));
					payment = new Payment(rs.getDouble("amount"),
							rs.getDate("payment_date"), null, receiverAccount);
					payments.add(payment);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					rs.close();
				}
			}
			for (Payment p : payments) {
				try (PreparedStatement statement = conn
						.prepareStatement(userPaymentInfoQuary)) {
					statement.setLong(1, p.getReceiverAccount().getId());
					rs = statement.executeQuery();
					while (rs.next()) {
						user = new User();
						info = new UserInformation(rs.getString("firstname"),
								rs.getString("lastname"), null, user);
						user.setUserInformation(info);
						p.getReceiverAccount().setUser(user);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (rs != null) {
						rs.close();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payments;
	}

	@Override
	public List<Payment> findAllForReceiverAccount(Long accountId) {
		String paymentQuery = "SELECT amount, payment_date, payer_account_id "
				+ "FROM payment WHERE receiver_account_id = ?;";
		String userPaymentInfoQuary = "SELECT firstname, lastname, account.id as account_id FROM user_authentication "
				+ "JOIN user_information ON user_authentication.id = user_information.user_id "
				+ "JOIN account ON user_authentication.id = account.user_id "
				+ "WHERE account.id = ?;";
		List<Payment> payments = new ArrayList<Payment>();
		Payment payment;
		Account payerAccount;
		ResultSet rs = null;
		User user;
		UserInformation info;
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement statement = conn
					.prepareStatement(paymentQuery)) {
				statement.setLong(1, accountId);
				rs = statement.executeQuery();
				while (rs.next()) {
					payerAccount = new Account();
					payerAccount.setId(rs.getLong("payer_account_id"));
					payment = new Payment(rs.getDouble("amount"),
							rs.getDate("payment_date"), payerAccount, null);
					payments.add(payment);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					rs.close();
				}
			}
			for (Payment p : payments) {
				try (PreparedStatement statement = conn
						.prepareStatement(userPaymentInfoQuary)) {
					statement.setLong(1, p.getPayerAccount().getId());
					rs = statement.executeQuery();
					while (rs.next()) {
						user = new User();
						info = new UserInformation(rs.getString("firstname"),
								rs.getString("lastname"), null, user);
						user.setUserInformation(info);
						p.getPayerAccount().setUser(user);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (rs != null) {
						rs.close();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payments;
	}
}
