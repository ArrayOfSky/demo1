package Control.Register;

import Moudle.Bean.User;
import Moudle.Service.Service;
import com.alibaba.fastjson.JSON;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();
        User user = JSON.parseObject(params,User.class);
        Writer writer = response.getWriter();
        if(Service.register(user.getUserID(),user.getUserPwd())){
//            System.out.println("1");
            writer.write("1");
        }else{
//            System.out.println("0");
            writer.write("0");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
