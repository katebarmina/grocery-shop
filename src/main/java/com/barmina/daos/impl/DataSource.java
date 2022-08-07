package com.barmina.daos.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

class DataSource {
  private static final HikariConfig config = new HikariConfig();
  private static final HikariDataSource ds;

  static {
      config.setDriverClassName("com.mysql.jdbc.Driver");
      config.setJdbcUrl("jdbc:mysql://localhost:3306/shop");
      config.setUsername("root");
      config.setPassword("12345");
      ds = new HikariDataSource(config);
  }

  private DataSource() {}

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}
