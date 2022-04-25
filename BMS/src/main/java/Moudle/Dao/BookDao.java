package Moudle.Dao;

import Moudle.Bean.Book;
import Moudle.Bean.LendInfo;

import java.util.ArrayList;

public class BookDao {
    //添加图书
    public static void addBook(Book book){
        String sql = "insert into book(name,number) values(?,?)";
        dao.alter(sql,book.getName(),book.getNumber());
    }
    //删除图书
    public static void delBook(String name){
        String sql = "delete from book where name = ?";
        dao.alter(sql,name);
    }
    //查找某本书的信息
    public static Book selectBook(String bookName){
        String sql = "select * from book where name = ?";
        ArrayList<Book> book = dao.select(Book.class,sql,bookName);
        return book.get(0);
    }
    //查找全部书的所有信息
    public static ArrayList<Book> selectAllBook(){
        String sql = "select * from book";
        ArrayList<Book> book = dao.select(Book.class,sql);
        return book;
    }
    //借书
    public static void borrowBook(String userName,String bookName){
        String sql = "update book set number = number-1,borrowNumber = borrowNumber+1 where name = ?";
        dao.alter(sql,bookName);
        sql = "insert into lendinfo(sign,book) values(?,?)";
        dao.alter(sql,UserDao.getPriSign(userName),bookName);
    }
    //还书
    public static void returnBook(String userName,String bookName){
        String sql = "update book set number = number+1,borrowNumber = borrowNumber-1 where name = ?";
        dao.alter(sql,bookName);
        sql = "delete from lendinfo where sign = ? and book = ?";
        dao.alter(sql,UserDao.getPriSign(userName),bookName);
    }
    //看某个用户的借书列表
    public static ArrayList<LendInfo> checkLendList(String userName) {
        String sql = "select book from lendinfo where sign = ?";
        ArrayList<LendInfo> lendInfos = dao.select(LendInfo.class,sql,UserDao.getPriSign(userName));
        for(int i = 0;i<lendInfos.size();i++){
            lendInfos.get(i).setUserID(userName);
        }
        return lendInfos;
    }
    //看所有用户的借书列表
    public static ArrayList<LendInfo> checkAllList(){
        String sql = "select book,userID from user u join lendinfo l on u.sign = l.sign";
        ArrayList<LendInfo> arrayList = dao.select(LendInfo.class,sql);
        return arrayList;
    }
    //修改书籍数量
    public static void alterBookNumber(String bookName,int number) {
        String sql = "update book set number = ? where name = ?";
        dao.alter(sql,number,bookName);
    }
}






//下面的是没必要去写或者不应该在这里写的东西

//    //某本书被借出的数量
//    public static int priBorrowBook(String bookName){
//        String sql = "select borrowNumber from book where name = ?";
//        ArrayList<Book> array = dao.select(Book.class,sql,bookName);
//        return array.get(0).getBorrowNumber();
//    }
//所有被借出的书的数量
//    public static HashMap<String,Integer> pucBoorrowBook(){
//        return null;
//    }