package com.barmina.services;

import com.barmina.daos.ShoppingCartDao;
import com.barmina.daos.impl.ShoppingCartDaoImpl;
import com.barmina.models.Product;
import com.barmina.models.ShoppingCart;

public class ShoppingCartService {
  private final ShoppingCartDao cartDao = new ShoppingCartDaoImpl();

  public ShoppingCart getCartById(Long userId) {
    final ShoppingCart cart = cartDao.getCartByUserId(userId);
    if (cart.getCartId() != null) {
      cart.setProducts(cartDao.getAllItems(cart.getCartId()));
      return cart;
    }
    cartDao.createCart(userId);
    return cartDao.getCartByUserId(userId);
  }

  public void add(ShoppingCart cart, Product product) {
    if (cart.getProducts().stream()
        .noneMatch(item -> item.getProduct().getId().equals(product.getId()))) {
      cartDao.addItem(cart, product);
    } else {
      cart.getProducts()
          .forEach(
              item -> {
                if (item.getProduct().getId().equals(product.getId())) {
                  int quantity = item.getQuantity() + 1;
                  cartDao.updateQuantity(cart, product, quantity);
                }
              });
    }
  }

  public void delete(ShoppingCart cart, Product product) {
    cartDao.deleteItem(cart, product);
  }
}
