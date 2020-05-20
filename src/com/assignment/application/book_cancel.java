package com.assignment.application;

import java.sql.*;

public class book_cancel {
    public int other_book = 0;
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    public book_cancel(String bi,String crn){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(csse);
            String delete = "delete from booked_room where book_id = "+bi+";";
            Statement st = conn.createStatement();
            st.execute(delete);
            String room_state = "select * from booked_room where room_num = "+crn+";";
            ResultSet state = st.executeQuery(room_state);
            while (state.next()){
                other_book++;
            }
            if(other_book==0){
                String update = "update room_data SET booked_status = 0 where room_num = "+crn+";";
                st.execute(update);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

}
