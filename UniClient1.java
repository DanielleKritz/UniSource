/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniclient;

import com.un.pojo.Activity;
import com.un.pojo.Course;
import com.un.pojo.Student;
import com.un.pojo.User;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author dakritz
 */
public class UniClient1 extends JFrame {

    private JButton btLogon = new JButton("Login");
    private JLabel userLabel = new JLabel("Username:  ");
    private JTextField userField = new JTextField("", 40);
    private JLabel passwordLabel = new JLabel("Password:  ");
    private JPasswordField passwordField = new JPasswordField("", 40);

    private JLabel regLabel = new JLabel("Email:  ");
    private JTextField regField = new JTextField("", 40);
    private JButton regButton = new JButton("Register");

    private User user = new User();
    private UniSourceClientServerConnect connection;
    private ClientProfile profile;

    /**
     * @param args the command line arguments
     */
    public UniClient1() {
        JFrame frame = new JFrame("UniSource - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(600, 400));
        JPanel loginPanel = new JPanel(new GridLayout(2, 1));

        JPanel regPanel = new JPanel(new GridLayout(2, 1));

        loginPanel.setPreferredSize(new Dimension(200, 200));
        JPanel unPanel = new JPanel(new BorderLayout());
        unPanel.setPreferredSize(new Dimension(100, 100));
        
        JPanel rgPanel = new JPanel(new BorderLayout());
        
        userField.setPreferredSize(new Dimension(50, 50));
        JPanel pwPanel = new JPanel(new BorderLayout());
        unPanel.add(userLabel, BorderLayout.WEST);
        unPanel.add(userField, BorderLayout.CENTER);
        pwPanel.add(passwordLabel, BorderLayout.WEST);
        pwPanel.add(passwordField, BorderLayout.CENTER);
        
        rgPanel.add(regLabel, BorderLayout.WEST);
        rgPanel.add(regField, BorderLayout.CENTER);
        
        loginPanel.add(unPanel);
        loginPanel.add(pwPanel);
        
        regPanel.add(rgPanel);
        regButton.addActionListener(new UniClient1.RegisterListener());
        regPanel.add(regButton);

        mainPanel.add(loginPanel, BorderLayout.NORTH);
        btLogon.addActionListener(new LoginListener());
        mainPanel.add(btLogon, BorderLayout.CENTER);
        
        mainPanel.add(regPanel, BorderLayout.SOUTH);
        
        int frameWidth = 600;
        int frameHeight = 400;
        frame.setBounds(350, 180, frameWidth, frameHeight);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        UniClient1 uc = new UniClient1();
    }
    
    
    private class RegisterListener implements ActionListener {

        public RegisterListener() {
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if ((regField.getText()).equals("")) {
                System.out.println("This action requires your email.");
                return;
            }
            
            try {
                connection = new UniSourceClientServerConnect(regField.getText());
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(UniClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }

    class LoginListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            /* Check that we have the username*/
            if ((userField.getText()).equals("")) {
                System.out.println("The username needs to be entered.");
                return;
            }

            /* Check that we have the password*/
            if ((passwordField.getText()).equals("")) {
                System.out.println("The password needs to be entered.");
                return;
            }

            /*Populate user data*/
            user.setUserID(userField.getText());
            user.setRoleID(1);
            user.setPassword(passwordField.getText());

            try {
                try {
                    /* Open the connection and try to send
                    the user object. */
                    connection = new UniSourceClientServerConnect(user);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UniClient1.class.getName()).log(Level.SEVERE, null, ex);
                }

                Student student = (Student) connection.getObject();
                try {
                    connection.sendMsg(2, student);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UniClient1.class.getName()).log(Level.SEVERE, null, ex);
                }
                ArrayList<Course> courses = (ArrayList<Course>) connection.getObject();
                try {
                    connection.sendMsg(4, student);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UniClient1.class.getName()).log(Level.SEVERE, null, ex);
                }
                ArrayList<Activity> activity = (ArrayList<Activity>) connection.getObject();
//                Student stud1 = new Student("dakritz@ursinus.edu", "Danielle Kritz", "123", 1, 2017, 22,
//                "Chemistry and Computer Science Double Major");
//                ArrayList<Course> cour1 = new ArrayList<>();
//                ArrayList<Activity> act1 = new ArrayList<>();
//                cour1.add(new Course("MATH235", "Discrete"));
//                cour1.add(new Course("PHYS121", "Spacetime and Quantum" ));
//                act1.add(new Activity("Volunteering at SRA", "Helping to take care of the horses and side walk in lessons"));
//                act1.add(new Activity("Women in Science and Technology Club Member", "Plan and attend events to help women in STEM at the college network and develop a community of support"));                
                profile = new ClientProfile(student, courses, activity, connection);
                //connection.sendMsg(0, user);

            } catch (IOException ex) {
                //add exception comment
            }
        }
    }

    class GenerateListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

        }
    }

    class LogoutListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

        }

    }
}
