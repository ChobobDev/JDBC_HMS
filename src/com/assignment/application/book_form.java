package com.assignment.application;

import javax.swing.*;

public class book_form extends JFrame {
    JFrame bookframe = new JFrame();
    JTextField rn,rt,ci,co;
    JLabel title,rn_l,rt_l,ci_l,co_l;
    JButton btn_room_select,btn_book,btn_cancel;

    public book_form(){
        bookframe.setSize(400,430);
        bookframe.setLocationRelativeTo(null);
        bookframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookframe.getContentPane().setLayout(null);

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

        bookframe.getContentPane().add(rt_l);
        bookframe.getContentPane().add(rn_l);
        bookframe.getContentPane().add(ci_l);
        bookframe.getContentPane().add(co_l);

        bookframe.setVisible(true);





    }
}
