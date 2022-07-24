package controller.shoppingCart;

import dao.ProductDAO;
import dao.impl.ProductsDAOImpl;
import dao.impl.ShoppingCartDAOImpl;
import models.Product;
import models.ShoppingCart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/shoppingCart/remove")
public class RemoveFromCartController extends HttpServlet {
    private final ProductDAO productsDAO = new ProductsDAOImpl();
    private final ShoppingCartDAOImpl cartDao = new ShoppingCartDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        String productId = request.getParameter("productId");
        Product product = productsDAO.getProductById(productId);
        cart = cartDao.remove(cart, product);
        session.setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/shoppingCart");

    }

}
