package com.assignment.application;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class food_show {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    JComboBox combo;
    public static JFrame showframe = new JFrame("Sunny Isle Hotel");
    public String menus;
    public String amts;
    JTable tb;
    JScrollPane scp;
    public static DefaultTableModel model = new DefaultTableModel(new String[]{"Food ID", "Food Name","Amount"}, 0) {
        public boolean isCellEditable(int i, int c) {
            return false;
        }
    };
    public food_show(String bkid) {
        tb = new JTable(model);
        showframe.setSize(800, 500);
        showframe.setLocationRelativeTo(null);
        showframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showframe.getContentPane().setLayout(null);
        combo = new JComboBox();
        combo.setBounds(30, 10, 270, 30);
        showframe.getContentPane().add(combo);
        combo.removeAllItems();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(csse);
            Statement st = conn.createStatement();
            String orders = "select * from ordered_meal where book_id = '"+bkid+"';";
            ResultSet res = st.executeQuery(orders);
            while (res.next()) {
                Date sd = res.getDate("serving_date");
                String sd_tect = String.valueOf(sd);
                sd_tect = sd_tect.concat(" ");
                String servt = res.getString("serving_time");
                String ordertime = sd_tect.concat(servt);
                combo.addItem(ordertime);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                combo.setSelectedIndex(0);
                String sel = combo.getSelectedItem().toString();
                sel = sel.replace(" ", "");
                sel = sel.replace("-", "");
                String orderid = bkid.concat(sel);
                orderid = orderid.replace(":", "");
                System.out.println(orderid);
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(csse);
                    Statement st = conn.createStatement();
                    String meals = "select * from ordered_meal where order_id = '"+orderid+"';";
                    ResultSet res = st.executeQuery(meals);
                    while (res.next()) {
                        menus = res.getString("meal_id");
                        amts = res.getString("amount");
                    }
                } catch (SQLException err) {
                    System.out.println(err.getMessage());
                }
            }
        });
    }
}
