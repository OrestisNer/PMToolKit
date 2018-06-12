package classes;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Project implements Serializable{
	
	private User projectManager;
	private String name;
	private ArrayList<String> tasksID;
	private ArrayList<User> employees;
	private ArrayList<Diagram> diagrams;
	private LocalDate startingDate;
	private Calendar calendar;
	private Cost cost;
	
	public Project(String name, User projectManager){
		this.name=name;
		this.projectManager=projectManager;
		this.startingDate= LocalDate.now();
		tasksID= new ArrayList<String>();
		employees= new ArrayList<User>();
		employees.add(projectManager);
		diagrams= new ArrayList<Diagram>();
	}
	
	public void showInfo() {
		System.out.println("PM: "+ projectManager +"Name: "+ name);
		System.out.println("Tasks:");
		for(String task: tasksID) {
			System.out.println(task);
		}
		System.out.println("Employees");
		for(User emp: employees)
			System.out.println(emp.getName());
		
	}
	
	public void addTask(Activity task){
		tasksID.add(task.getId());
	}
	
	public void addEmployees(ArrayList<User> emp){
		employees.addAll(emp);
	}
	
	public void addEmployee(User employee){
		employees.add(employee);
	}
	
	public void addDiagram(Diagram diagram){
		diagrams.add(diagram);
	}
	
	public void createFolder(){
		File theDir = new File("Project Files/"+name);
		try{
		    theDir.mkdirs();
		} 
		catch(SecurityException se){
		     //Failed Message
		}        
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<String> getTasksIds(){
		return tasksID;
	}
	
	public ArrayList<User> getEmployees(){
		return employees;
	}
	
	public User getProjectManager(){
		return projectManager;
	}
	
	
}
