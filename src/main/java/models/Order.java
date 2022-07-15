package models;

import java.util.Date;

public class Order {
    private User user;
    private ShoppingCart cart;

    private String address;
    private String cardNumber;
    private String cardHolderName;
    private String mmYY;
    private int cvv;



    public Order(User user, ShoppingCart cart,String address, String cardNumber, String cardHolderName, String mmYY, int cvv) {
        this.user = user;
        this.cart = cart;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.mmYY = mmYY;
        this.cvv = cvv;
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getMmYY() {
        return mmYY;
    }

    public void setMmYY(String mmYY) {
        this.mmYY = mmYY;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
