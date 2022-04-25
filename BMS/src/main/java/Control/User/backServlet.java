package Control.User;

import Moudle.Bean.Book;
import Moudle.Bean.User;
import Moudle.Service.Service;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

@WebServlet("/backServlet")
public class backServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("我收到了");
        request.setCharacterEncoding("utf-8");
        BufferedReader bf = request.getReader();
        String params = bf.readLine();
        Book book = JSON.parseObject(params, Book.class);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean flag = Service.returnBook(user.getUserID(), book.getName());
        String i = flag?"1":"0" ;
        ArrayList array = new ArrayList();
        ArrayList array1 = Service.borrowList(user.getUserID());
        array.add(i);
        array.add(array1);
        String JSONString = JSON.toJSONString(array);
        Writer writer = response.getWriter();
        writer.write(JSONString);
//        System.out.println("我发回去了");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
