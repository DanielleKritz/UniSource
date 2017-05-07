/**   
 * @Title: Client.java 
 * @Package com.un.main 
 * @Description: TODO 
 * @author Jie Lin: kimihiro.lin80@gmail.com 
 * @date Apr 11, 2017 3:13:18 PM 
 * @version V1.0   
 */  
package com.un.main;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import com.un.pojo.Activity;
import com.un.pojo.Course;
import com.un.pojo.Message;
import com.un.pojo.Student;
import com.un.pojo.User;

/** 
 * @ClassName: Client 
 * @Description: TODO
 * @author Jie Lin: kimihiro.lin80@gmail.com
 * @date Apr 11, 2017 3:13:18 PM 
 *  
 */
public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 6666);
			//time out for 60s
			//socket.setSoTimeout(60000);
			
			//send object to client
			ObjectOutputStream objectWriter= new ObjectOutputStream(socket.getOutputStream());
			//get object from client
			ObjectInputStream objectReader = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			
			System.out.println("Get the connection");
			
			//test the correct match
			User u = new User();
			u.setPassword("a1234567890");
			u.setUserID("jabigelow@ursinus.edu");
			u.setRoleID(1);
			u.setUsername(null);
			
			objectWriter.writeObject(new Message(0,u));
			objectWriter.flush();//send object to server
			
			System.out.println("object has sent to server");
			
			Object obj = objectReader.readObject();
			if(obj!=null){
 				Message m = (Message) obj;
 				if(m.getTaskCode()==-1){
 					System.out.println("something goes wrong");
 				}else{
 					Student s = (Student) m.getObject();
 					System.out.println(s.toString());
 				}
			}
			
			//test for null user
			objectWriter.writeObject(new Message(0,new User()));
			objectWriter.flush();//send object to server
			
			System.out.println("object has sent to server");
			
			obj = objectReader.readObject();
			if(obj!=null){
 				Message m = (Message) obj;
 				if(m.getTaskCode()==-1){
 					System.out.println("something goes wrong");
 				}else{
 					Student s = (Student) m.getObject();
 					System.out.println(s.toString());
 				}
			}
			
			
			//test for unmatched userId and password
			objectWriter.writeObject(new Message(0,new User("jabigelow@ursinus.edu",null,"1561654564654",1)));
			objectWriter.flush();//send object to server
			
			System.out.println("object has sent to server");
			
			obj = objectReader.readObject();
			if(obj!=null){
 				Message m = (Message) obj;
 				if(m.getTaskCode()==-1){
 					System.out.println("something goes wrong");
 				}else{
 					Student s = (Student) m.getObject();
 					System.out.println(s.toString());
 				}
			}
			
			/** 
			 * 
			 * 
			 * 
			 * 
			 */
			//test new function for requestRegister
			//the only thing we are asking is the email address
			/*objectWriter.writeObject(new Message(1, "jilin@ursinus.edu"));
			objectWriter.flush();
			System.out.println("object has sent to server to test register process");
			
			obj = objectReader.readObject();
			if(obj!=null){
 				Message m = (Message) obj;
 				if(m.getTaskCode()==-1){
 					System.out.println("something goes wrong");
 				}else{
 					String str = (String) m.getObject();
 					System.out.println(str);
 				}
			}*/
			
			//When the user logs in, the client end should keep the userId and roleId while the user continue doing some actions
			/** 
			 * 
			 * 
			 * 
			 * 
			 */
			//to test if user can get his course list
			objectWriter.writeObject(new Message(2, new Student("jabigelow@ursinus.edu", null, null, 0, 0, 0, null)));
			objectWriter.flush();
			System.out.println("object has sent to server to test get course list process");
			
			obj = objectReader.readObject();
			if(obj!=null){
 				Message m = (Message) obj;
 				if(m.getTaskCode()==-1){
 					System.out.println("something goes wrong");
 				}else{
 					ArrayList<Course> ac = (ArrayList<Course>) m.getObject();
 					Iterator<Course> i = ac.iterator();
 					while(i.hasNext()){
 						System.out.println(i.next());
 					}
 				}
			}
			
			
			/** 
			 * 
			 * 
			 * 
			 * 
			 */
			//since the student client should not have the permission to delete a course from his course list. The only thing he can do is adding a new course to the list.
			objectWriter.writeObject(new Message(31, new Student("jabigelow@ursinus.edu", null, null, 0, 0, 0, null)));
			objectWriter.flush();
			
			System.out.println("let server to record user's id");
			
			objectWriter.writeObject(new Message(32, new Course("CS-170","Basic Introduction of programming")));
			objectWriter.flush();
			
			System.out.println("sending the update courses");
			
			obj = objectReader.readObject();
			if(obj!=null){
 				Message m = (Message) obj;
 				if(m.getTaskCode()==-1){
 					System.out.println("something goes wrong");
 				}else{
 					ArrayList<Course> ac = (ArrayList<Course>) m.getObject();
 					Iterator<Course> i = ac.iterator();
 					while(i.hasNext()){
 						System.out.println(i.next());
 					}
 				}
			}
			
			/** 
			 * 
			 * 
			 * 
			 * 
			 */
			//test for user request activities 
			//to test if user can get his course list
			objectWriter.writeObject(new Message(4, new Student("jabigelow@ursinus.edu", null, null, 0, 0, 0, null)));
			objectWriter.flush();
			System.out.println("object has sent to server to test get activity list process");
			
			obj = objectReader.readObject();
			if(obj!=null){
 				Message m = (Message) obj;
 				if(m.getTaskCode()==-1){
 					System.out.println("something goes wrong");
 				}else{
 					ArrayList<Activity> ac = (ArrayList<Activity>) m.getObject();
 					Iterator<Activity> i = ac.iterator();
 					while(i.hasNext()){
 						System.out.println(i.next());
 					}
 				}
			}
			
			/** 
			 * 
			 * 
			 * 
			 * 
			 */
			/*//test for user adds an activity
			//to test if user can adds an activity
			objectWriter.writeObject(new Message(50, new Student("jabigelow@ursinus.edu", null, null, 0, 0, 0, null)));
			objectWriter.flush();
			System.out.println("object has sent to server to let server to record the user id");
			
			//add a new activity
			objectWriter.writeObject(new Message(51, new Activity("Intership2","A winter intership")));
			objectWriter.flush();
			System.out.println("object has sent to server to let server to test if it can add a new activity");
			
			obj = objectReader.readObject();
			if(obj!=null){
 				Message m = (Message) obj;
 				if(m.getTaskCode()==-1){
 					System.out.println("something goes wrong");
 				}else{
 					ArrayList<Activity> ac = (ArrayList<Activity>) m.getObject();
 					Iterator<Activity> i = ac.iterator();
 					while(i.hasNext()){
 						System.out.println(i.next());
 					}
 				}
			}*/
			
			/** 
			 * 
			 * 
			 * 
			 * 
			 */
			/*//test for user deltes an activity
			//to test if user can delete an activity
			objectWriter.writeObject(new Message(50, new Student("jabigelow@ursinus.edu", null, null, 0, 0, 0, null)));
			objectWriter.flush();
			System.out.println("object has sent to server to let server to record the user id");
			
			//delete an activity
			objectWriter.writeObject(new Message(52, new Activity("Intership2","A winter intership")));
			objectWriter.flush();
			System.out.println("object has sent to server to let server to test if it can delete an existed activity");
			
			obj = objectReader.readObject();
			if(obj!=null){
 				Message m = (Message) obj;
 				if(m.getTaskCode()==-1){
 					System.out.println("something goes wrong");
 				}else{
 					ArrayList<Activity> ac = (ArrayList<Activity>) m.getObject();
 					Iterator<Activity> i = ac.iterator();
 					while(i.hasNext()){
 						System.out.println(i.next());
 					}
 				}
			}*/
			
			
			/*//test for user updates an activity
			//to test if user can update an activity
			objectWriter.writeObject(new Message(50, new Student("jabigelow@ursinus.edu", null, null, 0, 0, 0, null)));
			objectWriter.flush();
			System.out.println("object has sent to server to let server to record the user id");
			
			//update an activity
			objectWriter.writeObject(new Message(53, new Activity("Intership2","A winter intership (change)")));
			objectWriter.flush();
			System.out.println("object has sent to server to let server to test if it can update an existed activity");
			
			obj = objectReader.readObject();
			if(obj!=null){
 				Message m = (Message) obj;
 				if(m.getTaskCode()==-1){
 					System.out.println("something goes wrong");
 				}else{
 					ArrayList<Activity> ac = (ArrayList<Activity>) m.getObject();
 					Iterator<Activity> i = ac.iterator();
 					while(i.hasNext()){
 						System.out.println(i.next());
 					}
 				}
			}*/
			
			
			//update an profile
			objectWriter.writeObject(new Message(12, new Student("dakritz@ursinus.edu","Danielle", "a9876543210", 1, 2017, 21, "A senior student")));
			objectWriter.flush();
			System.out.println("object has sent to server to let server to test if it can update an existed profile");
			
			obj = objectReader.readObject();
			if(obj!=null){
 				Message m = (Message) obj;
 				if(m.getTaskCode()==-1){
 					System.out.println("something goes wrong");
 				}else{
 					Student s =(Student) m.getObject();
 					System.out.println(s);
 				}
			}
			
			
			//shutdown the connection from the client
			objectWriter.writeObject(new Message(-2,null));
			objectWriter.flush();//send object to server
			
			//shutdown the connection
			objectReader.close();
			objectWriter.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}
}

