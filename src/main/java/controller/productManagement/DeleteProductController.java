package controller.productManagement;

import dao.impl.ProductsDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/deleteProduct")
public class DeleteProductController extends HttpServlet {
    private ProductsDAOImpl productsDaoImpl = new ProductsDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        productsDaoImpl.deleteProduct(productId);
        response.sendRedirect(request.getContextPath()+"/listOfProducts");
    }

}
