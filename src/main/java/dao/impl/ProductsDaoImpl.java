package dao.impl;

import dao.ProductDao;
import dao.DaoException;
import models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDaoImpl implements ProductDao {
  private static final String SELECT_SQL =
      "SELECT product_id,product_name,price,brand,image FROM products ;";
  private static final String SELECT_WHERE_ID_SQL = "SELECT * FROM products where product_id = ?;";

  private static final String INSERT_SQL =
      "INSERT INTO products (product_name,price,brand,category_id) VALUES (?,?,?,?);";

  private static final String UPDATE_SQL =
      "UPDATE products SET product_name = ?,price = ?,brand = ?,category_id = ? WHERE product_id = ?;";

  private static final String DELETE_SQL = "DELETE FROM products WHERE product_id = ? ;";

  @Override
  public List<Product> getAll() throws DaoException {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException exception) {
      exception.printStackTrace();
    }
    List<Product> listOfProducts = new ArrayList<>();
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
        PreparedStatement statement = connection.prepareStatement(SELECT_SQL);
        ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        Product product = new Product();
        product.setId(resultSet.getLong(1));
        product.setName(resultSet.getString(2));
        product.setPrice(resultSet.getDouble(3));
        product.setBrand(resultSet.getString(4));
        product.setImage(resultSet.getString(5));
        listOfProducts.add(product);
      }

    } catch (SQLException ex) {
      throw new DaoException("Cannot get all products", ex);
    }
    return listOfProducts;
  }

  @Override
  public Product getById(String productId) throws DaoException {
    Product product = new Product();
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
        PreparedStatement statement = connection.prepareStatement(SELECT_WHERE_ID_SQL)) {
      statement.setString(1, productId);
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          product.setId(resultSet.getLong(1));
          product.setName(resultSet.getString(2));
          product.setPrice(resultSet.getDouble(3));
          product.setBrand(resultSet.getString(4));
          product.setCategoryId(resultSet.getLong(5));
          product.setImage(resultSet.getString(6));
        }
      }
    } catch (SQLException ex) {
      throw new DaoException("Cannot get product by id", ex);
    }
    return product;
  }

  @Override
  public void add(Product product) throws DaoException {
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
        PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
      statement.setString(1, product.getName());
      statement.setDouble(2, product.getPrice());
      statement.setString(3, product.getBrand());
      statement.setLong(4, product.getCategoryId());
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot add product", ex);
    }
  }

  @Override
  public void update(Product product, String productId) throws DaoException {
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
        PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
      statement.setString(1, product.getName());
      statement.setDouble(2, product.getPrice());
      statement.setString(3, product.getBrand());
      statement.setLong(4, product.getCategoryId());
      statement.setString(5, productId);
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot update product", ex);
    }
  }

  @Override
  public void delete(String productId) throws DaoException {
    try (Connection connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
        PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
      statement.setString(1, productId);
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot delete product", ex);
    }
  }
}
