package com.barmina.services;

import com.barmina.daos.ItemDao;
import com.barmina.daos.OrderDao;
import com.barmina.daos.ShoppingCartDao;
import com.barmina.daos.impl.ItemDaoImpl;
import com.barmina.daos.impl.OrderDaoImpl;
import com.barmina.daos.impl.ShoppingCartDaoImpl;
import com.barmina.models.Order;
import com.barmina.models.Status;

import java.util.List;

public class OrderService {
  private final OrderDao dao = new OrderDaoImpl();
  private final ShoppingCartDao cartDao = new ShoppingCartDaoImpl();
  private final ItemDao itemDao = new ItemDaoImpl();

  public void create(Long userId) {
    Order order = new Order(userId, Status.PROCESSING);
    Long cartId = cartDao.getCart(userId).get().getCartId();
    itemDao.deleteAll(cartId);
    cartDao.delete(cartId);
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
