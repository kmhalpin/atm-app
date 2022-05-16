package com.atm.app.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.atm.app.common.exception.NotFoundError;
import com.atm.app.domain.entity.User;

public class DBUserRepository implements com.atm.app.domain.repository.UserRepository {
  private String insertQuery = "INSERT INTO account (account_number, security_pin, name, balance) VALUES (?, ?, ?, ?)";

  @Override
  public User create(User user) {
    try (Connection conn = DB.getConnection();
        PreparedStatement pStatement = conn.prepareStatement(insertQuery);) {
      pStatement.setString(1, user.getAccountNumber());
      pStatement.setInt(2, user.getSecurityPin());
      pStatement.setString(3, user.getName());
      pStatement.setBigDecimal(4, new BigDecimal(user.getBalance()));
      pStatement.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return user;
  }

  @Override
  public List<User> find() {
    List<User> users = new LinkedList<>();
    try (Connection conn = DB.getConnection();
        Statement statement = conn.createStatement();
        ResultSet rsSet = statement.executeQuery("SELECT * FROM account");) {
      while (rsSet.next()) {
        users.add(new User(
            rsSet.getString("account_number"),
            rsSet.getInt("security_pin"),
            rsSet.getString("name"),
            rsSet.getBigDecimal("balance").longValue(),
            rsSet.getTimestamp("created_at").getNanos(),
            rsSet.getTimestamp("updated_at").getNanos()));
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return users;
  }

  private String getByAccountNumberQuery = "SELECT * FROM account WHERE account_number = ?";

  @Override
  public User getByAccountNumber(String aNumber) throws Exception {
    ResultSet rsSet = null;
    try (Connection conn = DB.getConnection();
        PreparedStatement pStatement = conn.prepareStatement(getByAccountNumberQuery);) {
      pStatement.setString(1, aNumber);
      rsSet = pStatement.executeQuery();

      if (rsSet.next())
        return new User(
            rsSet.getString("account_number"),
            rsSet.getInt("security_pin"),
            rsSet.getString("name"),
            rsSet.getBigDecimal("balance").longValue(),
            rsSet.getTimestamp("created_at").getNanos(),
            rsSet.getTimestamp("updated_at").getNanos());
      else
          throw new NotFoundError("Account not found");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      if (rsSet != null) {
        try {
          rsSet.close();
        } catch (SQLException e) {
        }
      }
    }
    return null;
  }

  private String updateBalanceQuery = "UPDATE account SET balance = ? WHERE account_number = ?";

  @Override
  public void updateBalance(String aNumber, long balance) {
    try (Connection conn = DB.getConnection();
        PreparedStatement pStatement = conn.prepareStatement(updateBalanceQuery);) {
      pStatement.setBigDecimal(1, new BigDecimal(balance));
      pStatement.setString(2, aNumber);
      pStatement.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
