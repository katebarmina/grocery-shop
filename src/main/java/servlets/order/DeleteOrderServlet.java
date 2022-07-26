package servlets.order;

import service.OrderService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/deleteOrder")
public class DeleteOrderServlet extends HttpServlet {
  private final OrderService orderService = new OrderService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String orderId = request.getParameter("orderId");
    orderService.delete(orderId);
    response.sendRedirect(request.getContextPath() + "/listOfOrders");
  }
}
