package com.barmina.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
  private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();

  static {
      try{
          dataSource.setDriverClass("com.mysql.jdbc.Driver");
          dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/shop");
          dataSource.setUser("root");
          dataSource.setPassword("12345");
      }catch (PropertyVetoException ex){
      System.out.println("Couldn't create connection");
      }
  }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private DataSource() {
    }
}
