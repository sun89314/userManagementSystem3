package web;

import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        String code = request.getParameter("verifycode");

        String realCode = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        //System.out.println(realCode+ ":" + code);
        if(!realCode.equalsIgnoreCase(code)){

            request.setAttribute("login_msg","the verify code is not right!");

            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();

        User user = new User();
        //System.out.println(map.get("username"));
        //System.out.println(map.get("password"));

        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }



        UserService service = new UserServiceImpl();
        System.out.println(user.toString());
        User loginUser = service.login(user);
        if(loginUser!=null) System.out.println(loginUser.toString());

        if(loginUser != null){
            request.getSession().setAttribute("user",loginUser);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
        else{
            request.setAttribute("login_msg","the username or password  is not right!");

            request.getRequestDispatcher("/login.jsp").forward(request,response);


        }


    }
}
