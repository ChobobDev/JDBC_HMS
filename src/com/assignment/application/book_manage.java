package com.assignment.application;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class book_manage extends JFrame {
    JFrame manageframe;
    JTable table_book;
    public static DefaultTableModel model = new DefaultTableModel(new String[]{"Select","Room Number", "Check IN"}, 0);
    public book_manage(){
        manageframe = new JFrame("Manage Reservation");
        manageframe.setLocationRelativeTo(null);
        manageframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        manageframe.getContentPane().setLayout(null);



        manageframe.setVisible(true);




    }
}
