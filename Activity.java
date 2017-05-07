    /**   
    * @Title: Activity.java 
    * @Package com.un.pojo 
    * @Description: TODO 
    * @author Jie Lin: kimihiro.lin80@gmail.com 
    * @date May 1, 2017 1:04:34 PM 
    * @version V1.0   
    */  
package com.un.pojo;

import java.io.Serializable;

/** 
    * @ClassName: Activity 
    * @Description: TODO
    * @author Jie Lin: kimihiro.lin80@gmail.com
    * @date May 1, 2017 1:04:34 PM 
    *  
    */
public class Activity implements Serializable{

	    /** 
	    * @Fields serialVersionUID : TODO
	    */  
	private static final long serialVersionUID = 1L;
	
	private String activityName;
	private String activityDescri;
	
	public Activity(){}
	
	public Activity(String activityName, String activityDescri)
	{
		this.activityName = activityName;
		this.activityDescri = activityDescri;
	}

	/** 
	* @return activityName 
	*/
	public String getActivityName() {
		return activityName;
	}

	/** 
	* @return activityDescri 
	*/
	public String getActivityDescri() {
		return activityDescri;
	}

	/**
	 * @param activityName the activityName to set
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
	 * @param activityDescri the activityDescri to set
	 */
	public void setActivityDescri(String activityDescri) {
		this.activityDescri = activityDescri;
	}

	/* (non Javadoc) 
	* <p>Title: toString</p> 
	* <p>Description: </p> 
	* @return 
	* @see java.lang.Object#toString() 
	*/ 
	@Override
	public String toString() {
		return "Activity [activityName=" + activityName + ", activityDescri=" + activityDescri + "]";
	}
}

