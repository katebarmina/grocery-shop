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

@WebServlet("/shoppingCart/remove")
public class RemoveFromCartServlet extends HttpServlet {
  private final ProductService productService = new ProductService();
  private final ShoppingCartService cartService = new ShoppingCartService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
    String productId = request.getParameter("productId");
    Product product = productService.getById(productId);
    cart = cartService.delete(cart, product);
    session.setAttribute("cart", cart);
    response.sendRedirect(request.getContextPath() + "/shoppingCart");
  }
}
