package dao;

import models.Order;
import models.Status;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderDao {
    private final String URL = "jdbc:mysql://localhost:3306/shop";
    private final String USER = "root";
    private final String PASSWORD = "12345";

    public boolean createOrder(Order order){
        int addedRows = 0;
        final String createOrder = "INSERT INTO orders (user_id,order_status) VALUES (?,?);";
    try {
        Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        PreparedStatement statement = connection.prepareStatement(createOrder);
        statement.setInt(1, (int) order.getUserId());
        statement.setString(2, String.valueOf(order.getStatus()));
        addedRows = statement.executeUpdate();
    }catch (SQLException ex){
        ex.printStackTrace();
    }
    return addedRows!=0;
    }

    public boolean updateStatus(Long orderId,String status){
        String updateStatus = "UPDATE orders SET order_status = ? WHERE order_id ="+orderId+";";
        int changedRows = 0;
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(updateStatus);
            statement.setString(1, status);
            changedRows = statement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return changedRows!=0;
    }

    public List<Order> getAllOrders(){
        String getOrders = "SELECT * from orders;";
        List<Order> allOrders = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(getOrders);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getLong(1));
                order.setUserId(rs.getLong(2));
                order.setStatus(Status.valueOf(rs.getString(3)));
                allOrders.add(order);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return allOrders;
    }

    public List<Order> getUsersOrders(String userId){
        String getUsersOrders = "SELECT * from orders WHERE user_id = "+userId +";";
        List<Order> allOrders = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(getUsersOrders);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getLong(2));
                order.setStatus(Status.valueOf(rs.getString(3)));
                allOrders.add(order);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return allOrders;
    }

    public boolean deleteOrder(String orderId){
        int changedRows = 0;
        String deleteOrder = "DELETE from orders where order_id = "+orderId+";";
        try {
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(deleteOrder);
           changedRows = statement.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return changedRows!=0;
    }
}
