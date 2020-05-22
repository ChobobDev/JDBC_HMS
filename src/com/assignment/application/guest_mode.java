package com.assignment.application;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;




public class guest_mode extends JFrame {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    JTable table;
    JScrollPane scp;
    JLabel un_label;
    JButton lg_out, book_room,refresh,btn_manage,btn_food;
    JOptionPane mesgbox=new JOptionPane();
    public static JFrame guestframe = new JFrame("Sunny Isle Hotel");
    public static DefaultTableModel model = new DefaultTableModel(new String[]{"Selected","Room Number", "Room Type", "Check IN","Check OUT","Food","Booking ID"}, 0){
        public boolean isCellEditable(int i,int c){
            switch(c){
                case 0:
                    return true;
                default:
                    return false;
            }
        }
    };

    public guest_mode(String un){
        String user_name = un;
        table = new JTable(model);
        Connection conn = null;
        try {
            model.setNumRows(0);
            conn = DriverManager.getConnection(csse);
            String data = "select * from booked_room where username like'"+user_name+"';";
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(data);
            while(res.next()){
                String rn_text = "";
                int rn = res.getInt("room_num");
                rn_text = String.valueOf(rn);
                int rt = res.getInt("room_type");
                String rt_text = "";
                if(rt==1){
                    rt_text = "1.Large room with double beds";
                }
                else if (rt==2){
                    rt_text = "2.Large room with a large single bed.";
                }
                else if (rt==3){
                    rt_text = "3.Small room with a single bed";
                }
                else if (rt==4){
                    rt_text ="4.VIP room";
                }
                else if (rt==0){
                    rt_text ="Food Ordered";
                }
                Date ci = res.getDate("check_in");
                String ci_text = String.valueOf(ci);;
                Date co = res.getDate("check_out");
                String co_text = String.valueOf(co);
                int fd = res.getInt("food_status");
                String fs = "";
                if(fd==1){
                    fs="Booked";
                }
                else{
                    fs="None";
                }
                String Booking_id = res.getString("book_id");
                model.addRow(new Object[]{false,rn_text,rt_text,ci_text,co_text,fs,Booking_id});
            }
            res.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JCheckBox box = new JCheckBox();
        box.setHorizontalAlignment(JLabel.CENTER);
        table.getColumn("Selected").setCellRenderer(dcr);
        table.getColumn("Selected").setCellEditor(new DefaultCellEditor(box));
        scp = new JScrollPane(table);
        table.setModel(model);

        guestframe.setSize(800,500);
        guestframe.setLocationRelativeTo(null);
        guestframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guestframe.getContentPane().setLayout(null);
        un_label = new JLabel("Username : " + un);
        lg_out = new JButton("Log-out");
        book_room = new JButton("Book a Room");
        refresh = new JButton("Refresh");
        btn_manage = new JButton("Cancel");
        btn_food = new JButton("Order Food");

        un_label.setBounds(610,0,150,30);
        lg_out.setBounds(660,30,100,30);
        book_room.setBounds(30,30,150,30);
        refresh.setBounds(530,30,100,30);
        scp.setBounds(30,100,740,300);
        btn_manage.setBounds(400,30,100,30);
        btn_food.setBounds(660,410,100,30);
        guestframe.getContentPane().add(un_label);
        guestframe.getContentPane().add(lg_out);
        guestframe.getContentPane().add(book_room);
        guestframe.getContentPane().add(scp);
        guestframe.getContentPane().add(refresh);
        guestframe.getContentPane().add(btn_manage);
        guestframe.getContentPane().add(btn_food);
        guestframe.setVisible(true);

        book_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new book_form(user_name);
            }
        });
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new guest_mode(user_name);
            }
        });
        btn_manage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row_count = table.getRowCount();
                for( int i =0; i<row_count;i++){
                    if(model.getValueAt(i,0).toString().equals("true")){
                        String cancel_rn = model.getValueAt(i,1).toString();
                        String bkid = model.getValueAt(i,6).toString();
                        new book_cancel(bkid,cancel_rn);
                    }
                }
                new guest_mode(un);
            }
        });
        lg_out.addActionListener(e -> {
            System.exit(0);
        });
        btn_food.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row_count = table.getRowCount();
                int selected = 0;
                for( int i =0; i<row_count;i++){
                    if(model.getValueAt(i,0).toString().equals("true")){
                        selected++;
                    }
                }
                if(selected>1){
                    mesgbox.showMessageDialog(null,"Please Select one Room at a time","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }
                else if(selected==0){
                    java.sql.Date current = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    String current_date = String.valueOf(current);
                    current_date = current_date.replaceAll("-","");
                    String order_id = un.concat(current_date);
                    new food_form(order_id,0,un);
                }
                else if (selected==1){
                    for( int i =0; i<row_count;i++){
                        if(model.getValueAt(i,0).toString().equals("true")){
                            String bkid = model.getValueAt(i,6).toString();
                            new food_form(bkid,1,un);
                        }
                    }
                }
            }
        });


    }

    public DefaultTableCellRenderer dcr = new DefaultTableCellRenderer(){
        public Component getTableCellRendererComponent(Object value, boolean isSelected,boolean hasFocus,int row,int column) {
            JCheckBox chbox = new JCheckBox();
            chbox.setSelected(((Boolean)value).booleanValue());
            chbox.setHorizontalAlignment(JLabel.CENTER);
            return chbox;
        }
    };






}
