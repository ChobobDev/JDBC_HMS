package com.assignment.application;

import javax.swing.*;
import java.sql.*;

public class id_checker {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public static int id_checked_status = 0;


    public  id_checker(String un) {
        Connection conn = null;
        JOptionPane mesgbox=new JOptionPane();
        try {

            conn = DriverManager.getConnection(csse);
            String id = "select * from user_account where username like'"+un+"';";
            Statement statement = conn.createStatement();
            ResultSet loginreuslt = statement.executeQuery(id);
            if(loginreuslt.next()){
                mesgbox.showMessageDialog(null,"ID exist","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                id_checked_status = 0;
            }
            else{
                mesgbox.showMessageDialog(null,"You can use this ID","INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                id_checked_status = 1;
            }


        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
