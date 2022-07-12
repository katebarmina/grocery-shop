package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
private List<Item> products;


    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public List<Item> getProducts() {
        return products;
    }

    public void setProducts(List<Item> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShoppingCart{"+ products;
    }
}
