package Moudle.Bean;

public class LendInfo {
    private int sign;//别看这玩意
    private String book;
    private String userID;

    @Override
    public String toString() {
        return "LendInfo{" +
                "sign=" + sign +
                ", book='" + book + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public LendInfo() {
    }

    public LendInfo(int sign, String book) {
        this.sign = sign;
        this.book = book;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }
}
