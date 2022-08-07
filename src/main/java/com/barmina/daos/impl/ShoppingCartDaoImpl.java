package com.barmina.daos.impl;

import com.barmina.daos.DaoException;
import com.barmina.daos.ShoppingCartDao;
import com.barmina.models.Item;
import com.barmina.models.Product;
import com.barmina.models.ShoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

  private final String INSERT_ITEM_SQL =
      "INSERT INTO cart_item (cart_id,product_id,quantity) VALUES(?,?,1);";
  private final String DELETE_ITEM_SQL =
      "DELETE from cart_item where cart_id = ? and product_id = ?;";
  private final String SELECT_ALL_ITEMS_SQL =
      "SELECT cart_item.product_id,cart_item.quantity,products.product_name,products.price,products.brand,products.image from cart_item inner join products on cart_item.product_id = products.product_id where cart_id =? ;";

  private final String SELECT_CART_BY_USER_ID = "SELECT id from shopping_cart where user_id = ?;";

  private final String UPDATE_QUANTITY_SQL =
      "UPDATE cart_item set quantity = ? where product_id = ? and cart_id = ?;";
  private final String INSERT_CART_SQL = "INSERT INTO shopping_cart (user_id) VALUES (?);";

  public void createCart(Long userId) throws DaoException {
    try (PreparedStatement statement = createStatement(INSERT_CART_SQL)) {
      statement.setLong(1, userId);
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Couldn't create cart", ex);
    }
  }

  public ShoppingCart getCartByUserId(Long userId) {
    ShoppingCart shoppingCart = new ShoppingCart();
    try (PreparedStatement statement = createStatement(SELECT_CART_BY_USER_ID)) {
      statement.setLong(1, userId);
      try (ResultSet rs = statement.executeQuery()) {
        while (rs.next()) {
          shoppingCart.setCartId(rs.getLong(1));
        }
      }
    } catch (SQLException ex) {
      throw new DaoException("Couldn't get user's cart", ex);
    }
    return shoppingCart;
  }

  @Override
  public void addItem(ShoppingCart cart, Product product) throws DaoException {
    try (PreparedStatement statement = createStatement(INSERT_ITEM_SQL)) {
      statement.setLong(1, cart.getCartId());
      statement.setLong(2, product.getId());
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Couldn't add item", ex);
    }
  }

  @Override
  public void deleteItem(ShoppingCart cart, Product product) throws DaoException {
    try (PreparedStatement statement = createStatement(DELETE_ITEM_SQL)) {
      statement.setLong(1, cart.getCartId());
      statement.setLong(2, product.getId());
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Couldn't delete item", ex);
    }
  }

  @Override
  public List<Item> getAllItems(Long cartId) throws DaoException {
    List<Item> items = new ArrayList<>();
    try (PreparedStatement statement = createStatement(SELECT_ALL_ITEMS_SQL)) {
      statement.setLong(1, cartId);
      try (ResultSet rs = statement.executeQuery()) {
        while (rs.next()) {
          Product product = new Product();
          Item item = new Item();
          product.setId(rs.getLong(1));
          item.setQuantity(rs.getInt(2));
          product.setName(rs.getString(3));
          product.setPrice(rs.getDouble(4));
          product.setBrand(rs.getString(5));
          product.setImage(rs.getString(6));
          item.setProduct(product);
          items.add(item);
        }
      }
    } catch (SQLException ex) {
      throw new DaoException("Couldn't get all items", ex);
    }
    return items;
  }

  public void updateQuantity(ShoppingCart cart, Product product, int quantity) throws DaoException {
    try (PreparedStatement statement = createStatement(UPDATE_QUANTITY_SQL)) {
      statement.setInt(1, quantity);
      statement.setLong(2, product.getId());
      statement.setLong(3, cart.getCartId());
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Couldn't get all items", ex);
    }
  }

  private PreparedStatement createStatement(String SQL) throws SQLException {
    Connection connection = DataSource.getConnection();
    return connection.prepareStatement(SQL);
  }
}
