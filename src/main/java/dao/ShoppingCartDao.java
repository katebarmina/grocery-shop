package dao;

import models.Product;
import models.ShoppingCart;

public interface ShoppingCartDao {

   ShoppingCart add(ShoppingCart cart, Product product);
   ShoppingCart delete(ShoppingCart cart, Product product);
}

