/**
 *  Copyright © 2017company name. All rights reserved.

 *

 * @Title: Role.java

 * @Prject: UniSource

 * @Package: com.unisource.pojo

 * @Description: TODO

 * @author: Dell  

 * @date: Apr 10, 2017 3:41:42 PM

 * @version: V1.0  
 */
package com.un.pojo;

/**
 * @ClassName: Role

 * @Description: TODO

 * @author: Jie Lin

 * @date: Apr 10, 2017 3:41:42 PM

 */
public class Role {
	private int ID;
	private String roleName;
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/* (non Javadoc)
	
	 * @Title: toString
	
	 * @Description: TODO
	
	 * @return
	
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role [ID=" + ID + ", roleName=" + roleName + "]";
	}
	
	
}
