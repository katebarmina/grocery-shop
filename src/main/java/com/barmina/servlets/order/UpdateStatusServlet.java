package com.barmina.servlets.order;

import com.barmina.services.OrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateStatus")
public class UpdateStatusServlet extends HttpServlet {
  private final OrderService orderService = new OrderService();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String orderId = request.getParameter("orderId");
    String status = request.getParameter("orderStatus");
    orderService.updateStatus(status, Long.valueOf(orderId));
    response.sendRedirect(request.getContextPath() + "/listOfOrders");
  }
}
