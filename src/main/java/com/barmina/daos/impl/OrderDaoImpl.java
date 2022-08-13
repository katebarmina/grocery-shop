package com.barmina.daos.impl;

import com.barmina.daos.DaoException;
import com.barmina.daos.OrderDao;
import com.barmina.models.Order;
import com.barmina.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

  private static final String INSERT_SQL = "INSERT INTO orders (user_id,status) VALUES (?,?);";
  private static final String UPDATE_SQL = "UPDATE orders SET status = ? WHERE id = ?;";
  private static final String SELECT_SQL = "SELECT * FROM orders;";
  private static final String SELECT_BY_ID_SQL = "SELECT * FROM orders WHERE user_id = ?;";
  private static final String DELETE_SQL = "DELETE FROM orders WHERE id = ?;";

  @Override
  public void create(Order order) {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
      statement.setLong(1, order.getUserId());
      statement.setString(2, String.valueOf(order.getStatus()));
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot create order", ex);
    }
  }

  @Override
  public void updateStatus(Long orderId, String status) throws DaoException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
      statement.setString(1, status);
      statement.setLong(2, orderId);
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot update status of the order", ex);
    }
  }

  @Override
  public List<Order> getAll() throws DaoException {
    List<Order> allOrders = new ArrayList<>();
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_SQL);
        ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        Order order = new Order();
        order.setOrderId(resultSet.getLong(1));
        order.setUserId(resultSet.getLong(2));
        order.setStatus(Status.valueOf(resultSet.getString(3)));
        allOrders.add(order);
      }
    } catch (SQLException ex) {
      throw new DaoException("Cannot get all orders", ex);
    }
    return allOrders;
  }

  @Override
  public List<Order> getAllById(Long userId) throws DaoException {
    List<Order> allOrders = new ArrayList<>();
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
      statement.setLong(1, userId);
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          Order order = new Order();
          order.setOrderId(resultSet.getLong(1));
          order.setStatus(Status.valueOf(resultSet.getString(3)));
          allOrders.add(order);
        }
      }
    } catch (SQLException ex) {
      throw new DaoException("Cannot get user's orders", ex);
    }
    return allOrders;
  }

  @Override
  public void deleteById(Long orderId) throws DaoException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
      statement.setLong(1,orderId);
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot delete order", ex);
    }
  }
}
