package com.assignment.application;

import java.sql.*;

public class cheff_ava {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public cheff_ava(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(csse);
            Statement st = conn.createStatement();
            String cheff = "select * from chef_data;";
            ResultSet cheff_state = st.executeQuery(cheff);
            while (cheff_state.next()){
                String Working_day = cheff_state.getString("available_date");
                String Cheff_name = cheff_state.getString("chef_name");
                System.out.println(Cheff_name);
                String[] days = Working_day.split(",");
                int size = days.length;
                for(int i =0;i<size;i++){
                    if(days[i].equals("2")){
                        days[i]="Monday";
                    }
                    if(days[i].equals("3")){
                        days[i]="Tuesday";
                    }
                    if(days[i].equals("4")){
                        days[i]="Wednesday";
                    }
                    if(days[i].equals("5")){
                        days[i]="Thursday";
                    }
                    if(days[i].equals("6")){
                        days[i]="Friday";
                    }
                    if(days[i].equals("7")){
                        days[i]="Saturday";
                    }
                    if(days[i].equals("1")){
                        days[i]="Sunday";
                    }
                    System.out.println(days[i]);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
