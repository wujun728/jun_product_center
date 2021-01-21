/**
 * @author	Robert Schoenthal
 * @date		29.08.2007
 * @license	gpl
 */
package com.sz.message.vo;

/**
 * @class net.SmackAdapter.JameMessage
 * @description stores a simple message object
 */
public class JameMessage{
	
	// private attributes
	private String messageBody;
	private String messageSubject;
	private String messageFrom;
	private String messageTo;
	
	 // default construtor
	public JameMessage() {
		this("","","","");
	}
	// enhanced constructor, sets the attributes instant
	public JameMessage(String messageBody, String messageSubject, String messageFrom, String messageTo) {
		this.messageBody=new String(messageBody);
		if(messageSubject != null)
			this.messageSubject=new String(messageSubject);
		this.messageFrom=new String(messageFrom);
		this.messageTo=new String(messageTo);
	}
	
	//setters
	public void setMessageBody(String messageBody){
		this.messageBody=messageBody;
	}
	public void setMessageSubject(String messageSubject){
		this.messageSubject=messageSubject;
	}
	public void setMessageFrom(String messageFrom){
		this.messageFrom=messageFrom;
	}	
	public void setMessageTo(String messageTo){
		this.messageTo=messageTo;
	}
	
	//getters
	public String getMessageBody() {
		return this.messageBody;
	}
	public String getMessageSubject() {
		return this.messageSubject;
	}
	public String getMessageFrom() {
		return this.messageFrom;
	}
	public String getMessageTo() {
		return this.messageTo;
	}
	
	/** 
	 * @override toString() Method so the object is more readable
	 */
	public String toString(){
		return this.messageFrom+" send "+this.messageBody;
	}
}
