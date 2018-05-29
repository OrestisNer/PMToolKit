package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public abstract class User implements Serializable {
	
	protected String username;
	protected String password;
	protected String firstname;
	protected String lastname;
	protected double salary;
	protected ArrayList<Message> messages;	
	protected ArrayList<Project> projects;
	protected String speciality;
	protected HashMap<Task,Boolean> tasks;
	
	public User(String username, String password, String firstname, String lastname, double salary, String speciality){
		this.username=username;
		this.password=password;
		this.firstname= firstname;
		this.lastname=lastname;
		this.salary=salary;
		this.speciality=speciality;
		messages= new ArrayList<Message>();
		projects= new ArrayList<Project>();
		tasks= new HashMap<Task,Boolean>();
		
	}
	
	public void addMessage(Message message){
		messages.add(message);
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void addProject(Project project){
		projects.add(project);
	}
	
	public ArrayList<Project> getProjects(){
		return projects;
	}
	
	public String getName(){
		return firstname+" "+lastname;
	}
	
	public String getSpeciality(){
		return speciality;
	}
	
	public void addTask(Task task){
		tasks.put(task, false);
	}
	
	public HashMap<Task,Boolean> getTasks(){
		return tasks;
	}
	
	public ArrayList<Task> getUnfinishedTasks(){
		ArrayList<Task> unfinishedTask= new ArrayList<Task>();
		for (Entry<Task, Boolean> entry : tasks.entrySet()) {
		    Task task = entry.getKey();
		    Boolean finished = entry.getValue();
		    if(!finished){
		    	unfinishedTask.add(task);
		    }
		}
		return unfinishedTask;
	}
	
	public ArrayList<Message> getMessages(){
		return messages;
	}
	
	
}
