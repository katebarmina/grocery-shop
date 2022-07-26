package servlets.product;

import models.Product;
import models.Role;
import models.User;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/listOfProducts")
public class ShowListOfProductsServlet extends HttpServlet {
  private final ProductService productService = new ProductService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    List<Product> listOfProducts = productService.getAll();
    request.setAttribute("products", listOfProducts);
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
    if (user != null && user.getRole().equals(Role.ADMIN)) {
      request
          .getRequestDispatcher(request.getContextPath() + "/manageProducts.jsp")
          .forward(request, response);
    }
    request.getRequestDispatcher("/showProducts.jsp").forward(request, response);
  }
}
