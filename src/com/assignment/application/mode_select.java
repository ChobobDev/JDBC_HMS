package com.assignment.application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mode_select extends JFrame{
    JLabel title;
    JButton btn_guest,btn_staff;
    JFrame frame = new JFrame("Sunny Isle Hotel");

    public mode_select(){
        frame.setSize(320,150);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        title = new JLabel("Please select the MODE below");
        btn_guest = new JButton("Guest Mode");
        btn_staff = new JButton("Staff Mode");

        title.setBounds(30, 10,260,30);
        title.setHorizontalAlignment(JLabel.CENTER);
        btn_guest.setBounds(30, 50, 110, 30);
        btn_staff.setBounds(170, 50, 110, 30);
        frame.getContentPane().add(title);
        frame.getContentPane().add(btn_guest);
        frame.getContentPane().add(btn_staff);

        frame.setVisible(true);
        btn_guest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home_login();
            }
        });

        btn_staff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new staff_login();
            }
        });

    }

    public static void main(String[] args){
        new mode_select();

    }
}
