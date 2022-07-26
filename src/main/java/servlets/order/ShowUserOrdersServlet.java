package servlets.order;

import models.Order;
import models.User;
import service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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
