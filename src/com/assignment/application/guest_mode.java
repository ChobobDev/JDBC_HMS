package com.assignment.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class guest_mode extends JFrame {
    JTable table;
    JLabel un_label;
    JButton lg_out, book_room;
    JFrame frame = new JFrame("Sunny Isle Hotel");
    public guest_mode(String un){
        new show_booked(un);
        frame.setSize(800,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        String data[][]={ {"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"}};
        String column[]={"Room_number","Room Type","Check in","Check out"};

        un_label = new JLabel("Username : "+un);
        lg_out = new JButton("Log-out");
        book_room = new JButton("Book a Room");
        table = new JTable(data,column);


        un_label.setBounds(610,0,150,30);
        lg_out.setBounds(660,30,100,30);
        book_room.setBounds(30,30,150,30);
        table.setBounds(30,80,740,300);
        frame.getContentPane().add(un_label);
        frame.getContentPane().add(lg_out);
        frame.getContentPane().add(book_room);
        frame.getContentPane().add(table);


        frame.setVisible(true);

        lg_out.addActionListener(e -> {frame.dispose();});

    }
}
