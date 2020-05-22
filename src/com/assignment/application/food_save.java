package com.assignment.application;

import java.sql.*;
import java.util.Calendar;

public class food_save {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public food_save(String bkid, Date sd, String st,int c,String un){
        Connection conn = null;
        int menu = food_form.Id.size();
        String na = "N/A";
        String mealid = "";
        String mealamt = "";
        for(int i =0;i<menu;i++){
            String id = food_form.Id.get(i);
            String amt = food_form.Amount.get(i).toString();
            mealid=mealid.concat(id);
            if(i!=menu-1){
                mealid=mealid.concat(",");
            }
            mealamt=mealamt.concat(amt);
            if(i!=menu-1){
                mealamt=mealamt.concat(",");
            }
        }
        System.out.println(mealid);
        System.out.println(mealamt);
        food_form.Id.removeAll(food_form.Id);
        food_form.Menu.removeAll(food_form.Menu);
        food_form.Amount.removeAll(food_form.Amount);
        try{
            conn = DriverManager.getConnection(csse);
            String order = "insert into ordered_meal (book_id,meal_id,amount,serving_date,serving_time)"+"values('"+bkid+"','"+mealid+"','"+mealamt+"','"+sd+"','"+st+"')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(order);
            if(c==0){
                java.sql.Date today_date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                String rn = "0000";
                String rt = "0";
                String save = "insert into booked_room (room_num,room_type,username,check_in,check_out,food_status,book_id)"+"values('"+rn+"','"+rt+"','"+un+"','"+today_date+"','"+today_date+"','"+1+"','"+bkid+"');";
                statement.executeUpdate(save);
            }
            if(c==1){
                try {
                    String upd = "update booked_room SET food_status = 1 where book_id = "+bkid+";";
                    statement.executeUpdate(upd);
                } catch (SQLException err) {
                    System.out.println(err.getMessage());
                }
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }
}
