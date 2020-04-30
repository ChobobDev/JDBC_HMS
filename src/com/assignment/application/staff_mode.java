package com.assignment.application;

import javax.swing.*;

public class staff_mode extends JFrame{
    JLabel un_label;
    JButton lg_out;
    JFrame frame = new JFrame("Sunny Isle Hotel");
    public staff_mode(String un){
        frame.setSize(800,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        un_label = new JLabel("Username : "+un);
        lg_out = new JButton("Log-out");


        un_label.setBounds(610,0,150,30);
        lg_out.setBounds(660,30,100,30);
        frame.getContentPane().add(un_label);
        frame.getContentPane().add(lg_out);

        frame.setVisible(true);

        lg_out.addActionListener(e -> {frame.dispose();});

    }
}
