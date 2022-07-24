package controller.orderManagement;

import dao.impl.OrderDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/deleteOrder")
public class DeleteOrderController extends HttpServlet {
    private final OrderDAOImpl orderDaoImpl = new OrderDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
    orderDaoImpl.deleteOrder(orderId);
    response.sendRedirect(request.getContextPath()+"/listOfOrders");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
