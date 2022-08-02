package com.barmina.service;

import com.barmina.dao.ShoppingCartDao;
import com.barmina.dao.impl.ShoppingCartDaoImpl;
import com.barmina.models.Item;
import com.barmina.models.Product;
import com.barmina.models.ShoppingCart;

public class ShoppingCartService {
  private final ShoppingCartDao dao = new ShoppingCartDaoImpl();

  public ShoppingCart add(ShoppingCart cart, Product product) {
    if (cart.getProducts().stream().noneMatch(item -> item.getProduct().equals(product))) {
      cart.getProducts().add(new Item(product, 1));
    } else {
      for (Item item : cart.getProducts()) {
        if (item.getProduct().equals(product)) {
          int quantity = item.getQuantity() + 1;
          item.setQuantity(quantity);
        }
      }
    }
    return cart;
  }

  public ShoppingCart delete(ShoppingCart cart, Product product) {
    cart.getProducts().removeIf(item -> item.getProduct().equals(product));
    return cart;
  }
}
