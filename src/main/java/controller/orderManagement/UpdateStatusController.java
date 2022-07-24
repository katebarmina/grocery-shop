package controller.orderManagement;

import dao.OrderDAO;
import dao.impl.OrderDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/updateStatus")
public class UpdateStatusController extends HttpServlet {
    private final OrderDAO orderDao = new OrderDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String status = request.getParameter("orderStatus");
        orderDao.updateStatus(Long.valueOf(orderId),status);
        response.sendRedirect(request.getContextPath()+"/listOfOrders");
    }
}
