package com.barmina.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

  private Long cartId;
  private List<Item> items;

  public ShoppingCart() {
    items = new ArrayList<>();
  }


  public Long getCartId() {
    return cartId;
  }

  public void setCartId(Long cartId) {
    this.cartId = cartId;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return "ShoppingCart{" + items;
  }
}
