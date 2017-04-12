/**
 *  Copyright © 2017company name. All rights reserved.

 *

 * @Title: Student.java

 * @Prject: UniSource

 * @Package: com.unisource.pojo

 * @Description: TODO

 * @author: Dell  

 * @date: Apr 10, 2017 8:46:34 PM

 * @version: V1.0  
 */
package com.un.pojo;

/**
 * @ClassName: Student

 * @Description: TODO

 * @author: Dell

 * @date: Apr 10, 2017 8:46:34 PM

 */
public class Student extends User {

	
	private int graduateYear;
	private int age;
	private String description;

	//regular constructor
	public Student(){}

	/**
	 * @Title:Student
	
	 * @Description:TODO
	
	 * @param userID
	 * @param username
	 * @param password
	 * @param description
	 * @param roleID
	 */
	public Student(String userID, String username, String password, int roleID, int graduateYear, int age,
			String description) {
		super(userID, username, password, roleID);
		this.graduateYear = graduateYear;
		this.age = age;
		this.description = description;
	}

	/**
	 * @return the graduateYear
	 */
	public int getGraduateYear() {
		return graduateYear;
	}

	/**
	 * @param graduateYear the graduateYear to set
	 */
	public void setGraduateYear(int graduateYear) {
		this.graduateYear = graduateYear;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/** 
	* @return description 
	*/
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non Javadoc)
	
	 * @Title: toString
	
	 * @Description: TODO
	
	 * @return
	
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [userID=" + userID + ", username=" + username
				+ ", password=" + password + ", description=" + description
				+ ", roleID=" + roleID + ", graduateYear=" + graduateYear
				+ ", age=" + age + "]";
	}
	
}
