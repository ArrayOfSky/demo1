package Control.Manager;

import Moudle.Bean.Book;
import Moudle.Bean.backMessage;
import Moudle.Service.Service;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

@WebServlet("/delServlet")
public class delServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        BufferedReader bf = request.getReader();
        String params = bf.readLine();
        Book book = JSON.parseObject(params, Book.class);
        boolean flag = Service.deleteBook(book.getName());
        String i = flag?"1":"0" ;
        ArrayList array = new ArrayList();
        ArrayList array1 = Service.checkUserList();
        ArrayList array2 = Service.checkBookList();
        array.add(i);
        array.add(array1);
        array.add(array2);
        String JSONString = JSON.toJSONString(array);
        Writer writer = response.getWriter();
        writer.write(JSONString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
