package classes;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class Project {
	
	private ProjectManager projectmanager;
	private String name;
	private ArrayList<Task> tasks;
	private ArrayList<Employee> employees;
	private ArrayList<Diagram> diagrams;
	private LocalDate startingDate;
	private Calendar calendar;
	private Cost cost;
	
	public Project(String name, ProjectManager projectManager){
		this.name=name;
		this.projectmanager=projectManager;
		this.startingDate= LocalDate.now();
	}
	
	public void addTask(Task task){
		tasks.add(task);
	}
	
	public void addEmployees(ArrayList<Employee> emp){
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
	
	
	
}
