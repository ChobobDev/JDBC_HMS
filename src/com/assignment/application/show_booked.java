package com.assignment.application;

import javax.swing.*;
import java.sql.*;

public class show_booked {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public static data[][];

    public show_booked(String un){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(csse);
            String booked_result = "select * from booked_room where username like'"+un+"';";
            Statement statement = conn.createStatement();
            ResultSet reuslt = statement.executeQuery(booked_result);
            while (reuslt.next()){

            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
