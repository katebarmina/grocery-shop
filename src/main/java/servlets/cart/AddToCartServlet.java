package servlets.cart;

import models.Product;
import models.ShoppingCart;
import service.ProductService;
import service.ShoppingCartService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/shoppingCart/add")
public class AddToCartServlet extends HttpServlet {
  private final ProductService productService = new ProductService();
  private final ShoppingCartService cartService = new ShoppingCartService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
    if (cart == null) {
      cart = new ShoppingCart();
      session.setAttribute("cart", cart);
    }
    String productID = req.getParameter("productId");
    Product product = productService.getById(productID);
    cart = cartService.add(cart, product);
    session.setAttribute("cart", cart);
    resp.sendRedirect(req.getContextPath() + "/listOfProducts");
  }
}
