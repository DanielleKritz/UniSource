/**
 *  Copyright © 2017company name. All rights reserved.

 *

 * @Title: User.java

 * @Prject: UniSource

 * @Package: com.unisource.pojo

 * @Description: TODO

 * @author: Dell  

 * @date: Apr 10, 2017 3:38:16 PM

 * @version: V1.0  
 */
package com.un.pojo;

import java.io.Serializable;


/**
 * @ClassName: User

 * @Description: TODO

 * @author: Jie Lin

 * @date: Apr 10, 2017 3:38:16 PM

 */
public class User implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	
	 * @fieldType: long
	
	 * @Description: TODO
	 */
	protected static final long serialVersionUID = 1L;
	/**
	 * @fieldName: serialVersionUID
	
	 * @fieldType: long
	
	 * @Description: TODO
	 */
	protected String userID;//email will be used
	protected String username;//the actual name will be used
	protected String password;
	protected int roleID;
	
	//regular constructor
	public User(){}

	/**
	 * @Title:User
	
	 * @Description:TODO
	
	 * @param userID
	 * @param username
	 * @param password
	 * @param roleID
	 */
	public User(String userID, String username, String password,
			int roleID) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.roleID = roleID;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the roleID
	 */
	public int getRoleID() {
		return roleID;
	}

	/**
	 * @param roleID the roleID to set
	 */
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	/* (non Javadoc)
	
	 * @Title: toString
	
	 * @Description: TODO
	
	 * @return
	
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username
				+ ", password=" + password + ", roleID=" + roleID + "]";
	}
	
	
}
