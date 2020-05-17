package com.assignment.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home_login extends JFrame{
    JButton btn_login,btn_signup;
    JTextField id_input;
    JPasswordField pw_input;
    JLabel id_label,pw_label,title;
    JFrame frame = new JFrame("Sunny Isle Hotel");
    public Home_login(){
        frame.setSize(400,270);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        title = new JLabel("Welcome to Guest Mode");
        btn_login = new JButton("Log - In");
        btn_signup = new JButton("Sign Up");
        id_label = new JLabel("ID");
        pw_label = new JLabel("PW");
        id_input = new JTextField();
        pw_input = new JPasswordField();


        title.setBounds(30,30,340,30);
        title.setHorizontalAlignment(JLabel.CENTER);
        btn_login.setBounds(270, 80, 100, 70);
        btn_signup.setBounds(30, 170, 340, 30);
        id_label.setBounds(30,80, 30,30);
        id_input.setBounds(60,80,200,30);
        pw_label.setBounds(30,120,30,30);
        pw_input.setBounds(60,120,200,30);

        frame.getContentPane().add(title);
        frame.getContentPane().add(btn_login);
        frame.getContentPane().add(btn_signup);
        frame.getContentPane().add(id_label);
        frame.getContentPane().add(id_input);
        frame.getContentPane().add(pw_label);
        frame.getContentPane().add(pw_input);
        frame.setVisible(true);

        btn_signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new regi_form();
            }

        });

        btn_login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String id_val = id_input.getText();
                String pw_val = String.valueOf(pw_input.getPassword());
                new login_checker(id_val,pw_val);
                id_input.setText("");
                pw_input.setText("");
                frame.dispose();

            }
        });






    }




}
