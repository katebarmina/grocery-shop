package dao.impl;

import dao.OrderDao;
import dao.DaoException;
import models.Order;
import models.Status;
import java.sql.*;
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
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
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
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
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
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
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
  public List<Order> getAllById(String userId) throws DaoException {
    List<Order> allOrders = new ArrayList<>();
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
        PreparedStatement statement = connection.prepareStatement(SELECT_WHERE_ID_SQL)) {
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
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
        PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
      statement.setLong(1, Long.parseLong(orderId));
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot delete order", ex);
    }
  }
}
