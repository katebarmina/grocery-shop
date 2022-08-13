package com.barmina.services;

import com.barmina.daos.ItemDao;
import com.barmina.daos.ShoppingCartDao;
import com.barmina.daos.impl.ItemDaoImpl;
import com.barmina.daos.impl.ShoppingCartDaoImpl;
import com.barmina.models.Item;
import com.barmina.models.Product;
import com.barmina.models.ShoppingCart;

import java.util.List;

public class ShoppingCartService {

  private final ItemDao itemDao = new ItemDaoImpl();
  private final ShoppingCartDao cartDao = new ShoppingCartDaoImpl();

  private final ProductService productService = new ProductService();

  private ShoppingCart getCartById(Long userId) {
    ShoppingCart cart =
        cartDao
            .getCart(userId)
            .orElseGet(
                () -> {
                  cartDao.createCart(userId);
                  return cartDao.getCart(userId).get();
                });
    cart.setItems(itemDao.getAllByCartId(cart.getCartId()));
    return cart;
  }

  public List<Item> getItems(Long userId) {
    ShoppingCart cart = getCartById(userId);
    return itemDao.getAllByCartId(cart.getCartId());
  }

  public void add(Long userId, Long productId) {
    ShoppingCart cart = getCartById(userId);
    Product product = productService.getById(productId);
    cart.getItems().stream()
        .filter(item -> item.getProduct().getId().equals(productId))
        .findFirst()
        .ifPresentOrElse(
            item -> {
              item.setQuantity(item.getQuantity() + 1);
              itemDao.updateQuantityById(item);
            },
            () -> itemDao.add(new Item(cart.getCartId(), product, 1)));
  }

  public void delete(Long userId, Long productId) {
    ShoppingCart cart = getCartById(userId);
    Item deletedItem =
        cart.getItems().stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst()
            .get();
    deletedItem.setQuantity(deletedItem.getQuantity() - 1);
    if (deletedItem.getQuantity() == 0) {
      itemDao.delete(deletedItem);
    } else {
      itemDao.updateQuantityById(deletedItem);
    }
  }
}
