package controller;

import dao.OrderDao;
import dao.UserDAO;
import models.Order;
import models.ShoppingCart;
import models.Status;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    private final OrderDao orderDao = new OrderDao();
    private UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     request.getRequestDispatcher("/order.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        if (user == null){
            request.getRequestDispatcher("/login").forward(request,response);
        }
        Order order = new Order(user.getId(), Status.PROCESSING);
        orderDao.createOrder(order);
        cart = null;
        request.getRequestDispatcher(request.getContextPath()+"orderCompleted.jsp").forward(request,response);

    }
}
