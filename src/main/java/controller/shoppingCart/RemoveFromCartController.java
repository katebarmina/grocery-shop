package controller.shoppingCart;

import dao.ProductsDAO;
import dao.ShoppingCartDao;
import models.Product;
import models.ShoppingCart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/shoppingCart/remove")
public class RemoveFromCartController extends HttpServlet {
    private final ProductsDAO productsdao = new ProductsDAO();
    private final ShoppingCartDao cartDao = new ShoppingCartDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
    String productId = request.getParameter("productId");
    Product product = productsdao.getProductById(productId);
    cart = cartDao.remove(cart,product);
    session.setAttribute("cart",cart);
    response.sendRedirect(request.getContextPath()+"/shoppingCart");

    }

}
