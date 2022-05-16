package com.atm.app.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.atm.app.domain.entity.Transaction;

public class DBTransactionRepository implements com.atm.app.domain.repository.TransactionRepository {
  private String insertQuery = "INSERT INTO transaction (account_from, account_to, balance, type) VALUES (?, ?, ?, ?)";

  @Override
  public Transaction create(Transaction transaction) {
    try (Connection conn = DB.getConnection();
        PreparedStatement pStatement = conn.prepareStatement(insertQuery);) {
      pStatement.setString(1, transaction.getAccountFrom());
      pStatement.setString(2, transaction.getAccountTo());
      pStatement.setBigDecimal(3, new BigDecimal(transaction.getBalance()));
      pStatement.setString(4, transaction.getType().label);
      pStatement.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return transaction;
  }

  private String getByIDQuery = "SELECT * FROM transaction WHERE id = ?";

  @Override
  public Transaction getByID(int id) {
    ResultSet rsSet = null;
    try (Connection conn = DB.getConnection();
        PreparedStatement pStatement = conn.prepareStatement(getByIDQuery);) {
      pStatement.setInt(1, id);
      rsSet = pStatement.executeQuery();

      if (rsSet.next())
        return new Transaction(
            rsSet.getInt("id"),
            rsSet.getString("account_from"),
            rsSet.getString("account_to"),
            rsSet.getBigDecimal("balance").longValue(),
            rsSet.getString("type"),
            rsSet.getTimestamp("created_at").getNanos(),
            rsSet.getTimestamp("updated_at").getNanos());
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

  @Override
  public List<Transaction> find() {
    List<Transaction> transactions = new LinkedList<>();
    try (Connection conn = DB.getConnection();
        Statement statement = conn.createStatement();
        ResultSet rsSet = statement.executeQuery("SELECT * FROM transaction");) {
      while (rsSet.next()) {
        transactions.add(new Transaction(
            rsSet.getInt("id"),
            rsSet.getString("account_from"),
            rsSet.getString("account_to"),
            rsSet.getBigDecimal("balance").longValue(),
            rsSet.getString("type"),
            rsSet.getTimestamp("created_at").getNanos(),
            rsSet.getTimestamp("updated_at").getNanos()));
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return transactions;
  }

  private String findByAccountQuery = "SELECT * FROM transaction WHERE account_from = ? OR account_to = ? ORDER BY created_at DESC";

  @Override
  public List<Transaction> findByAccount(String account) {
    ResultSet rsSet = null;
    List<Transaction> transactions = new LinkedList<>();
    try (Connection conn = DB.getConnection();
        PreparedStatement pStatement = conn.prepareStatement(findByAccountQuery);) {
      pStatement.setString(1, account);
      pStatement.setString(2, account);
      rsSet = pStatement.executeQuery();
      while (rsSet.next()) {
        transactions.add(new Transaction(
            rsSet.getInt("id"),
            rsSet.getString("account_from"),
            rsSet.getString("account_to"),
            rsSet.getBigDecimal("balance").longValue(),
            rsSet.getString("type"),
            rsSet.getTimestamp("created_at").getNanos(),
            rsSet.getTimestamp("updated_at").getNanos()));
      }
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
    return transactions;
  }
}
