    /**   
    * @Title: Course.java 
    * @Package com.un.pojo 
    * @Description: TODO 
    * @author Jie Lin: kimihiro.lin80@gmail.com 
    * @date May 1, 2017 1:01:11 PM 
    * @version V1.0   
    */  
package com.un.pojo;

import java.io.Serializable;

/** 
    * @ClassName: Course 
    * @Description: TODO
    * @author Jie Lin: kimihiro.lin80@gmail.com
    * @date May 1, 2017 1:01:11 PM 
    *  
    */
public class Course implements Serializable {

	    /** 
	    * @Fields serialVersionUID : TODO
	    */  
	private static final long serialVersionUID = 1L;
	
	private String courseCode;
	private String courseName;
	
	public Course(){}
	
	public Course(String courseCode, String courseName)
	{
		this.courseCode = courseCode;
		this.courseName = courseName;
	}

	/** 
	* @return courseCode 
	*/
	public String getCourseCode() {
		return courseCode;
	}

	/** 
	* @return courseName 
	*/
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseCode the courseCode to set
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/* (non Javadoc)
	
	 * @Title: toString
	
	 * @Description: TODO
	
	 * @return
	
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Course [courseCode=" + courseCode + ", courseName="
				+ courseName + "]";
	}
}

