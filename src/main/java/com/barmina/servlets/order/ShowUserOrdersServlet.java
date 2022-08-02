package com.barmina.servlets.order;

import com.barmina.models.Order;
import com.barmina.models.User;
import com.barmina.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/showUserOrders")
public class ShowUserOrdersServlet extends HttpServlet {

  private final OrderService orderService = new OrderService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    List<Order> userOrders = orderService.getAllById(String.valueOf(user.getId()));
    request.setAttribute("userOrders", userOrders);
    request.getRequestDispatcher("/showUserOrders.jsp").forward(request, response);
  }
}
