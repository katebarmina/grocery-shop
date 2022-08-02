package com.barmina.servlets.cart;

import com.barmina.models.Item;
import com.barmina.models.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/shoppingCart")
public class ShowCartServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    HttpSession session = request.getSession();
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
    if (cart == null) {
      cart = new ShoppingCart();
      session.setAttribute("cart", cart);
    }
    List<Item> products = cart.getProducts();
    request.setAttribute("products", products);
    request
        .getRequestDispatcher(request.getContextPath() + "/showCart.jsp")
        .forward(request, response);
  }
}
