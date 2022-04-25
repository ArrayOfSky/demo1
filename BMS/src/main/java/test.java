import Moudle.Bean.Book;
import Moudle.Bean.LendInfo;
import Moudle.Service.Service;
import java.util.ArrayList;
import java.util.Scanner;
/*
对于所有功能提供的控制台测试
 */
public class test {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        while (true) {
            System.out.println("1:登录");
            System.out.println("2:注册");
            System.out.println("3:退出");
            int a = scan.nextInt();
            switch (a) {
                case 1:
                    loginIn();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    return;
            }
        }
    }

    public static void loginIn() {
        System.out.println("请输入账号");
        String userID = scan.next();
        System.out.println("请输入密码");
        String userPWD = scan.next();
        int i = Service.loginIn(userID, userPWD);
        switch (i) {
            case 0:
                System.out.println("账号密码错误");
                break;
            case 1:
                System.out.println("欢迎用户登录");
                userLogin(userID);
                break;
            case 2:
                System.out.println("欢迎管理员登录");
                managerLogin();
                break;
        }
    }

    public static void userLogin(String userID) {
        while (true) {
            System.out.println("1:看书库");
            System.out.println("2:看已借阅列表");
            System.out.println("3:退出");
            int a = scan.nextInt();
            switch (a) {
                case 1:
                    lookBook(userID);
                    break;
                case 2:
                    lookLend(userID);
                    break;
                case 3:
                    return;
            }
        }
    }

    public static void lookBook(String userID) {

        while (true) {
            ArrayList<Book> array = Service.getMessageOfBook();
            for (int i = 0; i < array.size(); i++) {
                System.out.println(i + array.get(i).toString());
            }
            System.out.println("1:借书");
            System.out.println("2:退出");
            int a = scan.nextInt();
            switch (a) {
                case 1:
                    System.out.println("请输入书籍编号");
                    int b = scan.nextInt();
                    Service.borrowBook(userID, array.get(b).getName());
                    break;
                case 2:
                    return;
            }
        }
    }

    public static void lookLend(String userID) {

        while (true) {
            ArrayList<LendInfo> array = Service.borrowList(userID);
            for (int i = 0; i < array.size(); i++) {
                System.out.println(i + array.get(i).toString());
            }
            System.out.println("1:还书");
            System.out.println("2:退出");
            int a = scan.nextInt();
            switch (a) {
                case 1:
                    System.out.println("请输入图书编号");
                    int b = scan.nextInt();
                    Service.returnBook(userID, array.get(b).getBook());
                    break;
                case 2:
                    return;
            }
        }

    }

    public static void managerLogin() {

        while (true) {
            System.out.println("1:用户列表");
            System.out.println("2:图书列表");
            System.out.println("3:退出");
            int a = scan.nextInt();
            switch (a) {
                case 1:
                    UserList();
                    break;
                case 2:
                    BookList();
                    break;
                case 3:
                    return;
            }
        }
    }

    public static void UserList() {

        while (true) {
            ArrayList<LendInfo> array = Service.checkUserList();
            for (int i = 0; i < array.size(); i++) {
                System.out.println(i + array.get(i).toString());
            }
            System.out.println("1:强制用户还书");
            System.out.println("2:退出");
            int a = scan.nextInt();
            switch (a) {
                case 1:
                    System.out.println("请输入编号");
                    int b = scan.nextInt();
                    Service.returnBook(array.get(b).getUserID(), array.get(b).getBook());
                    break;
                case 2:
                    return;
            }
        }
    }

    public static void BookList() {

        while (true) {
            ArrayList<Book> array = Service.checkBookList();
            for (int i = 0; i < array.size(); i++) {
                System.out.println(i + array.get(i).toString());
            }
            System.out.println("1:添加图书");
            System.out.println("2:删除图书");
            System.out.println("3:修改图书信息");
            System.out.println("4:退出");
            int a = scan.nextInt();
            String bookName;
            int number;
            switch (a) {
                case 1:
                    System.out.println("请输入书名");
                    bookName = scan.next();
                    System.out.println("请输入书本数目");
                    number = scan.nextInt();
                    Service.addBook(bookName, number);
                    break;
                case 2:
                    System.out.println("请输入书名");
                    bookName = scan.next();
                    Service.deleteBook(bookName);
                    break;
                case 3:
                    System.out.println("请输入书名");
                    bookName = scan.next();
                    System.out.println("请输入剩余数目");
                    number = scan.nextInt();
                    Service.alterBook(bookName, number);
                    break;
                case 4:
                    return;
            }
        }

    }

    public static void register() {
        System.out.println("请输入账号");
        String userID = scan.next();
        System.out.println("请输入密码");
        String userPWD = scan.next();
        Service.register(userID, userPWD);
    }

}
