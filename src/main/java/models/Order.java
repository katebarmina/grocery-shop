package models;

public class Order {

    private long orderId;
    private long userId;
    private Status status;

    public Order() {
    }

    public Order(long userId, Status status) {
        this.userId = userId;
        this.status = status;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
