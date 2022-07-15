package servlets;

import dao.UserDAO;
import models.Role;
import models.User;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "loginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {

    private final UserDAO dao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        User newUser = new User();
        newUser.setEmail(username);
        newUser.setPassword(password);
        if (dao.IsRegistered(newUser)){
            if (dao.isAdmin(newUser)){
                newUser.setRole(Role.ADMIN);
            }else {
                newUser.setRole(Role.CLIENT);
            }
            HttpSession session = request.getSession();
            session.setAttribute("user",newUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request,response);
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/userDoesntExist.jsp");
            dispatcher.forward(request,response);
        }


    }
}
