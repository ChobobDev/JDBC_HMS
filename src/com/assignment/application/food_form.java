package com.assignment.application;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;

public class food_form {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public JFrame food_frame = new JFrame();
    JLabel bkid;
    JButton btnsave;
    JComboBox cbb;
    Date sel_date;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public food_form(String bk_id,int c,String un){
        new cheff_ava();
        food_frame.setSize(800,500);
        food_frame.setLocationRelativeTo(null);
        food_frame.getContentPane().setLayout(null);
        bkid = new JLabel("Booking ID : "+bk_id);
        cbb = new JComboBox();
        bkid.setBounds(600,0,200,30);
        cbb.setBounds(30,10,400,30);
        food_frame.getContentPane().add(bkid);
        food_frame.getContentPane().add(cbb);
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(csse);
            Statement st = conn.createStatement();
            String day = "select * from booked_room where book_id  = "+bk_id+";";
            ResultSet day_state = st.executeQuery(day);
            String[] days = new String[] {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
            while (day_state.next()) {
                Date checkin = day_state.getDate("check_in");
                Date checkout = day_state.getDate("check_out");
                Calendar cical = Calendar.getInstance();
                cical.setTime(checkin);
                Calendar cocal = Calendar.getInstance();
                cocal.setTime(checkout);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                while(!cical.equals(cocal)){
                    String date =df.format(cical.getTime());
                    cbb.addItem(date);
                    cical.add(Calendar.DAY_OF_MONTH,1);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        cbb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cheff_ava ch = new cheff_ava();
                String selected_date = (String) cbb.getSelectedItem();
                System.out.println(selected_date);
                sel_date = Date.valueOf(selected_date);
                Calendar seldate = Calendar.getInstance();
                seldate.setTime(sel_date);
                int sel_dow = seldate.get(Calendar.DAY_OF_WEEK);
                food_table ft = new food_table(sel_dow);
                ft.scp.setBounds(30,100,740,300);
                food_frame.getContentPane().add(ft.scp);
            }
        });
        food_frame.setVisible(true);

        btnsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(c==0){
                    Connection conn = null;
                    try {
                        java.sql.Date current = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                        conn = DriverManager.getConnection(csse);
                        String save = "insert into booked_room (room_num,room_type,username,check_in,check_out,food_status,book_id)"+"values('"+"N/A"+"','"+"N/A"+"','"+un+"','"+current+"','"+"N/A"+"','"+1+"','"+bk_id+"');";
                        Statement st = conn.createStatement();
                        st.executeUpdate(save);
                    } catch (SQLException err) {
                        System.out.println(err.getMessage());
                    }
                }
                else{
                    Connection conn = null;
                    try {
                        conn = DriverManager.getConnection(csse);
                        String upd = "update from booked_room SET food_status = 0 where book_id = "+bk_id+";";
                        Statement st = conn.createStatement();
                        st.executeUpdate(upd);
                    } catch (SQLException err) {
                        System.out.println(err.getMessage());
                    }

                }
            }
        });



    }
}
