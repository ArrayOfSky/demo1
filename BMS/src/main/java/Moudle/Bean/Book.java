package Moudle.Bean;

public class Book {
    private String name;//书名
    private int number;//剩余数量
    private int borrowNumber = 0;//借出数量

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", borrowNumber=" + borrowNumber +
                '}';
    }

    public Book() {
    }

    public int getBorrowNumber() {
        return borrowNumber;
    }

    public void setBorrowNumber(int borrowNumber) {
        this.borrowNumber = borrowNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Book(String name, int number) {
        this.name = name;
        this.number = number;
    }
}
