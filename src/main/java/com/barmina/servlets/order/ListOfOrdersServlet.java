package com.barmina.servlets.order;

import com.barmina.models.Order;
import com.barmina.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listOfOrders")
public class ListOfOrdersServlet extends HttpServlet {

  private final OrderService orderService = new OrderService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Order> orders = orderService.getAll();
    request.setAttribute("orders", orders);
    request.getRequestDispatcher("/allOrders.jsp").forward(request, response);
  }
}
