package dao.impl;

import dao.OrderDAO;
import exception.DAOException;
import models.Order;
import models.Status;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private final String URL = "jdbc:mysql://localhost:3306/shop";
    private final String USER = "root";
    private final String PASSWORD = "12345";

    @Override
    public boolean createOrder(Order order) {
        int addedRows;
        final String insertSql = "INSERT INTO orders (user_id,order_status) VALUES (?,?);";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(insertSql);
            statement.setInt(1, (int) order.getUserId());
            statement.setString(2, String.valueOf(order.getStatus()));
            addedRows = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Cannot create order", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return addedRows != 0;
    }

    @Override
    public boolean updateStatus(Long orderId, String status) throws DAOException {
        String updateSql = "UPDATE orders SET order_status = ? WHERE order_id =" + orderId + ";";
        int changedRows;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(updateSql);
            statement.setString(1, status);
            changedRows = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Cannot update status of the order", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return changedRows != 0;
    }

    @Override
    public List<Order> getAllOrders() throws DAOException {
        String getOrders = "SELECT * from orders;";
        List<Order> allOrders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(getOrders);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getLong(1));
                order.setUserId(resultSet.getLong(2));
                order.setStatus(Status.valueOf(resultSet.getString(3)));
                allOrders.add(order);
            }
        } catch (SQLException ex) {
            throw new DAOException("Cannot get all orders", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allOrders;
    }

    @Override
    public List<Order> getUsersOrders(String userId) throws DAOException {
        String selectSql = "SELECT * from orders WHERE user_id = " + userId + ";";
        List<Order> allOrders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(selectSql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getLong(1));
                order.setStatus(Status.valueOf(resultSet.getString(3)));
                allOrders.add(order);
            }
        } catch (SQLException ex) {
            throw new DAOException("Cannot get user's orders", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allOrders;
    }

    @Override
    public boolean deleteOrder(String orderId) throws DAOException {
        int changedRows;
        String deleteSql = "DELETE from orders where order_id = " + orderId + ";";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(deleteSql);
            changedRows = statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Cannot delete order", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return changedRows != 0;
    }
}
