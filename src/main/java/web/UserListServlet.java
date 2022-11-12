package web;

import dao.UserDaoImpl;
import domain.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserListServlet", value = "/UserListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 use userSerive to get the List information

        UserService service = new UserServiceImpl();
        List<User> users = service.findall();

        //put the information into the request
        request.setAttribute("users",users);
        //transfer toward the display page the list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }
}
