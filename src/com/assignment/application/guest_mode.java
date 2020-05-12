package com.assignment.application;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.Date;




public class guest_mode extends JFrame {
    private static String csse = "jdbc:mysql://csse-mysql.xjtlu.edu.cn:3306/SCho18?user=SCho18&password=123";
    JTable table;
    JScrollPane scp;
    JLabel un_label;
    JButton lg_out, book_room,refresh,btn_manage;
    public static JFrame guestframe = new JFrame("Sunny Isle Hotel");
    public static DefaultTableModel model = new DefaultTableModel(new String[]{"Selected","Room Number", "Room Type", "Check IN","Check OUT","Food"}, 0){
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
        table = new JTable(model);
        Connection conn = null;
        try {
            model.setNumRows(0);
            conn = DriverManager.getConnection(csse);
            String data = "select * from booked_room where username like'"+un+"';";
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
                else{
                    rt_text ="4.VIP room";
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
                model.addRow(new Object[]{false,rn_text,rt_text,ci_text,co_text,fs});
            }
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
        guestframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        guestframe.getContentPane().setLayout(null);
        un_label = new JLabel("Username : "+un);
        lg_out = new JButton("Log-out");
        book_room = new JButton("Book a Room");
        refresh = new JButton("Refresh");
        btn_manage = new JButton("Manage");

        un_label.setBounds(610,0,150,30);
        lg_out.setBounds(660,30,100,30);
        book_room.setBounds(30,30,150,30);
        refresh.setBounds(530,30,100,30);
        scp.setBounds(30,100,740,300);
        btn_manage.setBounds(400,30,100,30);
        guestframe.getContentPane().add(un_label);
        guestframe.getContentPane().add(lg_out);
        guestframe.getContentPane().add(book_room);
        guestframe.getContentPane().add(scp);
        guestframe.getContentPane().add(refresh);
        guestframe.getContentPane().add(btn_manage);
        lg_out.addActionListener(e -> {guestframe.dispose();});
        book_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new book_form(un);
            }
        });
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.fireTableDataChanged();
                new guest_mode(un);
            }
        });
        btn_manage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row_count = table.getRowCount();
                for( int i =0; i<row_count;i++){
                    if(model.getValueAt(i,0).toString().equals("true")){
                        String cancel_rn = model.getValueAt(i,1).toString();
                        System.out.println(cancel_rn);
                    }
                }

            }
        });


        guestframe.setVisible(true);

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
