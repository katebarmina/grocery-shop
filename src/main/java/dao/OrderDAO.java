package dao;

import models.Order;
import java.util.List;

public interface OrderDAO{
    boolean createOrder(Order order);

    boolean updateStatus(Long orderId,String status);

    List<Order> getAllOrders();

    List<Order> getUsersOrders(String userId);

    boolean deleteOrder(String orderId);
}
