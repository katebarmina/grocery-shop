package com.barmina.servlets.product;

import com.barmina.models.Product;
import com.barmina.models.Role;
import com.barmina.models.User;
import com.barmina.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
