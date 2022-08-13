package com.barmina.daos;

import com.barmina.models.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartDao {

  Optional<ShoppingCart> getCart(Long userId) throws DaoException;

  void createCart(Long userId) throws DaoException;

  void delete(Long cartId) throws DaoException;
}
