package com.barmina.servlets.cart;

import com.barmina.models.User;
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
  private final ShoppingCartService cartService = new ShoppingCartService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    cartService.delete(user.getId(), Long.valueOf(request.getParameter("productId")));
    response.sendRedirect(request.getContextPath() + "/shoppingCart");
  }
}
