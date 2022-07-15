package servlets.userManagement;

import dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/manageShop/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private final UserDAO userDao = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String userId = request.getParameter("userId");
     userDao.deleteUser(userId);
     response.sendRedirect(request.getContextPath()+"/showAllUsers");
    }
}
