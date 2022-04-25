package Moudle.Service;

import Moudle.Bean.Book;
import Moudle.Bean.LendInfo;
import Moudle.Bean.User;
import Moudle.Dao.BookDao;
import Moudle.Dao.UserDao;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Service {
    //0表示登录失败，1表示成员登录，2表示管理员登录
    public static int loginIn(String userID, String userPWD) {
        User user = UserDao.getPriAllInfo(userID);
        if (user!=null&&user.getUserPwd().equals(userPWD)) {
            if (user.getRight() == 1) {
                return 2;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    //true 表示注册成功，false表示注册失败
    public static boolean register(String userID, String userPWD) {
        ArrayList<User> list = UserDao.getPubAllInfo();
        for (User user : list) {
            if (userID.equals(user.getUserID())) {
                return false;
            }
        }
        UserDao.addUser(new User(0, userID, userPWD));
        return true;
    }

    //获取图书商城信息
    // 只提供书名，书本剩余数量
    public static ArrayList<Book> getMessageOfBook() {
        ArrayList<Book> array = BookDao.selectAllBook();
        return array;
    }
    //借书，如果这本书借过了，那就false，没借过，那就true；
    public static boolean borrowBook(String userID, String bookName) {
        Book book = BookDao.selectBook(bookName);
        if(book==null||book.getNumber()==0){
            return false;
        }
        ArrayList<LendInfo> array = BookDao.checkLendList(userID);
        for (LendInfo lend : array) {
            if (lend.getBook().equals(bookName)) {
                return false;
            }
        }
        BookDao.borrowBook(userID,bookName);
        return true;
    }

    //借阅列表
    //提供该用户借过的书的书名
    public static ArrayList<LendInfo> borrowList(String userName) {
        ArrayList<LendInfo> array = BookDao.checkLendList(userName);
        return array;
    }

    //还书
    public static boolean returnBook(String userName, String bookName) {
        Book book = BookDao.selectBook(bookName);
        if(book==null){
            return false;
        }
        ArrayList<LendInfo> array = borrowList(userName);
        for(LendInfo lend : array){
            if(lend.getBook().equals(bookName)){
                BookDao.returnBook(userName,bookName);
                return true;
            }
        }
        return false;
    }

    //看用户列表，看所有用户借的书
    //返回了用户名和哪个用户名借了哪本书
    public static ArrayList<LendInfo> checkUserList() {
        ArrayList<LendInfo> array = BookDao.checkAllList();
        return array;
    }

    //看书列表
    //返回有书名和书的数目，以及书的借出数目
    public static ArrayList<Book> checkBookList() {
        ArrayList<Book> array = BookDao.selectAllBook();
        return array;
    }

    //添加书籍
    //如果有这本书了就添加失败
    public static boolean addBook(String bookName, int number) {
        ArrayList<Book> array = BookDao.selectAllBook();
        for(Book book:array){
            if(book.getName().equals(bookName)){
                alterBook(bookName,number);
                return false;
            }
        }
        BookDao.addBook(new Book(bookName,number));
        return true;
    }

    //删除书籍
    //如果有人没有还书，则不能删除
    public static boolean deleteBook(String bookName) {
        Book book = BookDao.selectBook(bookName);
        if(book.getBorrowNumber()!=0){
            return false;
        }
        BookDao.delBook(bookName);
        return true;
    }

    //修改书籍信息
    public static void alterBook(String bookName, int number) {
        BookDao.alterBookNumber(bookName,number);
    }
}
