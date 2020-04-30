package com.assignment.application;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class show_booked {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    Object ob[][]=new Object[0][5];
    DefaultTableModel model;


    public show_booked(String un){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(csse);
            String booked_result = "select * from booked_room where username like'"+un+"';";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(booked_result);
            while (result.next()){
                String room_num = result.getString("room_num");
                String room_type = result.getString("room_type");
                String check_in = result.getString("check_in");
                String check_out = result.getString("check_out");
                String food = result.getString("food_status");
                Object data[]= {room_num,room_type,check_in,check_out,food};

            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
