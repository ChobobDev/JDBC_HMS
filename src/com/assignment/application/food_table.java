package com.assignment.application;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class food_table {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public ArrayList<String> chef_id;
    public ArrayList<String> chef_name = new ArrayList<>();
    public ArrayList<String> mealid = new ArrayList<>();
    public ArrayList<String> mealname = new ArrayList<>();
    JTable table;
    JScrollPane scp;
    public static DefaultTableModel model = new DefaultTableModel(new String[]{"Food ID", "Food Name", "Chef","Amount"}, 0) {
        public boolean isCellEditable(int i, int c) {
            switch (c) {
                case 3:
                    return true;
                default:
                    return false;
            }
        }
    };
    public food_table(int day){
        new cheff_ava();
        cheff_ava chav = new cheff_ava();
        table = new JTable(model);
        if(day==1){
            chef_id = new ArrayList<>(chav.Sunday);
        }
        if(day==2){
            chef_id = new ArrayList<>(chav.Monday);
        }
        if(day==3){
            chef_id = new ArrayList<>(chav.Tuesday);
        }
        if(day==4){
            chef_id = new ArrayList<>(chav.Wednesday);
        }
        if(day==5){
            chef_id = new ArrayList<>(chav.Thurdsday);
        }
        if(day==6){
            chef_id = new ArrayList<>(chav.Friday);
        }
        if(day==7){
            chef_id = new ArrayList<>(chav.Saturday);
        }
        Connection conn = null;
        try{
            model.setNumRows(0);
            conn = DriverManager.getConnection(csse);
            Statement st = conn.createStatement();
            for(int i = 0;i<chef_id.size();i=i+2){
                String meal = "select * from meal_data where chef_id = "+chef_id.get(i)+";";
                ResultSet meal_rs = st.executeQuery(meal);
                int j = 0;
                while(meal_rs.next()){
                    String meal_id = meal_rs.getString("meal_id");
                    String meal_name = meal_rs.getString("meal_name");
                    mealid.add(meal_id);
                    mealname.add(meal_name);
                    chef_name.add(chef_id.get(i+1));
                    food_form.model.addRow(new Object[]{mealid.get(j),mealname.get(j),chef_name.get(j),0});
                    model.addRow(new Object[]{mealid.get(j),mealname.get(j),chef_name.get(j),0});
                    j++;
                }
                mealid.removeAll(mealid);
                mealname.removeAll(mealname);
                chef_name.removeAll(chef_name);
            }
            scp = new JScrollPane(table);
            table.setModel(model);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

}
