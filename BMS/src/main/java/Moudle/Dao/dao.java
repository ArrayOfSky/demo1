package Moudle.Dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public class dao {

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void alter(String sql, Object... args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //通用的增、删、改操作（体现一：增、删、改 ； 体现二：针对于不同的表）
        Connection conn = null;
        PreparedStatement ps = null;
        //192.168.43.97
//        String url = "jdbc:mysql://localhost:3306/bms";
        //192.168.43.97
//        String url = "jdbc:mysql://47.103.142.62:3306/bms?useUnicode=true&useSSL=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
        String url = "jdbc:mysql://47.103.142.62:3306/BMS?useUnicode=true&useSSL=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=GMT%2B8";
//        String url = "jdbc:mysql://192.168.43.97:3306/bms";
        String user = "root";
        String password = "gaoyifeng";
        try {
            //1.获取数据库的连接
            conn = DriverManager.getConnection(url, user, password);
            //2.获取PreparedStatement的实例 (或：预编译sql语句)
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //4.执行sql语句
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //查找对象，返回对象arrayllist
    static <T> ArrayList<T> select(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        String url = "jdbc:mysql://47.103.142.62:3306/BMS?useUnicode=true&useSSL=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=GMT%2B8";
//        String url = "jdbc:mysql://47.103.142.62:3306/bms?useUnicode=true&useSSL=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";

        //jdbc:mysql://127.0.0.1:3306/bms?useUnicode=true&useSSL=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=GMT%2B8
//        String url = "jdbc:mysql://localhost:3306/bms";
        String user = "root";
        String password = "gaoyifeng";
        ResultSet rs = null;
        ArrayList<T> array = new ArrayList<>();
        try {
            // 1.获取数据库连接
            conn = DriverManager.getConnection(url, user, password);
            // 2.预编译sql语句，得到PreparedStatement对象
            ps = conn.prepareStatement(sql);
            // 3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 4.执行executeQuery(),得到结果集：ResultSet
            rs = ps.executeQuery();
            // 5.得到结果集的元数据：ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 6.1通过ResultSetMetaData得到columnCount,columnLabel；通过ResultSet得到列值
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {// 遍历每一个列
                    // 获取列值
                    Object columnVal = rs.getObject(i + 1);
                    // 获取列的别名:列的别名，使用类的属性名充当
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    // 6.2使用反射，给对象的相应属性赋值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnVal);
                }
//                System.out.println("??????????/");
                array.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7.关闭资源
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return array;
    }
}
