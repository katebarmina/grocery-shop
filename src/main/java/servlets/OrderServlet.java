package servlets;

import models.Order;
import models.Status;
import models.User;
import service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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
    Order order = new Order(user.getId(), Status.PROCESSING);
    orderService.create(order);
    response.sendRedirect(request.getContextPath() + "/orderCompleted.jsp");
  }
}
