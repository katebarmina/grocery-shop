package servlets.cart;

import models.Product;
import models.ShoppingCart;
import service.ProductService;
import service.ShoppingCartService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/shoppingCart/remove")
public class RemoveFromCartServlet extends HttpServlet {
  private final ProductService productService = new ProductService();
  private final ShoppingCartService cartService = new ShoppingCartService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
    String productId = request.getParameter("productId");
    Product product = productService.getById(productId);
    cart = cartService.delete(cart, product);
    session.setAttribute("cart", cart);
    response.sendRedirect(request.getContextPath() + "/shoppingCart");
  }
}
