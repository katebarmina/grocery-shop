package com.barmina.daos.impl;

import com.barmina.daos.DaoException;
import com.barmina.daos.ItemDao;
import com.barmina.models.Item;
import com.barmina.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

  private final String SELECT_ALL_ITEMS_SQL =
      "SELECT ci.id, ci.product_id, ci.quantity, p.name, p.price, p.brand, p.image \n"
          + "FROM cart_item ci \n"
          + "INNER JOIN products p \n"
          + "ON ci.product_id = p.id \n"
          + "WHERE cart_id = ?;";
  private final String UPDATE_QUANTITY_SQL = "UPDATE cart_item SET quantity = ? WHERE id = ?;";
  private final String INSERT_SQL =
      "INSERT INTO cart_item (cart_id,product_id,quantity) VALUES(?,?,1);";
  private final String DELETE_SQL = "DELETE FROM cart_item WHERE id = ?;";
  private final String DELETE_ALL_SQL = "DELETE FROM cart_item WHERE cart_id = ?;";

  @Override
  public List<Item> getAllByCartId(Long cartId) throws DaoException {
    List<Item> items = new ArrayList<>();
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ITEMS_SQL)) {
      statement.setLong(1, cartId);
      try (ResultSet rs = statement.executeQuery()) {
        while (rs.next()) {
          Product product = new Product();
          Item item = new Item();
          item.setId(rs.getLong(1));
          item.setCartId(cartId);
          product.setId(rs.getLong(2));
          item.setQuantity(rs.getInt(3));
          product.setName(rs.getString(4));
          product.setPrice(rs.getDouble(5));
          product.setBrand(rs.getString(6));
          product.setImage(rs.getString(7));
          item.setProduct(product);
          items.add(item);
        }
      }
    } catch (SQLException ex) {
      throw new DaoException("Couldn't get all items", ex);
    }
    return items;
  }

  @Override
  public void updateQuantityById(Item item) throws DaoException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_QUANTITY_SQL)) {
      statement.setInt(1, item.getQuantity());
      statement.setLong(2, item.getId());
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Couldn't update quantity of the item", ex);
    }
  }

  public void add(Item item) throws DaoException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
      statement.setLong(1, item.getCartId());
      statement.setLong(2, item.getProduct().getId());
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Couldn't add item", ex);
    }
  }

  public void delete(Item item) throws DaoException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
      statement.setLong(1, item.getId());
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Couldn't delete item", ex);
    }
  }

  public void deleteAll(Long cartId) throws DaoException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_ALL_SQL)) {
      statement.setLong(1, cartId);
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Couldn't delete all items", ex);
    }
  }
}
