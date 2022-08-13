package com.barmina.daos.impl;

import com.barmina.daos.DaoException;
import com.barmina.daos.ProductDao;
import com.barmina.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDaoImpl implements ProductDao {
  private static final String SELECT_SQL = "SELECT * FROM products ;";
  private static final String SELECT_BY_ID_SQL = "SELECT * FROM products WHERE id = ?;";
  private static final String INSERT_SQL =
      "INSERT INTO products (name,price,brand,category_id) VALUES (?,?,?,?);";
  private static final String UPDATE_SQL =
      "UPDATE products SET name = ?,price = ?,brand = ?,category_id = ? WHERE id = ?;";
  private static final String DELETE_SQL = "DELETE FROM products WHERE id = ? ;";

  @Override
  public List<Product> getAll() throws DaoException {
    List<Product> listOfProducts = new ArrayList<>();
    try (Connection connection = DataSource.getConnection();
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
  public Product getById(Long productId) throws DaoException {
    Product product = new Product();
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
      statement.setLong(1, productId);
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
    try (Connection connection = DataSource.getConnection();
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
  public void update(Product product, Long productId) throws DaoException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
      statement.setString(1, product.getName());
      statement.setDouble(2, product.getPrice());
      statement.setString(3, product.getBrand());
      statement.setLong(4, product.getCategoryId());
      statement.setLong(5, productId);
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot update product", ex);
    }
  }

  @Override
  public void delete(Long productId) throws DaoException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
      statement.setLong(1, productId);
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Cannot delete product", ex);
    }
  }

}
