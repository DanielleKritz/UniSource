/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisourceclient;

import com.un.pojo.Activity;
import com.un.pojo.Course;
import com.un.pojo.Message;
import com.un.pojo.Student;
import com.un.pojo.User;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author jabigelow
 */
public class UniSourceClient {

    UniClientServerConnect connection;

    /**
     * @param args the command line arguments
     */
    public UniSourceClient() throws IOException, ClassNotFoundException {
        User u = new User();
        u.setPassword("a1234567890");
        u.setUserID("jabigelow@ursinus.edu");
        u.setRoleID(1);
        u.setUsername(null);
        connection = new UniClientServerConnect(u);

        System.out.println("Login");

        Student m = (Student) connection.getObject();
        System.out.println(m.toString());
        System.out.println(m);

        connection.sendMsg(8, m);

        System.out.println("We requested to download resume");
        
        int FILE_SIZE = 6022386; // file size temporary hard coded
        // should bigger than the file to be downloaded
        String FILE_TO_RECEIVED = "D:/Documents/NetBeansProjects/UniSourceClient/src/unisourceclient/pdfBox/my_resume.pdf";

        
        
        byte[] mybytearray = (byte[]) connection.getObject();
        
        System.out.println("Converted to byte array");
        System.out.println(mybytearray);
        
        int bytesRead;
        int current = 0;

        InputStream is = connection.getConnect().getInputStream();
        System.out.println("got here");
        FileOutputStream fos = new FileOutputStream(FILE_TO_RECEIVED);
        System.out.println("got here 1");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        System.out.println("got here 2");
        System.out.println(mybytearray.length);
        bytesRead = is.read(mybytearray, 0, mybytearray.length);
        System.out.println(bytesRead);
        System.out.println("got here 3");
        current = bytesRead;
        System.out.println("got here 4");
        do {
            System.out.println("got here 5");
            System.out.println(mybytearray.length);
            System.out.println(current);
            bytesRead = is.read(mybytearray, current, (mybytearray.length - current));
            System.out.println(bytesRead);
            System.out.println("got here 6");
            if (bytesRead >= 0) {
                current += bytesRead;
            }
            System.out.println("Am i stuck in loop");
        } while (bytesRead > -1);

        bos.write(mybytearray, 0, current);
        bos.flush();
        System.out.println("File " + FILE_TO_RECEIVED + " downloaded (" + current + " bytes read)");

        if (fos != null) {
            fos.close();
        }
        if (bos != null) {
            bos.close();
        }

//        System.out.println("Courses");
//        connection.sendMsg(2, m);
//        ArrayList<Course> courses = (ArrayList<Course>) connection.getObject();
//        Iterator<Course> i = courses.iterator();
//
//        connection.sendMsg(4, m);
//        ArrayList<Activity> activities = (ArrayList<Activity>) connection.getObject();
//        Iterator<Activity> a = activities.iterator();
//
//        
//        File file = new File("D:/Documents/NetBeansProjects/UniSourceClient/src/unisourceclient/pdfBox/my_doc.pdf");
//        PDDocument document = PDDocument.load(file);
//
//        //Creating a blank page 
//        PDPage page = document.getPage(0);
//        PDPageContentStream contentStream = new PDPageContentStream(document, page);
//
//        //Begin the Content stream 
//        contentStream.beginText();
//
//        //Setting the font to the Content stream  
//        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
//
//        contentStream.setLeading(30);
//
//        //Setting the position for the line 
//        contentStream.newLineAtOffset(35, 700);
//
//        String text = m.getUsername();
//
//        //Adding text in the form of string 
//        contentStream.showText(text);
//
//        contentStream.newLine();
//
//        text = m.getUserID();
//
//        //Adding text in the form of string 
//        contentStream.showText(text);
//
//        contentStream.newLine();
//
//        text = m.getDescription();
//
//        //Adding text in the form of string 
//        contentStream.showText(text);
//
//        contentStream.newLine();
//
//        contentStream.newLine();
//
//        text = "Courses:";
//        contentStream.showText(text);
//
//        contentStream.newLine();
//
//        while (i.hasNext()) {
//            Course c = i.next();
//            text = ("Course: " + c.getCourseCode() + " - " + c.getCourseName());
//            contentStream.showText(text);
//
//            contentStream.newLine();
//        }
//        
//        contentStream.newLine();
//        contentStream.newLine();
//        
//        text = "Activites:";
//        contentStream.showText(text);
//        contentStream.newLine();
//        
//        while (a.hasNext()) {
//            Activity act = a.next();
//            text = ("Activity: " + act.getActivityName() + " - " + act.getActivityDescri());
//            contentStream.showText(text);
//
//            contentStream.newLine();
//        }
//
//        //Ending the content stream
//        contentStream.endText();
//
//        System.out.println("Content added");
//
//        //Closing the content stream
//        contentStream.close();
//
//        //Saving the document
//        document.save("D:/Documents/NetBeansProjects/UniSourceClient/src/unisourceclient/pdfBox/my_doc.pdf");
//
//        System.out.println("PDF created");
//
//        //Closing the document  
//        document.close();
//        System.out.println("Should be updating");
//        
//        Student me = m;
//        me.setDescription("A double major in Computer Science and Physics at Ursinus College");
//        me.setAge(22);
//        me.setGraduateYear(2017);
//        
//        System.out.println(me.toString());
//        
//        connection.sendMsg(12, me);
//        Student s = (Student) connection.getObject();
//        System.out.println(s.toString());
//        
//
//        
//        System.out.println("Courses");
//        connection.sendMsg(2, m);
//        ArrayList<Course> courses = (ArrayList<Course>) connection.getObject();
//        Iterator<Course> i = courses.iterator();
//        while (i.hasNext()) {
//            System.out.println(i.next());
//        }
//
////        Course c = new Course("MATH-235", "Discrete Mathematics");
////        connection.sendMsg(3, c);
////        ArrayList<Course> newCourses = (ArrayList<Course>) connection.getObject();
////        Iterator<Course> h = newCourses.iterator();
////        while (h.hasNext()) {
////            System.out.println(h.next());
////        }
//        
//        System.out.println("Activities");
//
//        connection.sendMsg(4, m);
//        ArrayList<Activity> activities = (ArrayList<Activity>) connection.getObject();
//        Iterator<Activity> a = activities.iterator();
//        while (a.hasNext()) {
//            System.out.println(a.next());
//        }
//        
//        System.out.println("");
//        System.out.println("Adding activity");
//         
//        Activity act = new Activity("Member of SPS", "Ursinus College local SPS chapter");
//        connection.sendMsg(51, act);
//        ArrayList<Activity> newActivities = (ArrayList<Activity>) connection.getObject();
//        Iterator<Activity> ac = newActivities.iterator();
//        while (ac.hasNext()) {
//            System.out.println(ac.next());
//        }   
        connection.close();

        System.out.println("Trying to register");

//        String r = "dakritz@ursinus.edu";
//        connection = new UniClientServerConnect(r);
//        System.out.println("Connection Closed");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UniSourceClient UniClient = new UniSourceClient();

    }

}
