package com.barmina.servlets.cart;

import com.barmina.models.Product;
import com.barmina.models.ShoppingCart;
import com.barmina.models.User;
import com.barmina.services.ProductService;
import com.barmina.services.ShoppingCartService;

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
    User user = (User) session.getAttribute("user");
    if (user != null) {
      ShoppingCart cart = cartService.getCartById(user.getId());
      Long productID = Long.valueOf(req.getParameter("productId"));
      Product product = productService.getById(productID);
      cartService.add(cart, product);
      resp.sendRedirect(req.getContextPath() + "/listOfProducts");
    } else {
      resp.sendRedirect(req.getContextPath() + "/login");
    }
  }
}
