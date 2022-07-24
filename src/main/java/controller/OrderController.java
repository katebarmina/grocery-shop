package controller;

import dao.impl.OrderDAOImpl;
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
    private final OrderDAOImpl orderDaoImpl = new OrderDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            request.getRequestDispatcher(request.getContextPath()+"/login").forward(request,response);
        }
     request.getRequestDispatcher("/orderInfoForm.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        Order order = new Order(user.getId(), Status.PROCESSING);
        orderDaoImpl.createOrder(order);
        cart = null;
        response.sendRedirect(request.getContextPath()+"/orderCompleted.jsp");

    }
}
