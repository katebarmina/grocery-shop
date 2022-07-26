package service;

import dao.ProductDao;
import dao.impl.ProductsDaoImpl;
import models.Product;

import java.util.List;

public class ProductService {

  private final ProductDao dao = new ProductsDaoImpl();

  public List<Product> getAll() {
    return dao.getAll();
  }

  public Product getById(String id) {
    return dao.getById(id);
  }

  public void add(Product product) {
    dao.add(product);
  }

  public void update(Product product, String id) {
    dao.update(product, id);
  }

  public void delete(String id) {
    dao.delete(id);
  }
}