package com.barmina.servlets.cart;

import com.barmina.models.Product;
import com.barmina.models.ShoppingCart;
import com.barmina.service.ProductService;
import com.barmina.service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/shoppingCart/add")
public class AddToCartServlet extends HttpServlet {
  private final ProductService productService = new ProductService();
  private final ShoppingCartService cartService = new ShoppingCartService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
    if (cart == null) {
      cart = new ShoppingCart();
      session.setAttribute("cart", cart);
    }
    String productID = req.getParameter("productId");
    Product product = productService.getById(productID);
    cart = cartService.add(cart, product);
    session.setAttribute("cart", cart);
    resp.sendRedirect(req.getContextPath() + "/listOfProducts");
  }
}
