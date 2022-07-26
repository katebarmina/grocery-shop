package dao;

import models.Order;
import java.util.List;

public interface OrderDao {
    void create(Order order);

    void updateStatus(Long orderId,String status);

    List<Order> getAll();

    List<Order> getAllById(String userId);

    void deleteById(String orderId);
}
