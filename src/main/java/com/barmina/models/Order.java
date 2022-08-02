package com.barmina.models;

public class Order {

  private Long orderId;
  private Long userId;
  private Status status;

  public Order() {}

  public Order(Long userId, Status status) {
    this.userId = userId;
    this.status = status;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
