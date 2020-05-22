package com.assignment.application;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class food_form {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public JFrame food_frame = new JFrame();
    JLabel bkid;
    JButton btnsave;
    JComboBox cbb;
    Date sel_date;
    JTable tb;
    JScrollPane scp;
    public static DefaultTableModel model = new DefaultTableModel(new String[]{"Food ID", "Food Name", "Chef","Amount"}, 0) {
        public boolean isCellEditable(int i, int c) {
            switch (c) {
                case 3:
                    return true;
                default:
                    return false;
            }
        }
    };
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public food_form(String bk_id,int c,String un){
        new cheff_ava();
        tb = new JTable(model);
        food_frame.setSize(800,500);
        food_frame.setLocationRelativeTo(null);
        food_frame.getContentPane().setLayout(null);
        bkid = new JLabel("Booking ID : "+bk_id);
        cbb = new JComboBox();
        btnsave = new JButton();
        bkid.setBounds(600,0,200,30);
        cbb.setBounds(30,10,400,30);
        btnsave.setBounds(660,410,100,30);
        food_frame.getContentPane().add(bkid);
        food_frame.getContentPane().add(cbb);
        food_frame.getContentPane().add(btnsave);
        Connection conn = null;
        if(c==1){
            try{
                conn = DriverManager.getConnection(csse);
                Statement st = conn.createStatement();
                String day = "select * from booked_room where book_id  = "+bk_id+";";
                ResultSet day_state = st.executeQuery(day);
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
        }
        java.sql.Date today_date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Calendar today = Calendar.getInstance();
        today.setTime(today_date);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        today.add(Calendar.DAY_OF_MONTH,2);
        if(c==0){
            for(int i =0;i<30;i++){
                String date = df.format(today.getTime());
                cbb.addItem(date);
                today.add(Calendar.DAY_OF_MONTH,1);
            }

        }
        cbb.setSelectedIndex(0);
        cbb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setNumRows(0);
                cheff_ava ch = new cheff_ava();
                String selected_date = (String) cbb.getSelectedItem();
                System.out.println(selected_date);
                sel_date = Date.valueOf(selected_date);
                Calendar seldate = Calendar.getInstance();
                seldate.setTime(sel_date);
                int sel_dow = seldate.get(Calendar.DAY_OF_WEEK);
                new food_table(sel_dow);
            }
        });
        scp = new JScrollPane(tb);
        tb.setModel(model);
        food_frame.setVisible(true);
        scp.setBounds(30,100,740,300);
        food_frame.getContentPane().add(scp);
        btnsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(c==0){
                    int rows =
                }
                else{

                }
            }
        });
    }

}
