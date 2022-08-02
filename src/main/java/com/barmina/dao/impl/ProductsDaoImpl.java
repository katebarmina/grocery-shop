package com.barmina.dao.impl;

import com.barmina.dao.DaoException;
import com.barmina.dao.ProductDao;
import com.barmina.db.DataSource;
import com.barmina.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    List<Product> listOfProducts = new ArrayList<>();
    try (PreparedStatement statement = createStatement(SELECT_SQL);
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
    try (PreparedStatement statement = createStatement(SELECT_WHERE_ID_SQL)) {
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
    try (PreparedStatement statement = createStatement(INSERT_SQL)) {
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
    try (PreparedStatement statement = createStatement(UPDATE_SQL)) {
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
    try (PreparedStatement statement = createStatement(DELETE_SQL)) {
      statement.setString(1, productId);
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot delete product", ex);
    }
  }

  private PreparedStatement createStatement(String SQL) throws SQLException {
    Connection connection = DataSource.getConnection();
    return connection.prepareStatement(SQL);
  }
}
