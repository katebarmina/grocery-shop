package servlets.product;

import models.Product;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/editProduct")
public class EditProductServlet extends HttpServlet {
  private final ProductService productService = new ProductService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String productId = request.getParameter("productId");
    request.setAttribute("productId", productId);
    request
        .getRequestDispatcher(request.getContextPath() + "/editProduct.jsp")
        .forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String productId = request.getParameter("productId");
    String name = request.getParameter("productName");
    double price = Double.parseDouble(request.getParameter("price"));
    String brand = request.getParameter("brand");
    long categoryId = Long.parseLong(request.getParameter("categoryId"));
    Product product = new Product(name, price, brand, categoryId);
    productService.update(product, productId);
    response.sendRedirect(request.getContextPath() + "/listOfProducts");
  }
}
