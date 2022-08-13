package com.barmina.servlets;

import com.barmina.models.User;
import com.barmina.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
  private final OrderService orderService = new OrderService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (user == null) {
      request.getRequestDispatcher(request.getContextPath() + "/login").forward(request, response);
    }
    request.getRequestDispatcher("/orderInfoForm.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    orderService.create(user.getId());
    response.sendRedirect(request.getContextPath() + "/orderCompleted.jsp");
  }
}
