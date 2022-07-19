package controller.shoppingCart;

import dao.ProductsDAO;
import dao.ShoppingCartDao;
import models.Product;
import models.ShoppingCart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/shoppingCart/add")
public class AddToCartController extends HttpServlet {
    private final String USER_ID = "userId";
    private ProductsDAO productsDao = new ProductsDAO();
    private ShoppingCartDao cartDao = new ShoppingCartDao();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
       ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
       if (cart == null) {
           cart = new ShoppingCart();
           session.setAttribute("cart", cart);
       }
           String productID = req.getParameter("productId");
           Product product = productsDao.getProductById(productID);
           cart = cartDao.add(cart,product);
           session.setAttribute("cart",cart);
           resp.sendRedirect(req.getContextPath()+"/listOfProducts");



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
