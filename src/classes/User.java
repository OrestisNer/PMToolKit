package classes;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import application.AlertUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class User implements Serializable {
	
	protected String username;
	protected String password;
	protected String firstname;
	protected String lastname;
	protected String fullname;
	protected double salary;
	protected ArrayList<Message> messages;	
	protected ArrayList<String> projects;
	protected String specialty;
	protected HashMap<String,HashMap<String,Boolean>> activities;
	
	public User(String username, String password, String firstname, String lastname, double salary, String specialty){
		this.username=username;
		this.password=password;
		this.firstname= firstname;
		this.lastname=lastname;
		this.fullname = firstname +" "+lastname;
		this.salary=salary;
		this.specialty=specialty;
		messages= new ArrayList<Message>();
		projects= new ArrayList<String>();
		activities= new HashMap<String,HashMap<String,Boolean>>();
		
	}
	
	public String getFullname() {
		return fullname;
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
	
	public void addProject(String projectName){
		projects.add(projectName);
	}
	
	public ArrayList<String> getProjects(){
		return projects;
	}
	
	public String getName(){
		return firstname+" "+lastname;
	}
	
	public String getSpecialty(){
		return specialty;
	}
	
	public void confirmActivity(Project project, Activity act) throws IOException {
		activities.get(project.getName()).put(act.getId(), true);
	}
	
	public void addActivity(Project project,Activity act){
		try{
			activities.get(project.getName()).put(act.getId(), false);
		}catch(NullPointerException e){
			HashMap<String, Boolean> taskList = new HashMap<String,Boolean>();
			taskList.put(act.getId(), false);
			activities.put(project.getName(), taskList);
			System.out.println("new HashMap<Task,Boolean> taskList ");
		}
	}
	
/*	public HashMap<String,Boolean> getActivities(Project project){
		try{
			return activities.get(project.getName());
		}catch(NullPointerException e){
			HashMap<String,Boolean> activityList = new HashMap<String,Boolean>();
			return activityList;
		}
	}*/
	
	public int getNumberOfTasks(Project project){
		try{
			return activities.get(project.getName()).keySet().size();
		}catch(NullPointerException e){
			return 0;
		}
	}
	
	public ArrayList<String> getUnfinishedActivities(Project project){
		ArrayList<String> unfinishedTask= new ArrayList<String>();
		HashMap<String,Boolean> employeeTasks;
		try{
			employeeTasks=activities.get(project.getName());
			for (Entry<String, Boolean> entry : employeeTasks.entrySet()) {
			    String taskID = entry.getKey();
			    Boolean finished = entry.getValue();
			    if(!finished){
			    	unfinishedTask.add(taskID);
			    }
			}
		}catch(NullPointerException e){
			System.out.println("getUnfinishedTasks problem.");
		}
		
		return unfinishedTask;
	}
	
	public ArrayList<Message> getMessages(){
		return messages;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	
	public double getSalary(){
		return salary;
	}
	
	public String getFirstname(){
		return firstname;
	}
	
	public String getLastname(){
		return lastname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
}
