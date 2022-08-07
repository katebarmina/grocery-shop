package com.barmina.servlets.order;

import com.barmina.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteOrder")
public class DeleteOrderServlet extends HttpServlet {
  private final OrderService orderService = new OrderService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Long orderId = Long.valueOf(request.getParameter("orderId"));
    orderService.delete(orderId);
    response.sendRedirect(request.getContextPath() + "/listOfOrders");
  }
}
