package com.barmina.daos;

import com.barmina.models.Item;
import com.barmina.models.Product;
import com.barmina.models.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {
  ShoppingCart getCartByUserId(Long userId) throws DaoException;

  void createCart(Long userId) throws DaoException;

  void addItem(ShoppingCart cart, Product product) throws DaoException;

  List<Item> getAllItems(Long cartId) throws DaoException;

  void updateQuantity(ShoppingCart cart, Product product, int quantity) throws DaoException;

  void deleteItem(ShoppingCart cart, Product product) throws DaoException;
}
