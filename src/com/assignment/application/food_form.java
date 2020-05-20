package com.assignment.application;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;

public class food_form {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    JFrame food_frame = new JFrame();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    java.sql.Date current_time = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    int current_day = current_time.getDay();

    public food_form(String bk_id){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(csse);
            Statement st = conn.createStatement();
            String day = "select * from booked_room where book_id  = "+bk_id+";";
            ResultSet day_state = st.executeQuery(day);
            System.out.println(current_time);
            while (day_state.next()) {
                Date checkin = day_state.getDate("check_in");
                Date checkout = day_state.getDate("check_out");
                Calendar ci_cal = Calendar.getInstance();
                Calendar co_cal = Calendar.getInstance();
                ci_cal.setTime(checkin);
                co_cal.setTime(checkout);
                int dow_i = ci_cal.get(Calendar.DAY_OF_WEEK);
                int dow_o = co_cal.get(Calendar.DAY_OF_WEEK);
                System.out.println(dow_i);
                System.out.println(dow_o);

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        food_frame.setSize(800,500);
        food_frame.setLocationRelativeTo(null);
        food_frame.setVisible(true);



    }
}
