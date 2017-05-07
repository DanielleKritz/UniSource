/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniclient;

import com.un.pojo.Activity;
import com.un.pojo.Course;
import com.un.pojo.Student;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author dakritz
 */
public class ClientProfile extends JFrame {

    /* private JButton btTransGenerate = new JButton("Transcript");
    private JButton btQuit = new JButton("Quit");
    private JButton btAddCourse = new JButton("Add Course");
    private JButton btEditCourse = new JButton("Edit Course");
    private JLabel nameLabel = new JLabel("Name:");
    private JLabel yearLabel = new JLabel("Year:");
    private JLabel majorLabel = new JLabel("Major(s):");
    private JLabel minorLabel = new JLabel("Minor(s):");
    private JLabel creditsLabel = new JLabel("Credits Completed:");*/
    JButton updateStudButton = new JButton("Update");
    JButton addCourseButton = new JButton("Add");
    JButton addActButton = new JButton("Add");
    JButton deleteActButton = new JButton("Delete");
    JButton updateActButton = new JButton("Update");
    JButton generateButton = new JButton("Generate");
    JLabel nameLabel;
    JLabel ageLabel;
    JLabel yearLabel;
    JLabel descriptLabel;
    JTextField nameField;
    JTextField ageField;
    JTextField yearField;
    JTextField descriptField;
    private Student s;
    private ArrayList<Course> c;
    private ArrayList<Activity> a;
    private UniSourceClientServerConnect con;
    JFrame frame;
    ArrayList<JTextField> panels;
    ArrayList<JTextField> actPanels;

    public ClientProfile(Student s, ArrayList<Course> c, ArrayList<Activity> a, UniSourceClientServerConnect con) {
        this.a = a;
        this.c = c;
        this.s = s;
        this.con = con;
        frame = new JFrame("UniSource");
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel p1 = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new BorderLayout());
        JPanel p3 = new JPanel(new BorderLayout());
        JPanel p4 = new JPanel(new BorderLayout());
        JPanel p5 = new JPanel(new BorderLayout());
        Dimension panelDim = new Dimension(1000, 600);
        p1.setBackground(Color.decode("0XCC0000"));
        p1.setPreferredSize(panelDim);
        p2.setBackground(Color.decode("0XCC0000"));
        p2.setPreferredSize(panelDim);
        p3.setBackground(Color.decode("0XCC0000"));
        p3.setPreferredSize(panelDim);
        p4.setBackground(Color.decode("0XCC0000"));
        p4.setPreferredSize(panelDim);
        p5.setBackground(Color.decode("0XCC0000"));
        p5.setPreferredSize(panelDim);

        /*Student Profile Tab*/
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel leftCPanel = new JPanel(new BorderLayout());
        JPanel rightCPanel = new JPanel(new BorderLayout());
        JPanel fieldsPanel1 = new JPanel(new BorderLayout());
        JPanel fieldsPanel2 = new JPanel(new BorderLayout());
        JPanel fieldsPanel1a = new JPanel(new BorderLayout());
        JPanel fieldsPanel1b = new JPanel(new BorderLayout());
        JPanel fieldsPanel2a = new JPanel(new BorderLayout());
        JPanel fieldsPanel2b = new JPanel(new BorderLayout());
        JPanel eachPanel1 = new JPanel(new BorderLayout());
        eachPanel1.setPreferredSize(new Dimension(100, 25));
        JPanel eachPanel2 = new JPanel(new BorderLayout());
        JPanel eachPanel3 = new JPanel(new BorderLayout());
        JPanel eachPanel4 = new JPanel(new BorderLayout());
        updateStudButton = new JButton("Update");
        nameLabel = new JLabel("Name:  ");
        ageLabel = new JLabel("Age:  ");
        yearLabel = new JLabel("Year:  ");
        descriptLabel = new JLabel("Description:  ");
        nameField = new JTextField(s.getUsername(), 40);
        ageField = new JTextField(Integer.toString(s.getAge()), 40);
        yearField = new JTextField(Integer.toString(s.getGraduateYear()), 40);
        descriptField = new JTextField(s.getDescription(), 40);
        //nameField.addActionListener(new changeFieldListener());
        //ageField.addActionListener(new changeFieldListener());
        //yearField.addActionListener(new changeFieldListener());
        //descriptField.addActionListener(new changeFieldListener());
        eachPanel1.add(nameLabel, BorderLayout.WEST);
        eachPanel1.add(nameField, BorderLayout.EAST);
        eachPanel2.add(ageLabel, BorderLayout.WEST);
        eachPanel2.add(ageField, BorderLayout.EAST);
        eachPanel3.add(yearLabel, BorderLayout.WEST);
        eachPanel3.add(yearField, BorderLayout.EAST);
        eachPanel4.add(descriptLabel, BorderLayout.WEST);
        eachPanel4.add(descriptField, BorderLayout.EAST);
        fieldsPanel1a.add(eachPanel1);
        fieldsPanel1b.add(eachPanel2);
        fieldsPanel2a.add(eachPanel3);
        fieldsPanel2b.add(eachPanel4);
        fieldsPanel2.add(fieldsPanel2a, BorderLayout.NORTH);
        fieldsPanel2.add(fieldsPanel2b, BorderLayout.SOUTH);
        fieldsPanel1.add(fieldsPanel1a, BorderLayout.NORTH);
        fieldsPanel1.add(fieldsPanel1b, BorderLayout.SOUTH);
        rightCPanel.add(fieldsPanel1, BorderLayout.NORTH);
        rightCPanel.add(fieldsPanel2, BorderLayout.SOUTH);
        centerPanel.add(rightCPanel, BorderLayout.EAST);
        centerPanel.add(leftCPanel, BorderLayout.WEST);
        p1.add(centerPanel, BorderLayout.CENTER);
        updateStudButton.addActionListener(new updateStudListener());
        p1.add(updateStudButton, BorderLayout.SOUTH);
        tabbedPane.addTab("Student Profile", p1);

        /*Student Courses Tab*/
        int numCourses = c.size();
        JPanel tablePanel = new JPanel(new GridLayout((numCourses + 2), 2));
        panels = new ArrayList<JTextField>();
        panels.add(new JTextField("Course Number"));
        panels.add(new JTextField("Course Name"));
        Iterator<Course> ci = c.iterator();
        int i = 0;
        while (ci.hasNext()) {
            Course current = ci.next();
            panels.add(new JTextField(current.getCourseCode()));
            panels.add(new JTextField(current.getCourseName()));
        }
        panels.add(new JTextField("Add Course Number Here"));
        panels.add(new JTextField("Add Course Name Here"));
        Iterator<JTextField> tfi = panels.iterator();
        while (tfi.hasNext()) {
            tablePanel.add(tfi.next());
        }
        p2.add(tablePanel, BorderLayout.CENTER);
        addCourseButton.addActionListener(new addCourseListener());
        p2.add(addCourseButton, BorderLayout.SOUTH);
        tabbedPane.addTab("Courses", p2);

        /*Student Activities Tab*/
        int numActs = a.size();
        JPanel actTablePanel = new JPanel(new GridLayout((numActs + 2), 2));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        actPanels = new ArrayList<JTextField>();
        actPanels.add(new JTextField("Activity Title"));
        actPanels.add(new JTextField("Activity Description"));
        Iterator<Activity> ai = a.iterator();
        int j = 0;
        while (ai.hasNext()) {
            Activity current = ai.next();
            actPanels.add(new JTextField(current.getActivityName()));
            actPanels.add(new JTextField(current.getActivityDescri()));
        }
        actPanels.add(new JTextField("Add Activity Title Here"));
        actPanels.add(new JTextField("Add Activity Description Here"));

        Iterator<JTextField> tfi2 = actPanels.iterator();
        while (tfi2.hasNext()) {
            actTablePanel.add(tfi2.next());
        }
        addActButton.addActionListener(new addActListener());
        buttonPanel.add(addActButton);
        updateActButton.addActionListener(new updateActListener());
        buttonPanel.add(updateActButton);
        deleteActButton.addActionListener(new deleteActListener());
        buttonPanel.add(deleteActButton);
        p3.add(actTablePanel, BorderLayout.CENTER);
        p3.add(buttonPanel, BorderLayout.SOUTH);
        tabbedPane.addTab("Activities", p3);

        /*Geneerate Tab*/
        generateButton.addActionListener(new generateListener());
        p4.add(generateButton, BorderLayout.CENTER);
        tabbedPane.addTab("Generate", p4);

        frame.setContentPane(tabbedPane);
        int frameWidth = 1200;
        int frameHeight = 700;
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //frame.setBounds((int) screenSize.getWidth() - frameWidth, 0, frameWidth, frameHeight);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    class updateStudListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                s.setUsername(nameField.getText());
                s.setAge(Integer.parseInt(ageField.getText()));
                s.setGraduateYear(Integer.parseInt(yearField.getText()));
                s.setDescription(descriptField.getText());

                System.out.println("Name is: " + s.getUsername());
                con.sendMsg(12, s);
            } catch (IOException ex) {
                //Add error message
            } catch (ClassNotFoundException ex) {
                //Add error message
            }
            frame.revalidate();
            frame.repaint();

        }
    }

    class addCourseListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                Course newCourse = new Course((panels.get(panels.size() - 2)).getText(), (panels.get(panels.size() - 1)).getText());
                c.add(newCourse);
                System.out.println("Course name is: " + c.get(c.size() - 1).getCourseName());
                con.sendMsg(3, newCourse);
            } catch (IOException ex) {
                //Add error message
            } catch (ClassNotFoundException ex) {
                //Add error message
            }
            frame.revalidate();
            frame.repaint();

        }
    }

    class addActListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                Activity newActivity = new Activity((actPanels.get(actPanels.size() - 2)).getText(), (actPanels.get(actPanels.size() - 1)).getText());
                a.add(newActivity);
                System.out.println("Activity name is: " + a.get(a.size() - 1).getActivityDescri());
                con.sendMsg(51, newActivity);
            } catch (IOException ex) {
                //Add error message
            } catch (ClassNotFoundException ex) {
                //Add error message
            }
            frame.revalidate();
            frame.repaint();

        }
    }

    class updateActListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                Activity newActivity = a.get(3);
//                int k = 0;
//                for (int i = 0; i < ((a.size() - 1) * 2); i++) {
//                    a.get(k).setActivityName(actPanels.get(i).getText());
//                    a.get(k).setActivityDescri(actPanels.get(++i).getText());
//                    k = k + 1;
//                }

                con.sendMsg(52, newActivity);
                con.sendMsg(51, newActivity);
            } catch (IOException ex) {
                //Add error message
            } catch (ClassNotFoundException ex) {
                //Add error message
            }
            frame.revalidate();
            frame.repaint();

        }
    }

    class deleteActListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            // try {

            /* con.sendMsg(52, a);
            } catch (IOException ex) {
               //Add error message
            } catch (ClassNotFoundException ex) {
                //Add error message
            }*/
            frame.revalidate();
            frame.repaint();

        }
    }

    class generateListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                con.sendMsg(52, s);
            } catch (IOException ex) {
                //Add error message
            } catch (ClassNotFoundException ex) {
                //Add error message
            }
            frame.revalidate();
            frame.repaint();

        }
    }
}
