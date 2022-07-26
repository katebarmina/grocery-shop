package servlets.product;

import models.Product;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
  private final ProductService productService = new ProductService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.sendRedirect(request.getContextPath() + "/addNewProduct.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String name = request.getParameter("productName");
    double price = Double.parseDouble(request.getParameter("price"));
    String brand = request.getParameter("brand");
    long categoryId = Long.parseLong(request.getParameter("categoryId"));
    Product newProduct = new Product(name, price, brand, categoryId);
    productService.add(newProduct);
    response.sendRedirect(request.getContextPath() + "/listOfProducts");
  }
}
