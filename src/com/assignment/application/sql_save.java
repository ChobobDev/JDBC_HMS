package com.assignment.application;

import java.sql.*;

public class sql_save {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";

    public sql_save(String un, String pw, String pst, String em, String pn){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(csse);
            String save = "insert into user_account (username,password,is_staff,passport_num,email,phone_num)"+"values('"+un+"','"+pw+"','"+0+"','"+pst+"','"+em+"','"+pn+"');";
            Statement statement = conn.createStatement();
            statement.executeUpdate(save);


        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



}
