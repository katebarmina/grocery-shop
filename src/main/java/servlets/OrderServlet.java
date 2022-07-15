package servlets;

import dao.UserDAO;
import models.Order;
import models.ShoppingCart;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "orderServlet", value = "/orderServlet")
public class OrderServlet extends HttpServlet {
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
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        String address = request.getParameter("address");
        String cardNum = request.getParameter("cardNumber");
        String cardHolderName = request.getParameter("cardHolderName");
        String mmYY = request.getParameter("mmYY");
        int cvv = Integer.parseInt(request.getParameter("cvv"));
        Order order = new Order(user,cart,address,cardNum,cardHolderName,mmYY,cvv);

    }
}
