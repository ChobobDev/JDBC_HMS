package com.assignment.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class book_cancel {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public book_cancel(String bi){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(csse);
            String delete = "delete from booked_room where book_id = "+bi+";";
            Statement st = conn.createStatement();
            st.execute(delete);
            System.out.println(bi+"executed");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

}
