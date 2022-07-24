package controller.orderManagement;

import dao.impl.OrderDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/updateStatus")
public class UpdateStatusController extends HttpServlet {
    private final OrderDAOImpl orderDaoImpl = new OrderDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String status = request.getParameter("orderStatus");
        orderDaoImpl.updateStatus(Long.valueOf(orderId),status);
        response.sendRedirect(request.getContextPath()+"/listOfOrders");
    }
}
