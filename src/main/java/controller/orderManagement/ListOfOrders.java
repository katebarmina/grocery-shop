package controller.orderManagement;

import dao.impl.OrderDAOImpl;
import models.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/listOfOrders")
public class ListOfOrders extends HttpServlet {
    private final OrderDAOImpl orderDaoImpl = new OrderDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderDaoImpl.getAllOrders();
        request.setAttribute("orders",orders);
        request.getRequestDispatcher("/allOrders.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
