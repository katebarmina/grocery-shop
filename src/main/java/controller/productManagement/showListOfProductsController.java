package controller.productManagement;

import models.Product;
import dao.ProductsDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/listOfProducts")
public class showListOfProductsController extends HttpServlet {
    private final ProductsDAO dao = new ProductsDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        List<Product> listOfProducts = dao.getAllProducts();
        request.setAttribute("products",listOfProducts);
        request.getRequestDispatcher("/showProducts.jsp").forward(request,response);

    }

}
