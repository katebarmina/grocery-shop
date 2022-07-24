package controller.productManagement;

import dao.impl.ProductsDAOImpl;
import models.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/addProduct")
public class AddProductController extends HttpServlet {
    private final ProductsDAOImpl productsDaoImpl = new ProductsDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     response.sendRedirect(request.getContextPath()+"/addNewProduct.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("productName");
        Double price = Double.valueOf(request.getParameter("price"));
        String brand = request.getParameter("brand");
        long categoryId = Long.parseLong(request.getParameter("categoryId"));
        Product newProduct = new Product(name,price,brand,categoryId);
        productsDaoImpl.addProduct(newProduct);
        response.sendRedirect(request.getContextPath()+"/listOfProducts");
    }
}
