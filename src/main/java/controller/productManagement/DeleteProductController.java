package controller.productManagement;

import dao.ProductsDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/deleteProduct")
public class DeleteProductController extends HttpServlet {
    private ProductsDAO productsDao = new ProductsDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        productsDao.deleteProduct(productId);
        response.sendRedirect(request.getContextPath()+"/listOfProducts");
    }

}
