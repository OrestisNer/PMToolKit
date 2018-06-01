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
	protected HashMap<Project,HashMap<Task,Boolean>> tasks;
	
	public User(String username, String password, String firstname, String lastname, double salary, String specialty){
		this.username=username;
		this.password=password;
		this.firstname= firstname;
		this.lastname=lastname;
		this.salary=salary;
		this.specialty=specialty;
		messages= new ArrayList<Message>();
		projects= new ArrayList<String>();
		tasks= new HashMap<Project,HashMap<Task,Boolean>>();
		
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
			tasks.get(project).put(task, false);
		}catch(NullPointerException e){
			HashMap<Task, Boolean> taskList = new HashMap<Task,Boolean>();
			taskList.put(task, false);
			tasks.put(project, taskList);
		}
	}
	
	public HashMap<Task,Boolean> getTasks(Project project){
		
		return tasks.get(project);
	}
	
	public ArrayList<Task> getUnfinishedTasks(Project project){
		ArrayList<Task> unfinishedTask= new ArrayList<Task>();
		HashMap<Task,Boolean> employeeTasks;
		try{
			employeeTasks=tasks.get(project);
			for (Entry<Task, Boolean> entry : employeeTasks.entrySet()) {
			    Task task = entry.getKey();
			    Boolean finished = entry.getValue();
			    if(!finished){
			    	unfinishedTask.add(task);
			    }
			}
		}catch(NullPointerException e){
			//do nothing
		}
		
		if(unfinishedTask.isEmpty())
			System.out.println("Empty list");
		else {
			for(Task t: unfinishedTask)
				System.out.println(t.getName());
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
