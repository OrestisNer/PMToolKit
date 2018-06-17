package classes;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Project implements Serializable{
	
	private User projectManager;
	private String name;
	private String category;
	private int kloc;
	private ArrayList<String> activitiesID;
	private ArrayList<User> employees;
	private ArrayList<Diagram> diagrams;
	private LocalDate startingDate;
	private Calendar calendar;
	private Cost cost;
	
	public Project(String name, String category , int kloc, User projectManager){
		this.name=name;
		this.projectManager=projectManager;
		this.startingDate= LocalDate.now();
		this.category = category;
		this.kloc = kloc;
		activitiesID= new ArrayList<String>();
		employees= new ArrayList<User>();
		employees.add(projectManager);
		diagrams= new ArrayList<Diagram>();
	}
	
	public void addActivity(Activity act){
		activitiesID.add(act.getId());
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
	
	public String getCategory() {
		return category;
	}
	
	public int getKloc() {
		return kloc;
	}
	
	public ArrayList<String> getActivitiesIds(){
		return activitiesID;
	}
	
	public ArrayList<User> getEmployees(){
		return employees;
	}
	
	public ArrayList<String> getEmpUsernames(){
		ArrayList<String> empUsernames = new ArrayList<>();
		for(User emp : employees) 
			empUsernames.add(emp.getUsername());
		
		return empUsernames;
	}
	
	public User getProjectManager(){
		return projectManager;
	}
	
	public LocalDate getStartingDate() {
		return startingDate;
	}
}
