package com.barmina.servlets.product;

import com.barmina.models.Product;
import com.barmina.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
