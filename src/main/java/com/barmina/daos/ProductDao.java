package com.barmina.daos;

import com.barmina.models.Product;

import java.util.List;

public interface ProductDao {

  List<Product> getAll() throws DaoException;

  Product getById(Long productId) throws DaoException;

  void add(Product product) throws DaoException;

  void update(Product product, Long productId) throws DaoException;

  void delete(Long productId) throws DaoException;
}
