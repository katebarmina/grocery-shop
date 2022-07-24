package controller.productManagement;

import models.Product;
import dao.impl.ProductsDAOImpl;
import models.Role;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/listOfProducts")
public class ShowListOfProductsController extends HttpServlet {
    private final ProductsDAOImpl dao = new ProductsDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        List<Product> listOfProducts = dao.getAllProducts();
        request.setAttribute("products",listOfProducts);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user!= null && user.getRole().equals(Role.ADMIN)){
            request.getRequestDispatcher(request.getContextPath()+"/manageProducts.jsp").forward(request,response);
        }
        request.getRequestDispatcher("/showProducts.jsp").forward(request,response);

    }

}
