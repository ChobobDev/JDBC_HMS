package com.assignment.application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class book_form extends JFrame {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    JFrame bookframe = new JFrame();
    JComboBox rt,rn;
    JTextField ci,co;
    JLabel title,rn_l,rt_l,ci_l,co_l;
    JButton btn_select,btn_book,btn_cancel;


    public book_form(){
        bookframe.setSize(480,430);
        bookframe.setLocationRelativeTo(null);
        bookframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookframe.getContentPane().setLayout(null);
        String[] rt_combo = {"1.Large room with double beds","2.Large room with a large single bed.","3.Small room with a single bed","4.VIP room"};
        String[] rn_combo = {};
        rt = new JComboBox(rt_combo);
        rn = new JComboBox(rn_combo);
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
                else{
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
        title = new JLabel("Reserve a Room");
        rt_l = new JLabel("Room Type");
        rn_l = new JLabel(" Room Number");
        ci_l = new JLabel("Check-in Date");
        co_l = new JLabel("Check-out Date");

        title.setBounds(30,20,340,30);
        title.setHorizontalAlignment(JLabel.CENTER);
        rt_l.setBounds(30,60,100,30);
        rt_l.setHorizontalAlignment(JLabel.CENTER);
        rn_l.setBounds(30,100,100,30);
        rn_l.setHorizontalAlignment(JLabel.CENTER);
        ci_l.setBounds(30,140,100,30);
        ci_l.setHorizontalAlignment(JLabel.CENTER);
        co_l.setBounds(30,180,100,30);
        co_l.setHorizontalAlignment(JLabel.CENTER);
        rt.setBounds(150,60,230,30);
        rn.setBounds(150,100,230,30);


        bookframe.getContentPane().add(rt_l);
        bookframe.getContentPane().add(rn_l);
        bookframe.getContentPane().add(ci_l);
        bookframe.getContentPane().add(co_l);
        bookframe.getContentPane().add(rt);
        bookframe.getContentPane().add(rn);


        bookframe.setVisible(true);





    }
}
