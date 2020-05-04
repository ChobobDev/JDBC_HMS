package com.assignment.application;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class book_manage extends JFrame {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    JFrame manageframe;
    JScrollPane scroll;
    JTable table_book;
    JCheckBox jcb;
    DefaultTableModel model = new DefaultTableModel(new String[]{"Select","Room Number", "Check IN"}, 0);
    public book_manage(){
        manageframe = new JFrame("Manage Reservation");
        table_book = new JTable(model);
        scroll = new JScrollPane(table_book);
        jcb = new JCheckBox();
        manageframe.setLocationRelativeTo(null);
        manageframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        manageframe.getContentPane().setLayout(null);



        manageframe.setVisible(true);




    }
}
