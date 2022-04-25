package Moudle.Bean;

import java.util.ArrayList;

public class User {
    //
    private int right = 0;//权限
    private String userID;//id
    private String userPwd;//密码
    private int sign;

    @Override
    public String toString() {
        return "User{" +
                "right=" + right +
                ", userID='" + userID + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", sign=" + sign +
                '}';
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public User(){

    }
    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public User(String userID, String userPwd) {
        this.userID = userID;
        this.userPwd = userPwd;
    }

    public User(int right, String userID, String userPwd) {
        this.right = right;
        this.userID = userID;
        this.userPwd = userPwd;
    }
}
