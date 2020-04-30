package com.assignment.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class regi_form extends JFrame{
    JFrame registerframe = new JFrame();
    JTextField id,passport,email,phonenum;
    JPasswordField pw,pw_veri;
    JButton btn_save,btn_cancel,btn_username;
    JLabel title,id_label,pw_label,veri_label,pst_label,email_label,phonenum_label;



    public regi_form(){
        JOptionPane mesgbox=new JOptionPane();

        registerframe.setSize(400,430);
        registerframe.setLocationRelativeTo(null);
        registerframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerframe.getContentPane().setLayout(null);


        title = new JLabel("Registration");
        id_label = new JLabel("ID");
        pw_label = new JLabel("Password");
        veri_label = new JLabel("Re-type PW");
        pst_label = new JLabel("Passport Number");
        email_label = new JLabel("E-mail");
        phonenum_label = new JLabel("Phone Number");
        id = new JTextField();
        pw = new JPasswordField();
        pw_veri = new JPasswordField();
        passport = new JTextField();
        email = new JTextField();
        phonenum = new JTextField();
        btn_username = new JButton("Check");
        btn_save = new JButton("Save");
        btn_cancel = new JButton("Cancel");

        title.setBounds(30,20,340,30);
        title.setHorizontalAlignment(JLabel.CENTER);
        id_label.setBounds(30,60,100,30);
        id_label.setHorizontalAlignment(JLabel.CENTER);
        id.setBounds(150,60,100,30);
        btn_username.setBounds(260,60, 90,30);
        pw_label.setBounds(30,100,100,30);
        pw_label.setHorizontalAlignment(JLabel.CENTER);
        pw.setBounds(150,100,200,30);
        veri_label.setBounds(30,140,100,30);
        veri_label.setHorizontalAlignment(JLabel.CENTER);
        pw_veri.setBounds(150,140,200,30);
        pst_label.setBounds(30,180,100,30);
        pst_label.setHorizontalAlignment(JLabel.CENTER);
        passport.setBounds(150,180,200,30);
        email_label.setBounds(30,220,100,30);
        email_label.setHorizontalAlignment(JLabel.CENTER);
        email.setBounds(150,220,200,30);
        phonenum_label.setBounds(30,260,100,30);
        phonenum_label.setHorizontalAlignment(JLabel.CENTER);
        phonenum.setBounds(150,260,200,30);
        btn_save.setBounds(30,330,150,30);
        btn_cancel.setBounds(210,330,150,30);


        registerframe.getContentPane().add(title);
        registerframe.getContentPane().add(id_label);
        registerframe.getContentPane().add(id);
        registerframe.getContentPane().add(pw_label);
        registerframe.getContentPane().add(pw);
        registerframe.getContentPane().add(veri_label);
        registerframe.getContentPane().add(pw_veri);
        registerframe.getContentPane().add(pst_label);
        registerframe.getContentPane().add(passport);
        registerframe.getContentPane().add(email_label);
        registerframe.getContentPane().add(email);
        registerframe.getContentPane().add(phonenum_label);
        registerframe.getContentPane().add(phonenum);
        registerframe.getContentPane().add(btn_save);
        registerframe.getContentPane().add(btn_cancel);
        registerframe.getContentPane().add(btn_username);






        registerframe.setVisible(true);
        btn_cancel.addActionListener(e -> {registerframe.dispose();});

        btn_username.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String username = id.getText();
                new id_checker(username);
            }


        });

        btn_save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String pw_val = String.valueOf(pw.getPassword());
                String pw_v = String.valueOf(pw_veri.getPassword());
                if(id_checker.id_checked_status==1){
                    if(pw_val.equals(pw_v)){
                        String un_new = id.getText();
                        String pw_new = String.valueOf(pw.getPassword());
                        String pst_new = passport.getText();
                        String em_new = email.getText();
                        String pn_new = phonenum.getText();
                        new sql_save(un_new,pw_new,pst_new,em_new,pn_new);
                        registerframe.dispose();
                    }
                    else{
                        mesgbox.showMessageDialog(null,"Password mismatch","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
                    }

                }
                else{
                    mesgbox.showMessageDialog(null,"ID should be checked","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
                }



            }
        });




    }

}
