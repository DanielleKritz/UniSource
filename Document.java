/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.un.pojo;

import java.io.Serializable;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author jabigelow
 */
public class Document implements Serializable {
    
    
    private static final long serialVersionUID = 1L;
	
	private PDDocument doc;
	
	public Document(){}
	
	public Document(PDDocument doc)
	{
		this.doc = doc;
	}

	/** 
	* @return activityName 
	*/
	public PDDocument getDoc() {
		return doc;
	}


	/**
	 * @param activityName the activityName to set
	 */
	public void setActivityName(PDDocument doc) {
		this.doc = doc;
	}



    
}
