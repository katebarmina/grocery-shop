package controller.userManagement;

import dao.impl.UserDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/manageShop/deleteUser")
public class DeleteUserController extends HttpServlet {
    private final UserDAOImpl userDaoImpl = new UserDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String userId = request.getParameter("userId");
     userDaoImpl.deleteUser(userId);
     response.sendRedirect(request.getContextPath()+"/showAllUsers");
    }
}
