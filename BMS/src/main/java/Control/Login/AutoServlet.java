package Control.Login;

import Moudle.Bean.User;
import Moudle.Service.Service;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/autoServlet")
public class AutoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("爷收到了");
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        Writer writer = response.getWriter();
//        System.out.println("准备进去了");
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                username = cookie.getValue();
            } else if (cookie.getName().equals("password")) {
                password = cookie.getValue();
            }
//            System.out.println("循环中");
        }
//        System.out.println("出来了");
        if(username!=null&&password!=null){
            User user = new User(username,password);
//            System.out.println(username);
//            System.out.println(password);
            int right = Service.loginIn(username,password);
            request.getSession().setAttribute("user",user);
            if(right==0){
//                System.out.println("0!!!!!");
                writer.write("0");
                return;
            }
            if(right==1){
//                System.out.println("1");
            writer.write("1");
            }else{
//                System.out.println("2");
             writer.write("2");
            }
        }else{
//            System.out.println("0");
            writer.write("0");
        }
//        System.out.println("爷发了");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
