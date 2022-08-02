package com.barmina.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

  private Long cartId;
  private List<Item> products;

  public ShoppingCart() {
    products = new ArrayList<>();
  }

  public List<Item> getProducts() {
    return products;
  }

  public Long getCartId() {
    return cartId;
  }

  public void setCartId(Long cartId) {
    this.cartId = cartId;
  }

  public void setProducts(List<Item> products) {
    this.products = products;
  }

  @Override
  public String toString() {
    return "ShoppingCart{" + products;
  }
}
