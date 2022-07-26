package service;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import models.Order;

import java.util.List;

public class OrderService {
  private final OrderDao dao = new OrderDaoImpl();

  public void create(Order order) {
    dao.create(order);
  }

  public void updateStatus(String status, Long orderId) {
    dao.updateStatus(orderId, status);
  }

  public List<Order> getAll() {
    return dao.getAll();
  }

  public List<Order> getAllById(String userId) {
    return dao.getAllById(userId);
  }

  public void delete(String orderId) {
    dao.deleteById(orderId);
  }
}
