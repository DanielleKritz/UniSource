/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniclient;

import com.un.pojo.Activity;
import com.un.pojo.Course;
import com.un.pojo.Message;
import com.un.pojo.Student;
import com.un.pojo.User;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class UniSourceClientServerConnect {

    private Socket connection; // socket to server
    private static final int UNI_SERVER_PORT = 6666;
    private boolean connected = false; // used to close connection

    private ObjectOutputStream toUniServer; // reading and writing to socket
    private ObjectInputStream fromUniServer;

    private final int CLOSE = -2;
    private final int ERROR = -1;
    private final int LOGIN = 0;
    private final int REGISTER = 1;
    private final int UPDATE_PROF = 12;
    private final int GET_COURSES = 2;
    private final int ADD_COURSE = 3;
    private final int GET_ACT = 4;
    private final int ADD_ACT = 51;
    private final int DELETE_ACT = 52;
    private final int REQUEST_TRANS = 6;
    private final int GET_TRANS = 7;
    private final int GET_RESUM = 8;

    private int lstTaskCode;
    private User user;

    private Object data = null;

    public UniSourceClientServerConnect(User u) throws IOException, ClassNotFoundException {
        user = u;
        try {
            connection = new Socket("127.0.0.1", UNI_SERVER_PORT);
        } catch (UnknownHostException e) {
            System.out.println("Error setting up socket, connection unknown: " + e);
        } catch (IOException e) {
            System.out.println("Error setting up socket connection: " + e);
        }
        connected = true;
        toUniServer = new ObjectOutputStream(connection.getOutputStream());
        fromUniServer = new ObjectInputStream(new BufferedInputStream(connection.getInputStream()));

        sendMsg(LOGIN, user);
        System.out.println("Login msg sent");
    }
    
    public UniSourceClientServerConnect(String email) throws IOException,  ClassNotFoundException {
        try {
            connection = new Socket("127.0.0.1", UNI_SERVER_PORT);
        } catch (UnknownHostException e) {
            System.out.println("Error setting up socket, connection unknown: " + e);
        } catch (IOException e) {
            System.out.println("Error setting up socket connection: " + e);
        }
        connected = true;
        toUniServer = new ObjectOutputStream(connection.getOutputStream());
        fromUniServer = new ObjectInputStream(new BufferedInputStream(connection.getInputStream()));

        sendMsg(REGISTER, email);
        System.out.println("Admin notified");
        connection.close();
    }
    
    public void makeDocument() throws IOException {
        System.out.println("Courses");
        try {
            sendMsg(2, user);
        } catch (IOException ex) {
            Logger.getLogger(UniSourceClientServerConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UniSourceClientServerConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Course> courses = (ArrayList<Course>) getObject();
        Iterator<Course> i = courses.iterator();

        try {
            sendMsg(4, user);
        } catch (IOException ex) {
            Logger.getLogger(UniSourceClientServerConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UniSourceClientServerConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Activity> activities = (ArrayList<Activity>) getObject();
        Iterator<Activity> a = activities.iterator();

        
        File file = new File("D:/Documents/NetBeansProjects/UniSourceClient/src/unisourceclient/pdfBox/my_doc.pdf");
        PDDocument document = PDDocument.load(file);

        //Creating a blank page 
        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        //Begin the Content stream 
        contentStream.beginText();

        //Setting the font to the Content stream  
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

        contentStream.setLeading(30);

        //Setting the position for the line 
        contentStream.newLineAtOffset(35, 700);

        String text = user.getUsername();

        //Adding text in the form of string 
        contentStream.showText(text);

        contentStream.newLine();

        text = user.getUserID();

        //Adding text in the form of string 
        contentStream.showText(text);

        contentStream.newLine();

        Student u = (Student) user;
        text = u.getDescription();

        //Adding text in the form of string 
        contentStream.showText(text);

        contentStream.newLine();

        contentStream.newLine();

        text = "Courses:";
        contentStream.showText(text);

        contentStream.newLine();

        while (i.hasNext()) {
            Course c = i.next();
            text = ("Course: " + c.getCourseCode() + " - " + c.getCourseName());
            contentStream.showText(text);

            contentStream.newLine();
        }
        
        contentStream.newLine();
        contentStream.newLine();
        
        text = "Activites:";
        contentStream.showText(text);
        contentStream.newLine();
        
        while (a.hasNext()) {
            Activity act = a.next();
            text = ("Activity: " + act.getActivityName() + " - " + act.getActivityDescri());
            contentStream.showText(text);

            contentStream.newLine();
        }

        //Ending the content stream
        contentStream.endText();

        System.out.println("Content added");

        //Closing the content stream
        contentStream.close();

        //Saving the document
        document.save("D:/Documents/NetBeansProjects/UniSourceClient/src/unisourceclient/pdfBox/my_doc.pdf");

        System.out.println("PDF created");

        //Closing the document  
        document.close();
        System.out.println("Should be updating");
        

    }

    public void sendMsg(int taskCode, Object o) throws IOException, ClassNotFoundException {
        if (taskCode == ADD_COURSE) {
            toUniServer.writeObject(new Message(31, user));
            toUniServer.flush();
            toUniServer.writeObject(new Message(32, o));
            lstTaskCode = 32;            
        } else if (taskCode == ADD_ACT) {
            toUniServer.writeObject(new Message(50, user));
            toUniServer.flush();
            toUniServer.writeObject(new Message(ADD_ACT, o));
            lstTaskCode = taskCode;
        } else if (taskCode == DELETE_ACT) {
            toUniServer.writeObject(new Message(50, user));
            toUniServer.flush();
            toUniServer.writeObject(new Message(DELETE_ACT, o));
            lstTaskCode = taskCode;
        } else if (taskCode == GET_RESUM) {
            makeDocument();
        } else {
            toUniServer.writeObject(new Message(taskCode, o));
            lstTaskCode = taskCode;
        }
        toUniServer.flush();

        if (taskCode != CLOSE) {
            Object reply = fromUniServer.readObject();
            data = readReply(reply);
        }
    }

    private Object readReply(Object reply) {
        Object o = null;
        if (reply != null) {
            Message m = (Message) reply;
            if (m.getTaskCode() == ERROR) {
                System.out.println("Error from server.");
            } else if (m.getTaskCode() != lstTaskCode) {
                System.out.println("Error in protocol.");
            } else {
                switch (m.getTaskCode()) {
                    case LOGIN:
                        o = (Student) m.getObject();
                        user = (Student) o;
                        break;
                    case UPDATE_PROF:
                        o = (Student) m.getObject();
                        user = (Student) o;
                        break;
                    case GET_COURSES:
                        o = (ArrayList<Course>) m.getObject();
                        break;
                    case 32:
                        o = (ArrayList<Course>) m.getObject();
                        break;
                    case GET_ACT:
                        o = (ArrayList<Activity>) m.getObject();
                        break;
                    case ADD_ACT:
                        o = (ArrayList<Activity>) m.getObject();
                        break;
                    case DELETE_ACT: 
                        o = (ArrayList<Activity>) m.getObject();
                        break;
                    case REQUEST_TRANS:
                        break;
                    case GET_TRANS:
                        o = m.getObject();
                        break;
                    case 9:
                        break;
                    default:
                        break;
                }
            }
        }
        return o;
    }

    public Object getObject() {
        return data;
    }
    
    public Socket getConnect() {
        return connection;
    }

    public void close() throws ClassNotFoundException {
        try {
            sendMsg(CLOSE, null);
            connected = false;
            toUniServer.close();
            fromUniServer.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error closing connection: " + e);
            connected = true;
        }
    }

}
