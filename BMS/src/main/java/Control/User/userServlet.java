package Control.User;
import Moudle.Bean.User;
import Moudle.Service.Service;
import com.alibaba.fastjson.JSON;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
@WebServlet("/userServlet")
public class userServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("爷爷收到了");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        ArrayList array = new ArrayList();
        ArrayList array1 = Service.getMessageOfBook();
        ArrayList array2 = Service.borrowList(user.getUserID());
        array.add(array1);
        array.add(array2);
        Writer writer = response.getWriter();
        String jsonString = JSON.toJSONString(array);
        writer.write(jsonString);
//        System.out.println("爷爷发完了");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
