package com.assignment.application;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class book_save {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public static  int available = 0;
    public static  int room_number;
    JOptionPane mesgbox=new JOptionPane();
    public book_save(String rn, int rt, Date ci, Date co,String un){
        List <Object> rn_array = new ArrayList<Object>();
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(csse);
            String rt_parse = "select * from room_data where room_type like'"+rt+"';";
            Statement statement = conn.createStatement();
            ResultSet rn_reuslt = statement.executeQuery(rt_parse);
            while (rn_reuslt.next()){
                if(available==rn_reuslt.getInt("booked_status")){
                    int aval_rn =rn_reuslt.getInt("room_num");
                    rn_array.add(aval_rn);
                }
            }
            int size = rn_array.size();
            Random rnd = new Random();
            int rnd_sel = rnd.nextInt(size);
            if(rn.equals("Skip")){
                room_number = (int) rn_array.get(rnd_sel);
            }
            else{
                room_number = Integer.parseInt(rn);
            }
            System.out.println(room_number);
            try {
                conn = DriverManager.getConnection(csse);
                String date = "select * from booked_room where room_num like'"+room_number+"';";
                Statement stmt = conn.createStatement();
                ResultSet date_ava = stmt.executeQuery(date);
                if(date_ava.next()){
                    Date booked_co = date_ava.getDate("check_out");
                    Date booked_ci = date_ava.getDate("check_in");
                    int compare = ci.compareTo(booked_co);
                    int compare_2 = co.compareTo(booked_ci);
                    if(compare<0||compare_2>0){
                        mesgbox.showMessageDialog(null,"This room is not available on that date","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        conn = DriverManager.getConnection(csse);
                        String save = "insert into booked_room (room_num,room_type,username,check_in,check_out,food_status)"+"values('"+room_number+"','"+rt+"','"+un+"','"+ci+"','"+co+"','"+0+"');";
                        Statement st = conn.createStatement();
                        st.executeUpdate(save);
                        String update = "update room_data SET booked_status = 1 where room_num = "+room_number+";";
                        Statement st_up = conn.createStatement();
                        st_up.executeUpdate(update);
                        book_form.bookframe.dispose();
                        new guest_mode(un);


                    }
                }
                else{
                    conn = DriverManager.getConnection(csse);
                    String save = "insert into booked_room (room_num,room_type,username,check_in,check_out,food_status)"+"values('"+room_number+"','"+rt+"','"+un+"','"+ci+"','"+co+"','"+0+"');";
                    Statement st = conn.createStatement();
                    st.executeUpdate(save);
                    String update = "update room_data SET booked_status = 1 where room_num = "+room_number+";";
                    Statement st_up = conn.createStatement();
                    st_up.executeUpdate(update);
                    book_form.bookframe.dispose();
                    new guest_mode(un);


                }


            }catch (SQLException err){
                System.out.println(err.getMessage());
            }



        }catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }
}
