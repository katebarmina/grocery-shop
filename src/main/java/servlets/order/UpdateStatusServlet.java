package servlets.order;

import service.OrderService;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
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
