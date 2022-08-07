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

@WebServlet("/shoppingCart/remove")
public class RemoveFromCartServlet extends HttpServlet {
  private final ProductService productService = new ProductService();
  private final ShoppingCartService cartService = new ShoppingCartService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    ShoppingCart cart = cartService.getCartById(user.getId());
    Long productId = Long.valueOf(request.getParameter("productId"));
    Product product = productService.getById(productId);
    cartService.delete(cart, product);
    response.sendRedirect(request.getContextPath() + "/shoppingCart");
  }
}
