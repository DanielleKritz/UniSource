package unisource;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author dakritz
 */
public class UniClient extends JFrame{

    private JButton btTransGenerate = new JButton("Transcript");
    private JButton btLogon = new JButton("Login");
    private JButton btQuit = new JButton("Quit");
    private JButton btAddCourse = new JButton("Add Course");
    private JButton btEditCourse = new JButton("Edit Course");
    private JLabel userLabel = new JLabel("Username:  ");
    private JTextField userField = new JTextField("", 40);
    private JLabel passwordLabel = new JLabel("Password:  ");
    private JTextField passwordField = new JTextField("", 40);
    private JLabel nameLabel = new JLabel("Name:");
    private JLabel yearLabel = new JLabel("Year:");
    private JLabel majorLabel = new JLabel("Major(s):");
    private JLabel minorLabel = new JLabel("Minor(s):");
    private JLabel creditsLabel = new JLabel("Credits Completed:");
    
    /**
     * @param args the command line arguments
     */
    
    public UniClient(){
        JFrame frame = new JFrame("UniSource");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel loginPanel = new JPanel(new GridLayout(2,1));
        JPanel unPanel = new JPanel(new BorderLayout());
        JPanel pwPanel = new JPanel(new BorderLayout());
        unPanel.add(userLabel, BorderLayout.WEST);
        unPanel.add(userField, BorderLayout.CENTER);
        pwPanel.add(passwordLabel, BorderLayout.WEST);
        pwPanel.add(passwordField, BorderLayout.CENTER);
        loginPanel.add(unPanel);
        loginPanel.add(pwPanel);
        mainPanel.add(loginPanel, BorderLayout.CENTER);
        btLogon.addActionListener(new LoginListener());
        mainPanel.add(btLogon, BorderLayout.SOUTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        //JTabbedPane tabbedPane = new JTabbedPane();
    }
    
    public static void main(String[] args) {
        new UniClient();
    }
    
    class LoginListener implements ActionListener{
        public void actionPerformed(ActionEvent event) { 
            /* Check that we have the username*/
	    if ((userField.getText()).equals("")) {
		System.out.println("The username needs to be entered.");
		return;
	    }

	    /* Check that we have the password*/ 
           if((passwordField.getText()).equals("")) {
		System.out.println("The password needs to be entered.");
		return;
	    }
           
           try{
               LoginRequest logon = new LoginRequest(userField.getText(), passwordField.getText());
           }catch(Exception e){
               
           }
        }
        
    class GenerateListener implements ActionListener{
        public void actionPerformed(ActionEvent event) { 
            
        }
    }
    
    class LogoutListener implements ActionListener{
        public void actionPerformed(ActionEvent event) { 
            
        }

    }
}
}


