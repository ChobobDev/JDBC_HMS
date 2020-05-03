package com.assignment.application;

import javax.swing.*;
import java.sql.*;



public class login_checker {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public login_checker(String id,String pw){
        Connection conn = null;
        JOptionPane mesgbox=new JOptionPane();
        try {
            conn = DriverManager.getConnection(csse);
            String login = "select * from user_account where username like'"+id+"';";
            Statement statement = conn.createStatement();
            ResultSet loginreuslt = statement.executeQuery(login);
            if(loginreuslt.next()){
                if(pw.equals(loginreuslt.getString("password"))){
                    System.out.println("Login in Success");
                    new guest_mode(id);
                    new guest_table(id);
                }
                else{
                    mesgbox.showMessageDialog(null,"Wrong Password","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }

            }
            else {
                mesgbox.showMessageDialog(null,"Ur account does not exist","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
            }

            loginreuslt.close();

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
