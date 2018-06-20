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
	private ArrayList<String> employees;
	private ArrayList<Diagram> diagrams;
	private LocalDate startingDate;
	//private Calendar calendar;
	private Cost cost;
	private int duration;
	private boolean isCosted;
	
	public Project(String name, String category , int kloc, User projectManager){
		this.name=name;
		this.projectManager=projectManager;
		this.startingDate= LocalDate.now();
		this.category = category;
		this.kloc = kloc;
		this.isCosted=false;
		activitiesID= new ArrayList<String>();
		employees= new ArrayList<String>();
		employees.add(projectManager.getUsername());
		diagrams= new ArrayList<Diagram>();
	}
	
	public void addActivity(Activity act){
		activitiesID.add(act.getId());
	}
	
	public void addEmployees(ArrayList<User> emp){
		for(User employee: emp){
			employees.add(employee.getUsername());
		}
	}
	
	public void addEmployee(User employee){
		employees.add(employee.getUsername());
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
	
	public void setDuration(int duration){
		this.duration=duration;	
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
	
	public ArrayList<String> getEmployees(){
		return employees;
	}
	
	public ArrayList<String> getEmpUsernames(){
		return employees;
	}
	
	public User getProjectManager(){
		return projectManager;
	}
	
	public LocalDate getStartingDate() {
		return startingDate;
	}
	
	public boolean isCosted(){
		return isCosted;
	}
	
	public void setIsCosted(boolean value){
		this.isCosted=value;
	}
	
	public Cost getCost(){
		return cost;
	}
	
	public void initializeCost(){
		cost= new Cost(this);
	}
}
