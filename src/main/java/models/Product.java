package models;

import java.util.Objects;

public class Product {
    private long id;
    private String name;
    private double price;
    private String brand;

    private long categoryId;

    private String image;

    public Product() {
    }

    public Product(String name, double price, String brand, long categoryId) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long category_id) {
        this.categoryId = category_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.price, price) == 0 && Objects.equals(name, product.name) && Objects.equals(brand, product.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, brand);
    }

    @Override
    public String toString() {
        return  name +
                "\n" + price +
                "\n" + brand;
    }
}
