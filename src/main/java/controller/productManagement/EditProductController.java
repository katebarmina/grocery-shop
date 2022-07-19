package controller.productManagement;

import dao.ProductsDAO;
import models.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/editProduct")
public class EditProductController extends HttpServlet {
    private final ProductsDAO productsDao = new ProductsDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        request.setAttribute("productId",productId);
        request.getRequestDispatcher(request.getContextPath()+"/editProduct.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId =  request.getParameter("productId");
        String name = request.getParameter("productName");
        double price = Double.parseDouble(request.getParameter("price"));
        String brand = request.getParameter("brand");
        long categoryId = Long.parseLong(request.getParameter("categoryId"));
        Product product = new Product(name,price,brand,categoryId);
        productsDao.updateProduct(product,productId);
        response.sendRedirect(request.getContextPath()+"/listOfProducts");




    }
}
