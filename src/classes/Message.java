package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {
	
	private User sender;
	private ArrayList<User> receivers;
	private String subject;
	private String message;
	private boolean read;
	
	
	public Message(User sender, ArrayList<User> receivers, String subject, String message){
		this.sender=sender;
		this.receivers=receivers;
		this.subject=subject;
		this.message=message;
		read=false;
	}
	
	
	public boolean isSender(User employee){
		if(employee.getUsername().equalsIgnoreCase(sender.getUsername())){
			return true;
		}
		return false;
	}
	
	public User getSender(){
		return sender;
	}
	public ArrayList<User> getReceivers(){
		return receivers;
	}
	
	public String getSubject(){
		return subject;
	}
	
	public String getMessage(){
		return message;
	}
	
	public int getNumberOfReceivers(){
		return receivers.size();
	}

}
