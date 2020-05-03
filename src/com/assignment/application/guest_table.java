package com.assignment.application;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class guest_table {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public JTable guest_table;
    public static JScrollPane scp;
    DefaultTableModel model = new DefaultTableModel(new String[]{"Room Number", "Room Type", "Check IN","Check OUT","Food"}, 0);
    public guest_table(String un){
        guest_table = new JTable(model);
        scp = new JScrollPane(guest_table);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(csse);
            String data = "select * from booked_room where username like'"+un+"';";
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(data);
            while(res.next()){
                String rn_text = "";
                int rn = res.getInt("room_num");
                rn_text = String.valueOf(rn);
                int rt = res.getInt("room_type");
                String rt_text = "";
                if(rt==1){
                    rt_text = "1.Large room with double beds";
                }
                else if (rt==2){
                    rt_text = "2.Large room with a large single bed.";
                }
                else if (rt==3){
                    rt_text = "3.Small room with a single bed";
                }
                else{
                    rt_text ="4.VIP room";
                }
                Date ci = res.getDate("check_in");
                String ci_text = String.valueOf(ci);;
                Date co = res.getDate("check_out");
                String co_text = String.valueOf(co);
                int fd = res.getInt("food_status");
                String fs = "";
                if(fd==1){
                    fs="Booked";
                }
                else{
                    fs="None";
                }
                model.addRow(new Object[]{rn_text,rt_text,ci_text,co_text,fs});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        guest_table.setModel(model);

    }
}
