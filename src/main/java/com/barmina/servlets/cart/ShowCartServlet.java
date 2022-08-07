package com.barmina.servlets.cart;

import com.barmina.models.Item;
import com.barmina.models.ShoppingCart;
import com.barmina.models.User;
import com.barmina.services.ShoppingCartService;

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
  private final ShoppingCartService cartService = new ShoppingCartService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    ShoppingCart cart = cartService.getCartById(user.getId());
    List<Item> products = cart.getProducts();
    request.setAttribute("products", products);
    request
        .getRequestDispatcher(request.getContextPath() + "/showCart.jsp")
        .forward(request, response);
  }
}
