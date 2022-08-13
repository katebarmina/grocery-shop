package com.barmina.daos;

import com.barmina.models.Item;

import java.util.List;

public interface ItemDao {

  List<Item> getAllByCartId(Long cartId) throws DaoException;

  void updateQuantityById(Item item) throws DaoException;

  void add(Item item) throws DaoException;

  void delete(Item item) throws DaoException;

  void deleteAll(Long cartId) throws DaoException;
}
