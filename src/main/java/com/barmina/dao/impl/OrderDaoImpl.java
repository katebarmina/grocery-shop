package com.barmina.dao.impl;

import com.barmina.dao.DaoException;
import com.barmina.dao.OrderDao;
import com.barmina.db.DataSource;
import com.barmina.models.Order;
import com.barmina.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

  private static final String INSERT_SQL =
      "INSERT INTO orders (user_id,order_status) VALUES (?,?);";
  private static final String UPDATE_SQL = "UPDATE orders SET order_status = ? WHERE order_id = ?;";

  private static final String SELECT_SQL = "SELECT * from orders;";

  private static final String SELECT_WHERE_ID_SQL = "SELECT * from orders WHERE user_id = ?;";

  private static final String DELETE_SQL = "DELETE from orders where order_id = ?;";

  @Override
  public void create(Order order) {
    try (PreparedStatement statement = createStatement(INSERT_SQL)) {
      statement.setLong(1, order.getUserId());
      statement.setString(2, String.valueOf(order.getStatus()));
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot create order", ex);
    }
  }

  @Override
  public void updateStatus(Long orderId, String status) throws DaoException {
    try (PreparedStatement statement = createStatement(UPDATE_SQL)) {
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
    try (PreparedStatement statement = createStatement(SELECT_SQL);
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
  public List<Order> getAllById(String userId) throws DaoException {
    List<Order> allOrders = new ArrayList<>();
    try (PreparedStatement statement = createStatement(SELECT_WHERE_ID_SQL)) {
      statement.setLong(1, Long.parseLong(userId));
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
  public void deleteById(String orderId) throws DaoException {
    try (PreparedStatement statement = createStatement(DELETE_SQL)) {
      statement.setLong(1, Long.parseLong(orderId));
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot delete order", ex);
    }
  }

  private PreparedStatement createStatement(String SQL) throws SQLException {
    Connection connection = DataSource.getConnection();
    return connection.prepareStatement(SQL);
  }
}
