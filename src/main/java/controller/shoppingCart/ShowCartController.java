package controller.shoppingCart;

import models.Item;
import models.ShoppingCart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/shoppingCart")
public class ShowCartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        List<Item> products = cart.getProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher(request.getContextPath() + "/showCart.jsp").forward(request, response);
    }

}
