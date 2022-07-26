package servlets.product;

import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
  private final ProductService productService = new ProductService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String productId = request.getParameter("productId");
    productService.delete(productId);
    response.sendRedirect(request.getContextPath() + "/listOfProducts");
  }
}
