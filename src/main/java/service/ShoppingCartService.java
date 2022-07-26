package service;

import dao.ShoppingCartDao;
import dao.impl.ShoppingCartDaoImpl;
import models.Product;
import models.ShoppingCart;

public class ShoppingCartService {
  private final ShoppingCartDao dao = new ShoppingCartDaoImpl();

  public ShoppingCart add(ShoppingCart cart, Product product) {
    return dao.add(cart, product);
  }

  public ShoppingCart delete(ShoppingCart cart, Product product) {
    return dao.delete(cart, product);
  }
}
