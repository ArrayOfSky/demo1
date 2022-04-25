package Control.Login;

import Moudle.Bean.LoginMessage;
import Moudle.Bean.User;
import Moudle.Service.Service;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("我收到了");
        req.setCharacterEncoding("utf-8");
        BufferedReader br = req.getReader();
        String params = br.readLine();
        LoginMessage login = JSON.parseObject(params,LoginMessage.class);
        String userName = login.getUsername();
        String password = login.getPassword();
        User user = new User(userName,password);
        String auto = login.getAuto();
        int right = Service.loginIn(userName,password);
        Writer writer = resp.getWriter();
//        System.out.println("到这了");
        if(right != 0){
            if("1".equals(auto)){
                Cookie cUserName = new Cookie("username",userName);
                Cookie cPassword = new Cookie("password",password);
                cUserName.setMaxAge(60*60*24*31);
                cPassword.setMaxAge(60*60*24*31);
                resp.addCookie(cUserName);
                resp.addCookie(cPassword);
            }
            req.getSession().setAttribute("user",user);
            if(right==1){
                writer.write("1");
//                System.out.println("返回了1");
            }else{
//                System.out.println("返回了2");
                writer.write("2");
            }
        }else{
//            System.out.println("返回了0");
            writer.write("0");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
