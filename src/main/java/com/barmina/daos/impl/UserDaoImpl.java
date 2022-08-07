package com.barmina.daos.impl;

import com.barmina.daos.DaoException;
import com.barmina.daos.UserDao;
import com.barmina.models.Role;
import com.barmina.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
  private static final String INSERT_SQL =
      "INSERT INTO users (email,password,user_role,salt) VALUES (?,?,?,?);";
  private static final String SELECT_WHERE_EMAIL_SQL = "SELECT * FROM users WHERE email = ?;";

  private static final String SELECT_WHERE_PASSWORD_SQL =
      "SELECT * FROM users WHERE email = ? and password = ?;";

  private static final String SELECT_USER_SQL =
      "SELECT user_id,user_role,salt FROM users where email = ?;";

  private static final String DELETE_SQL = "DELETE FROM users WHERE user_id = ?;";

  private static final String SELECT_SQL = "SELECT * FROM users;";

  @Override
  public void insertUser(User user) throws DaoException {
    try (PreparedStatement statement = createStatement(INSERT_SQL)) {
      statement.setString(1, user.getEmail());
      statement.setString(2, user.getPassword());
      statement.setString(3, String.valueOf(user.getRole()));
      statement.setBytes(4, user.getSalt());
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot register user", ex);
    }
  }

  @Override
  public boolean selectByEmail(User user) throws DaoException {
    try (PreparedStatement statement = createStatement(SELECT_WHERE_EMAIL_SQL)) {
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
    try (PreparedStatement statement = createStatement(SELECT_WHERE_PASSWORD_SQL)) {
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
  public User getUser(User user) throws DaoException {
    try (PreparedStatement statement = createStatement(SELECT_USER_SQL)) {
      statement.setString(1, user.getEmail());
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          user.setId(resultSet.getLong(1));
          user.setRole(Role.valueOf(resultSet.getString(2)));
          user.setSalt(resultSet.getBytes(3));
        }
      }
    } catch (SQLException ex) {
      throw new DaoException("Cannot get user", ex);
    }
    return user;
  }

  @Override
  public void delete(Long userId) throws DaoException {
    try (PreparedStatement statement = createStatement(DELETE_SQL)) {
      statement.setLong(1, userId);
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot delete user", ex);
    }
  }

  @Override
  public List<User> getAll() throws DaoException {
    List<User> allUsers = new ArrayList<>();
    try (PreparedStatement statement = createStatement(SELECT_SQL);
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

  @Override
  public void updateRole(Long userId, Role role) throws DaoException {
    try (PreparedStatement statement =
        createStatement("UPDATE users SET user_role = ? WHERE user_id = ?")) {
      statement.setLong(2, userId);
      statement.setString(1, String.valueOf(role));
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot get all users", ex);
    }
  }

  private PreparedStatement createStatement(String SQL) throws SQLException {
    Connection connection = DataSource.getConnection();
    return connection.prepareStatement(SQL);
  }
}
