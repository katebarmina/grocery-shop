package dao;

import models.Item;
import models.Product;
import models.ShoppingCart;

public class ShoppingCartDao {

    public ShoppingCart add(ShoppingCart cart,Product product){
        if (cart.getProducts().stream().noneMatch(item -> item.getProduct().equals(product))) {
            cart.getProducts().add(new Item(product, 1));
        }else {
           for (Item item: cart.getProducts()) {
                if (item.getProduct().equals(product)){
                   int quantity = item.getQuantity()+1;
                   item.setQuantity(quantity);
                }
            }
        }
        return cart;

    }
    public ShoppingCart remove(ShoppingCart cart,Product product){
      cart.getProducts().removeIf(item -> item.getProduct().equals(product));
      return cart;
    }


}
