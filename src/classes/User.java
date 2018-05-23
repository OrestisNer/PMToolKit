package classes;

import java.util.ArrayList;

public class User {
	
	protected String username;
	protected String password;
	protected String firstname;
	protected String lastname;
	protected double salary;
	protected ArrayList<Message> messages;	
	
	public User(String username, String password, String firstname, String lastname, double salary){
		this.username=username;
		this.password=password;
		this.firstname= firstname;
		this.lastname=lastname;
		this.salary=salary;
		messages= new ArrayList<Message>();
		
	}
	
	public void addMessage(Message message){
		messages.add(message);
	}
	
}
