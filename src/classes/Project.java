package classes;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Project implements Serializable{
	
	private User projectmanager;
	private String name;
	private ArrayList<Task> tasks;
	private ArrayList<User> employees;
	private ArrayList<Diagram> diagrams;
	private LocalDate startingDate;
	private Calendar calendar;
	private Cost cost;
	
	public Project(String name, User employee){
		this.name=name;
		this.projectmanager=employee;
		this.startingDate= LocalDate.now();
		tasks= new ArrayList<Task>();
		employees= new ArrayList<User>();
		diagrams= new ArrayList<Diagram>();
	}
	
	public void addTask(Task task){
		tasks.add(task);
	}
	
	public void addEmployees(ArrayList<User> emp){
		employees.addAll(emp);
	}
	
	public void addDiagram(Diagram diagram){
		diagrams.add(diagram);
	}
	
	public void createFolder(){
		File theDir = new File("Projects/"+name);
		try{
		    theDir.mkdir();
		} 
		catch(SecurityException se){
		     //Failed Message
		}        
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Task> getTasks(){
		return tasks;
	}
	
	public ArrayList<User> getEmployees(){
		return employees;
	}
	
	
}
