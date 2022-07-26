package servlets.order;

import models.Order;
import service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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
