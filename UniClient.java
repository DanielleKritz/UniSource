/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniclient;

import com.un.pojo.Message;
import com.un.pojo.User;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author dakritz
 */
public class UniClient extends JFrame {

    private JButton btLogon = new JButton("Login");
    private JLabel userLabel = new JLabel("Username:  ");
    private JTextField userField = new JTextField("", 40);
    private JLabel passwordLabel = new JLabel("Password:  ");
    private JTextField passwordField = new JTextField("", 40);

    private JLabel regLabel = new JLabel("Email:  ");
    private JTextField regField = new JTextField("", 40);
    private JButton regButton = new JButton("Register");

    private User user = new User();
    private UniSourceClientServerConnect connection;

    /**
     * @param args the command line arguments
     */
    public UniClient() {
        JFrame frame = new JFrame("UniSource - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel loginPanel = new JPanel(new GridLayout(2, 1));

        JPanel regPanel = new JPanel(new GridLayout(2, 1));

        JPanel unPanel = new JPanel(new BorderLayout());
        JPanel pwPanel = new JPanel(new BorderLayout());

        JPanel rgPanel = new JPanel(new BorderLayout());

        unPanel.add(userLabel, BorderLayout.WEST);
        unPanel.add(userField, BorderLayout.CENTER);
        pwPanel.add(passwordLabel, BorderLayout.WEST);
        pwPanel.add(passwordField, BorderLayout.CENTER);

        rgPanel.add(regLabel, BorderLayout.WEST);
        rgPanel.add(regField, BorderLayout.CENTER);

        loginPanel.add(unPanel);
        loginPanel.add(pwPanel);

        regPanel.add(rgPanel);
        regButton.addActionListener(new RegisterListener());
        regPanel.add(regButton);

        mainPanel.add(loginPanel, BorderLayout.NORTH);
        btLogon.addActionListener(new LoginListener());
        mainPanel.add(btLogon, BorderLayout.CENTER);

        mainPanel.add(regPanel, BorderLayout.SOUTH);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new UniClient();
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

//    class 
    class LoginListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            Message message;
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

            /* Create the message, open the connection and try to send
	       the user object. */
//            try {
//
//                message = new Message(0, user);
//                //System.out.println(envelope.toString());
//            } catch (Exception e) {
//                /* If there is an error, do not go further */
//                return;
//            }
            try {
                //            try {
                connection = new UniSourceClientServerConnect(user);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(UniClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println(connection.getObject().toString());
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
