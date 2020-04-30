package com.assignment.application;

import javax.swing.*;
import java.sql.*;

public class book_new {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public book_new(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(csse);
            String id = "";
            Statement statement = conn.createStatement();
            ResultSet bookres = statement.executeQuery(id);
            if(bookres.next()){

            }
            else{

            }


        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }





}
