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
import java.util.Optional;

public class UserDaoImpl implements UserDao {
  private static final String INSERT_SQL =
      "INSERT INTO users (email,password,role,salt) VALUES (?,?,?,?);";
  private static final String SELECT_BY_EMAIL_SQL = "SELECT * FROM users WHERE email = ?;";

  private static final String SELECT_BY_PASSWORD_SQL =
      "SELECT * FROM users WHERE id = ? AND password = ?;";

  private static final String DELETE_SQL = "DELETE FROM users WHERE id = ?;";

  private static final String SELECT_SQL = "SELECT * FROM users;";
  private static final String UPDATE_SQL = "UPDATE users SET role = ? WHERE id = ?";

  @Override
  public void insertUser(User user) throws DaoException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
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
  public Optional<User> getByEmail(String email) throws DaoException {
    User user = null;
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL_SQL)) {
      statement.setString(1, email);
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          user = new User();
          user.setId(resultSet.getLong(1));
          user.setEmail(email);
          user.setRole(Role.valueOf(resultSet.getString(4)));
          user.setSalt(resultSet.getBytes(5));
        }
      }
    } catch (SQLException ex) {
      throw new DaoException("Cannot find user with this email", ex);
    }
    return Optional.ofNullable(user);
  }

  @Override
  public boolean checkPassword(User user) throws DaoException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_PASSWORD_SQL)) {
      statement.setLong(1, user.getId());
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
  public void delete(Long userId) throws DaoException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
      statement.setLong(1, userId);
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot delete user", ex);
    }
  }

  @Override
  public List<User> getAll() throws DaoException {
    List<User> allUsers = new ArrayList<>();
    try (Connection connection = DataSource.getConnection();
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

  @Override
  public void updateRole(Long userId, Role role) throws DaoException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
      statement.setLong(2, userId);
      statement.setString(1, String.valueOf(role));
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot get all users", ex);
    }
  }

}
