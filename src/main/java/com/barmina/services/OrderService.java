package com.barmina.services;

import com.barmina.daos.OrderDao;
import com.barmina.daos.impl.OrderDaoImpl;
import com.barmina.models.Order;

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

  public List<Order> getAllById(Long userId) {
    return dao.getAllById(userId);
  }

  public void delete(Long orderId) {
    dao.deleteById(orderId);
  }
}
