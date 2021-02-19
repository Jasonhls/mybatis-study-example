package com.cn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcHelloWorld {
    /**
     * 入口函数
     * @param args
     */
    public static void main(String[] args) {
        try {
            Connection con = null; //定义一个MYSQL连接对象
            Class.forName("com.mysql.jdbc.Driver").newInstance();//MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false");//连接本地MYSQL

            //更新一条数据
            String updateSql = "UPDATE user set age = 30 where id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(updateSql);
            preparedStatement.setString(1, "27");
            int i = preparedStatement.executeUpdate();
            System.out.println("update：" + i);

            //查询数据并输出
            String sql = "SELECT * FROM user where id = ?";
            PreparedStatement preparedStatement1 = con.prepareStatement(sql);
            preparedStatement1.setString(1, "27");
            ResultSet rs = preparedStatement1.executeQuery();
            while(rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                System.out.println("\r\n\r\n");
                System.out.println("name：" + name + "，age：" + age + "，sex：" + sex);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
