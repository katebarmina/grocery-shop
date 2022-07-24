package controller.orderManagement;

import dao.impl.OrderDAOImpl;
import models.Order;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/showUserOrders")
public class ShowUserOrdersController extends HttpServlet {
    private final OrderDAOImpl orderDaoImpl = new OrderDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session =request.getSession();
    User user = (User) session.getAttribute("user");
    List<Order> userOrders = orderDaoImpl.getUsersOrders(String.valueOf(user.getId()));
    request.setAttribute("userOrders",userOrders);
    request.getRequestDispatcher("/showUserOrders.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
