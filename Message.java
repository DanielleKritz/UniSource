    /**   
    * @Title: Message.java 
    * @Package com.un.pojo 
    * @Description: TODO 
    * @author Jie Lin: kimihiro.lin80@gmail.com 
    * @date Apr 11, 2017 1:15:13 AM 
    * @version V1.0   
    */  
package com.un.pojo;


    /** 
    * @ClassName: Message 
    * @Description: This is the actual message will be sent back and forth between client and server
    * @author Jie Lin: kimihiro.lin80@gmail.com
    * @date Apr 11, 2017 1:15:13 AM 
    *  we don't not contain any setters here, since the object will be init with data
    */
public class Message {
	private int taskCode; //this determines which function it will go to. For example, if code == 1, it will go to user login function 
	private boolean flag; //false represents there is no data. true represents there contains data. 
	private Object object;
	/** 
	* <p>Title: Message</p> 
	* <p>Description: constructor</p> 
	* @param taskCode
	* @param flag
	* @param object 
	*/ 
	public Message(int taskCode, boolean flag, Object object) {
		super();
		this.taskCode = taskCode; // will be 0 for login
		this.flag = flag;
		this.object = object;
	}
	/** 
	* @return taskCode 
	*/
	public int getTaskCode() {
		return taskCode;
	}
	/** 
	* @return flag 
	*/
	public boolean isFlag() {
		return flag;
	}
	/** 
	* @return object 
	*/
	public Object getObject() {
		return object;
	}
}

