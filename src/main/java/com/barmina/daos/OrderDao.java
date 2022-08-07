package com.barmina.daos;

import com.barmina.models.Order;

import java.util.List;

public interface OrderDao {
  void create(Order order);

  void updateStatus(Long orderId, String status);

  List<Order> getAll();

  List<Order> getAllById(Long userId);

  void deleteById(Long orderId);
}
