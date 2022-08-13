package com.barmina.daos.impl;

import com.barmina.daos.DaoException;
import com.barmina.daos.ShoppingCartDao;
import com.barmina.models.ShoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

  private final String INSERT_CART_SQL = "INSERT INTO shopping_cart (user_id) VALUES (?);";
  private final String SELECT_ID_SQL = "SELECT id FROM shopping_cart WHERE user_id = ?;";
  private final String DELETE_SQL = "DELETE FROM shopping_cart WHERE id = ?;";

  public void createCart(Long userId) throws DaoException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_CART_SQL)) {
      statement.setLong(1, userId);
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Couldn't create cart", ex);
    }
  }

  public Optional<ShoppingCart> getCart(Long userId) {
    ShoppingCart cart = null;
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ID_SQL)) {
      statement.setLong(1, userId);
      try (ResultSet rs = statement.executeQuery()) {
        while (rs.next()) {
          cart = new ShoppingCart();
          cart.setCartId(rs.getLong(1));
        }
      }
    } catch (SQLException ex) {
      throw new DaoException("Couldn't get cart id", ex);
    }
    return Optional.ofNullable(cart);
  }

  public void delete(Long id) throws DaoException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
      statement.setLong(1, id);
      statement.execute();
    } catch (SQLException ex) {
      throw new DaoException("Couldn't delete cart", ex);
    }
  }
}
