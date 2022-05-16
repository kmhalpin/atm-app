package com.atm.app.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DB {
  private static HikariDataSource ds;

  static {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl("jdbc:mariadb://localhost:3306/atm-app-db");
    config.setUsername("root");
    config.setPassword("");

    ds = new HikariDataSource(config);
  }

  static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}
