package com.assignment.application;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class staff_mode extends JFrame{
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    JTable table;
    JScrollPane scp;
    JLabel un_label;
    JButton lg_out;
    JFrame frame = new JFrame("Sunny Isle Hotel");
    DefaultTableModel model = new DefaultTableModel(new String[]{"Username","Room Number", "Room Type", "Check IN","Check OUT","Food"}, 0);
    public staff_mode(String un){
        Connection conn = null;
        table = new JTable(model);
        scp = new JScrollPane(table);
        try{
            conn = DriverManager.getConnection(csse);
            String data = "select * from booked_room";
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(data);
            while(res.next()){
                String un_text = res.getString("username");
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
                model.addRow(new Object[]{un_text,rn_text,rt_text,ci_text,co_text,fs});
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        table.setModel(model);


        frame.setSize(800,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        un_label = new JLabel("Username : "+un);
        lg_out = new JButton("Log-out");


        un_label.setBounds(610,0,150,30);
        lg_out.setBounds(660,30,100,30);
        scp.setBounds(30,100,740,300);
        frame.getContentPane().add(un_label);
        frame.getContentPane().add(lg_out);
        frame.getContentPane().add(scp);

        frame.setVisible(true);

        lg_out.addActionListener(e -> {frame.dispose();});

    }
}
