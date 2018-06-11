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
	protected ArrayList<String> projects;
	protected String specialty;
	protected HashMap<String,HashMap<String,Boolean>> tasks;
	
	public User(String username, String password, String firstname, String lastname, double salary, String specialty){
		this.username=username;
		this.password=password;
		this.firstname= firstname;
		this.lastname=lastname;
		this.salary=salary;
		this.specialty=specialty;
		messages= new ArrayList<Message>();
		projects= new ArrayList<String>();
		tasks= new HashMap<String,HashMap<String,Boolean>>();
		
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
	
	public void addTask(Project project,Task task){
		
		try{
			tasks.get(project.getName()).put(task.getId(), false);
		}catch(NullPointerException e){
			HashMap<String, Boolean> taskList = new HashMap<String,Boolean>();
			taskList.put(task.getId(), false);
			tasks.put(project.getName(), taskList);
			System.out.println("new HashMap<Task,Boolean> taskList ");
		}
	}
	
	public HashMap<String,Boolean> getTasks(Project project){
		
		return tasks.get(project);
	}
	
	public ArrayList<String> getUnfinishedTasks(Project project){
		ArrayList<String> unfinishedTask= new ArrayList<String>();
		HashMap<String,Boolean> employeeTasks;
		try{
			employeeTasks=tasks.get(project.getName());
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
	
	
	
}
