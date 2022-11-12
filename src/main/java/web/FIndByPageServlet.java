package web;

import domain.PageBean;
import domain.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "FIndByPageServlet", value = "/FIndByPageServlet")
public class FIndByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curreentPage = request.getParameter("curreentPage");

        String rows = request.getParameter("rows");
        if(curreentPage == null || "".equals(curreentPage)){
            curreentPage = "1";
        }

        if(rows == null || "".equals(rows)){
            rows = "10";
        }

        Map<String, String[]> condition = request.getParameterMap();
        for(String key:condition.keySet()){
            System.out.println(key + ":" + condition.get(key)[0]);
        }

        UserService service = new UserServiceImpl();

        PageBean<User> pb = service.findUserByPage(curreentPage,rows,condition);
        System.out.println(pb);
        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);

        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }
}
