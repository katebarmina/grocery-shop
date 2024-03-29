package com.barmina.services;

import com.barmina.daos.ProductDao;
import com.barmina.daos.impl.ProductsDaoImpl;
import com.barmina.models.Product;

import java.util.List;

public class ProductService {

  private final ProductDao dao = new ProductsDaoImpl();

  public List<Product> getAll() {
    return dao.getAll();
  }

  public Product getById(Long id) {
    return dao.getById(id);
  }

  public void add(Product product) {
    dao.add(product);
  }

  public void update(Product product, Long id) {
    dao.update(product, id);
  }

  public void delete(Long id) {
    dao.delete(id);
  }
}
