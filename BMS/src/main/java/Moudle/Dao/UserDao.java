package Moudle.Dao;

import Moudle.Bean.User;

import java.util.ArrayList;

public class UserDao {
    //注册用户
    public static void addUser(User user){
        String sql = "insert into user(`right`,userID,userPwd) values(?,?,?)";
        dao.alter(sql,user.getRight(),user.getUserID(),user.getUserPwd());
    }
    //删除用户
    public static void deleteUser(String userId){
        String sql = "delete from user where userID = ?";
        dao.alter(sql,userId);
    }
    //获取单个用户全部信息
    public static User getPriAllInfo(String userId){
        String sql = "select * from user where userID = ?";
        ArrayList<User> array = dao.select(User.class,sql,userId);
        if(array.size()==0){
            return null;
        }
        return array.get(0);
    }
    //获取所有用户的信息
    public static ArrayList<User> getPubAllInfo() {
        String sql  = "select * from user";
        ArrayList<User> array = dao.select(User.class,sql);
        return array;
    }
//    获取单个用户的sign
    static int getPriSign(String userID){
        String sql = "select sign from user where userID = ?";
       ArrayList<User> array = dao.select(User.class,sql,userID);
        return array.get(0).getSign();
    }
}





//下面的是没必要去写或者不应该在这里写的东西


//    //借书
//    public static void borrowBook(String userID,String bookName){
//        String sql = "update book set number = number-1,borrowNumber = borrowNumber+1 where name = ?";
//        dao.alter(sql,bookName);
//        //还未写完
////        sql = "insert into lendinfo";
//    }
//    //还书
//    public static void returnBook(){
//    }
//    //获取单个用户的权限
//    public static int getPriRight(String userID){
//        String sql = "select `right` from user where userID = ?";
//       ArrayList<User> array = dao.select(User.class,sql,userID);
//        return array.get(0).getRight();
//    }
//    //获取单个用户的已借阅书籍
//    public static ArrayList<String> getPriBorrowBook(String userId){
//        return null;
//    }
//    //获取所有用户的id
//    public static ArrayList<String> getPubUserId(){
//        String sql = "select id from user";
//        ArrayList<User> list = dao.select(User.class,sql);
//        ArrayList<String> array = new ArrayList<>();
//        for(User user : list){
//            array.add(user.getUserID());
//        }
//        return array;
//    }
//    //获取所有用户已借阅的书籍
//    public static ArrayList<ArrayList<String>> getPubBorrowBook(){
//        return null;
//    }
