package dao;

import models.Item;
import models.Product;
import models.ShoppingCart;

public interface ShoppingCartDAO {

   ShoppingCart add(ShoppingCart cart, Product product);
   ShoppingCart remove(ShoppingCart cart, Product product);
}

