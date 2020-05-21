package com.assignment.application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class cheff_ava {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public ArrayList<String> Monday = new ArrayList<>();
    public ArrayList<String> Tuesday = new ArrayList<>();
    public ArrayList<String> Wednesday = new ArrayList<>();
    public ArrayList<String> Thurdsday = new ArrayList<>();
    public ArrayList<String> Friday = new ArrayList<>();
    public ArrayList<String> Saturday = new ArrayList<>();
    public ArrayList<String> Sunday = new ArrayList<>();

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
                String Cheff_id = cheff_state.getString("chef_id");
                String[] days = Working_day.split(",");
                int size = days.length;
                for(int i =0;i<size;i++){
                    if(days[i].equals("2")){
                        days[i]="Monday";
                        Monday.add(Cheff_id);
                        Monday.add(Cheff_name);
                    }
                    if(days[i].equals("3")){
                        days[i]="Tuesday";
                        Tuesday.add(Cheff_id);
                        Tuesday.add(Cheff_name);
                    }
                    if(days[i].equals("4")){
                        days[i]="Wednesday";
                        Wednesday.add(Cheff_id);
                        Wednesday.add(Cheff_name);
                    }
                    if(days[i].equals("5")){
                        days[i]="Thursday";
                        Thurdsday.add(Cheff_id);
                        Thurdsday.add(Cheff_name);
                    }
                    if(days[i].equals("6")){
                        days[i]="Friday";
                        Friday.add(Cheff_id);
                        Friday.add(Cheff_name);
                    }
                    if(days[i].equals("7")){
                        days[i]="Saturday";
                        Saturday.add(Cheff_id);
                        Saturday.add(Cheff_name);
                    }
                    if(days[i].equals("1")){
                        days[i]="Sunday";
                        Sunday.add(Cheff_id);
                        Sunday.add(Cheff_name);
                    }
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
