package com.assignment.application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;


public class book_form extends JFrame {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public int leap_year = 0;
    public int leap_year_o = 0;
    JFrame bookframe = new JFrame();
    JComboBox rt,rn,ci_y,ci_m,ci_d,co_y,co_m,co_d;
    JLabel title,rn_l,rt_l,ci_l,co_l,y,m,d;
    JButton btn_select,btn_book,btn_cancel;


    public book_form(){
        bookframe.setSize(480,430);
        bookframe.setLocationRelativeTo(null);
        bookframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookframe.getContentPane().setLayout(null);
        String[] rt_combo = {"","1.Large room with double beds","2.Large room with a large single bed.","3.Small room with a single bed","4.VIP room"};
        String[] rn_combo = {};
        String[] year = {"","2020","2021","2022","2023","2024"};
        String[] month = {"","1","2","3","4","5","6","7","8","9","10","11","12"};
        String[] day = {};
        rt = new JComboBox(rt_combo);
        rn = new JComboBox(rn_combo);
        ci_y =new JComboBox(year);
        ci_m =new JComboBox(month);
        ci_d =new JComboBox(day);
        co_y =new JComboBox(year);
        co_m =new JComboBox(month);
        co_d =new JComboBox(day);

        rt.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection conn = null;
                int rt_int = 0;
                String rt_val = (String) rt.getSelectedItem();
                System.out.println(rt_val);
                if(rt_val=="1.Large room with double beds"){
                    rt_int = 1;
                }
                else if (rt_val=="2.Large room with a large single bed."){
                    rt_int = 2;
                }
                else if (rt_val=="3.Small room with a single bed"){
                    rt_int = 3;
                }
                else if (rt_val=="4.VIP room"){
                    rt_int = 4;
                }
                System.out.println(rt_int);
                try {
                    rn.removeAllItems();
                    conn = DriverManager.getConnection(csse);
                    String rt_search = "select * from room_data where room_type like'"+rt_int+"';";
                    Statement statement = conn.createStatement();
                    ResultSet rt_reuslt = statement.executeQuery(rt_search);
                    while (rt_reuslt.next()){
                        int roomn = rt_reuslt.getInt("room_num");
                        String rn_text = String.valueOf(roomn);
                        rn.addItem(rn_text);
                    }
                    rn.addItem("Skip");

                }catch (SQLException err) {
                    System.out.println(err.getMessage());
                }

            }
        });

        ci_y.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ci_m.setSelectedIndex(0);
                ci_d.removeAllItems();
                String ciyval = (String) ci_y.getSelectedItem();
                System.out.println(ciyval);
                if(ciyval.equals("2020")||ciyval.equals("2024")){
                    leap_year = 1;
                }
                else{
                    leap_year = 0;
                }
            }
        });
        ci_m.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ci_d.removeAllItems();
                String cimval = (String) ci_m.getSelectedItem();
                if(cimval.equals("2")&&leap_year==1){
                    for(int i =1;i<30;i++){
                        ci_d.addItem(i);
                    }
                }
                else if(cimval.equals("2")){
                    for(int i =1;i<29;i++){
                        ci_d.addItem(i);
                    }
                }
                else if(cimval.equals("1")||cimval.equals("3")||cimval.equals("5")||cimval.equals("7")||cimval.equals("8")||cimval.equals("10")||cimval.equals("12")){
                    for(int i =1;i<32;i++){
                        ci_d.addItem(i);
                    }

                }
                else{
                    for(int i =1;i<31;i++){
                        ci_d.addItem(i);
                    }

                }
            }
        });

        co_y.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                co_m.setSelectedIndex(0);
                co_d.removeAllItems();
                String coyval = (String) co_y.getSelectedItem();
                System.out.println(coyval);
                if(coyval.equals("2020")||coyval.equals("2024")){
                    leap_year_o = 1;
                }
                else{
                    leap_year_o = 0;
                }
            }
        });
        co_m.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                co_d.removeAllItems();
                String comval = (String) co_m.getSelectedItem();
                if(comval.equals("2")&&leap_year_o==1){
                    for(int i =1;i<30;i++){
                        co_d.addItem(i);
                    }
                }
                else if(comval.equals("2")){
                    for(int i =1;i<29;i++){
                        co_d.addItem(i);
                    }
                }
                else if(comval.equals("1")||comval.equals("3")||comval.equals("5")||comval.equals("7")||comval.equals("8")||comval.equals("10")||comval.equals("12")){
                    for(int i =1;i<32;i++){
                        co_d.addItem(i);
                    }

                }
                else{
                    for(int i =1;i<31;i++){
                        co_d.addItem(i);
                    }

                }
            }
        });



        title = new JLabel("Reserve a Room");
        rt_l = new JLabel("Room Type");
        rn_l = new JLabel(" Room Number");
        ci_l = new JLabel("Check-in Date");
        co_l = new JLabel("Check-out Date");
        y = new JLabel("YYYY");
        m = new JLabel("MM");
        d = new JLabel("DD");

        title.setBounds(30,20,340,30);
        title.setHorizontalAlignment(JLabel.CENTER);
        rt_l.setBounds(30,60,100,30);
        rt_l.setHorizontalAlignment(JLabel.CENTER);
        rn_l.setBounds(30,100,100,30);
        rn_l.setHorizontalAlignment(JLabel.CENTER);
        ci_l.setBounds(30,180,100,30);
        ci_l.setHorizontalAlignment(JLabel.CENTER);
        co_l.setBounds(30,220,100,30);
        co_l.setHorizontalAlignment(JLabel.CENTER);
        rt.setBounds(150,60,230,30);
        rn.setBounds(150,100,230,30);
        y.setBounds(150,140,70,30);
        y.setHorizontalAlignment(JLabel.CENTER);
        m.setBounds(230,140,70,30);
        m.setHorizontalAlignment(JLabel.CENTER);
        d.setBounds(310,140,70,30);
        d.setHorizontalAlignment(JLabel.CENTER);
        ci_y.setBounds(150,180,70,30);
        ci_m.setBounds(230,180,70,30);
        ci_d.setBounds(310,180,70,30);
        co_y.setBounds(150,220,70,30);
        co_m.setBounds(230,220,70,30);
        co_d.setBounds(310,220,70,30);


        bookframe.getContentPane().add(rt_l);
        bookframe.getContentPane().add(rn_l);
        bookframe.getContentPane().add(ci_l);
        bookframe.getContentPane().add(co_l);
        bookframe.getContentPane().add(rt);
        bookframe.getContentPane().add(rn);
        bookframe.getContentPane().add(y);
        bookframe.getContentPane().add(m);
        bookframe.getContentPane().add(d);
        bookframe.getContentPane().add(ci_y);
        bookframe.getContentPane().add(ci_m);
        bookframe.getContentPane().add(ci_d);
        bookframe.getContentPane().add(co_y);
        bookframe.getContentPane().add(co_m);
        bookframe.getContentPane().add(co_d);


        bookframe.setVisible(true);





    }
}
