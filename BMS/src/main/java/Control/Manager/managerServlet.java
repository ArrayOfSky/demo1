package Control.Manager;

import Moudle.Bean.User;
import Moudle.Service.Service;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

@WebServlet("/managerServlet")
public class managerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList array = new ArrayList();
        ArrayList array1 = Service.checkUserList();
        ArrayList array2 = Service.checkBookList();
        array.add(array1);
        array.add(array2);
        Writer writer = response.getWriter();
        String jsonString = JSON.toJSONString(array);
        writer.write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
