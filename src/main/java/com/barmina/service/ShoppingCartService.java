package com.barmina.service;

import com.barmina.dao.ShoppingCartDao;
import com.barmina.dao.impl.ShoppingCartDaoImpl;
import com.barmina.models.Product;
import com.barmina.models.ShoppingCart;

public class ShoppingCartService {
  private final ShoppingCartDao dao = new ShoppingCartDaoImpl();

  public ShoppingCart getCartById(String userId) {
    ShoppingCart cart = dao.getCartByUserId(userId);
    if (cart.getCartId() != null) {
      cart.setProducts(dao.getAllItems(String.valueOf(cart.getCartId())));
      return cart;
    }
    dao.createCart(userId);
    return dao.getCartByUserId(userId);
  }

  public void add(ShoppingCart cart, Product product) {
    if (cart.getProducts().stream()
        .noneMatch(item -> item.getProduct().getId().equals(product.getId()))) {
      dao.addItem(cart, product);
    } else {
      cart.getProducts()
          .forEach(
              item -> {
                if (item.getProduct().getId().equals(product.getId())) {
                  int quantity = item.getQuantity() + 1;
                  dao.updateQuantity(cart, product, quantity);
                }
              });
    }
  }

  public void delete(ShoppingCart cart, Product product) {
    dao.deleteItem(cart, product);
  }
}
