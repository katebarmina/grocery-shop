package com.barmina.models;

import java.util.Objects;

public class Item {
  private Long id;

  private Long cartId;
  private Product product;
  private int quantity;

  public Item() {}

  public Item(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public Item(Long cartId, Product product, int quantity) {
    this.cartId = cartId;
    this.product = product;
    this.quantity = quantity;
  }

  public Long getCartId() {
    return cartId;
  }

  public void setCartId(Long cartId) {
    this.cartId = cartId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Item item = (Item) o;
    return quantity == item.quantity && Objects.equals(product, item.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, quantity);
  }
}
