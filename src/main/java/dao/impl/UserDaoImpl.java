package dao.impl;

import dao.UserDao;
import dao.DaoException;
import models.Role;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
  private static final String INSERT_SQL =
      "INSERT INTO users (email,password,user_role) VALUES (?,?,?);";
  private static final String SELECT_WHERE_EMAIL_SQL = "SELECT * FROM users WHERE email = ?;";

  private static final String SELECT_WHERE_PASSWORD_SQL =
      "SELECT * FROM users WHERE email = ? and password = ?;";

  private static final String SELECT_USER_ID_SQL = "SELECT user_id FROM users where email = ?;";

  private static final String DELETE_SQL = "DELETE FROM users WHERE user_id = ?;";

  private static final String SELECT_SQL = "SELECT * FROM users;";

  @Override
  public boolean isAdmin(User user) {
    String ADMIN_EMAIL = "admin@gmail.com";
    String ADMIN_PASSWORD = "12345";
    return user.getEmail().equals(ADMIN_EMAIL) && user.getPassword().equals(ADMIN_PASSWORD);
  }

  @Override
  public void register(User user) throws DaoException {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
        PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
      Class.forName("com.mysql.jdbc.Driver");
      statement.setString(1, user.getEmail());
      statement.setString(2, user.getPassword());
      statement.setString(3, String.valueOf(user.getRole()));
      statement.execute();
    } catch (SQLException | ClassNotFoundException ex) {
      throw new DaoException("Cannot register user", ex);
    }
  }

  @Override
  public boolean IsRegistered(User user) throws DaoException {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
        PreparedStatement statement = connection.prepareStatement(SELECT_WHERE_EMAIL_SQL)) {
      statement.setString(1, user.getEmail());
      try (ResultSet resultSet = statement.executeQuery()) {
        if (!resultSet.next()) {
          return false;
        }
      }
    } catch (SQLException ex) {
      throw new DaoException("Cannot find user with this email", ex);
    }
    return true;
  }

  @Override
  public boolean checkPassword(User user) throws DaoException {
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
        PreparedStatement statement = connection.prepareStatement(SELECT_WHERE_PASSWORD_SQL)) {
      statement.setString(1, user.getEmail());
      statement.setString(2, user.getPassword());
      try (ResultSet resultSet = statement.executeQuery()) {
        if (!resultSet.next()) {
          return false;
        }
      }
    } catch (SQLException ex) {
      throw new DaoException("Cannot find user with this password", ex);
    }
    return true;
  }

  @Override
  public Long getId(User user) throws DaoException {
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
        PreparedStatement statement = connection.prepareStatement(SELECT_USER_ID_SQL)) {
      statement.setString(1, user.getEmail());
      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          return resultSet.getLong(1);
        }
      }
    } catch (SQLException ex) {
      throw new DaoException("Cannot get user by id", ex);
    }
    return -1L;
  }

  @Override
  public void delete(String userId) throws DaoException {
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
        PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
      statement.setString(1, userId);
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot delete user", ex);
    }
  }

  @Override
  public List<User> getAll() throws DaoException {
    List<User> allUsers = new ArrayList<>();
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
        PreparedStatement statement = connection.prepareStatement(SELECT_SQL);
        ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        User user = new User();
        user.setId(resultSet.getLong(1));
        user.setEmail(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setRole(Role.valueOf(resultSet.getString(4)));
        allUsers.add(user);
      }
    } catch (SQLException ex) {
      throw new DaoException("Cannot get all users", ex);
    }
    return allUsers;
  }
}
