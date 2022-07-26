package models;

import java.util.Objects;

public class Product {
  private Long id;
  private String name;
  private Double price;
  private String brand;

  private Long categoryId;

  private String image;

  public Product() {}

  public Product(String name, Double price, String brand, Long categoryId) {
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

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
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
    return Objects.equals(id, product.id)
        && Objects.equals(name, product.name)
        && Objects.equals(price, product.price)
        && Objects.equals(brand, product.brand)
        && Objects.equals(categoryId, product.categoryId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, price, brand);
  }

  @Override
  public String toString() {
    return name + "\n" + price + "\n" + brand;
  }
}
