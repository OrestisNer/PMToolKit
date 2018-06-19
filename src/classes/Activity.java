package classes;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import application.Utils;
import classes.ProjectManager;

public class Activity implements Serializable{
	
	private String name;
	private ArrayList<Activity> presequisitesActivities;
	private String description;
	private String subject;
	private LocalDate startingDate;
	private LocalDate deadline;
	private HashMap<String, Boolean> employees;
	private boolean completed;
	private int estimatedDuration;
	private double estimatedManMonths;
	private int actualDuration;
	private double actualManMonths;
	private int numberOfEmployees;
	private Project project;
	private String id;
	private String completedPercent;
	//private Button confirmButton;
	
	
	public Activity(String name, ArrayList<Activity> presequisitesActivities, ArrayList<User> emp, int estimatedDuration, 
			String subject, String description, LocalDate startingDate, Project project ){
		
		this.name=name;
		this.presequisitesActivities=presequisitesActivities;
		
		this.numberOfEmployees=emp.size();
		
		employees= new HashMap<String, Boolean>();
		for(User employee: emp){
			System.out.println(employee.getUsername());
			employees.put(employee.getUsername(), false);			
		}
		
		this.estimatedDuration=estimatedDuration;
		this.description=description;
		this.subject = subject;
		
		this.startingDate= startingDate;
		this.deadline=calcDeadline(startingDate,estimatedDuration);
		this.estimatedManMonths= calcManMonths(numberOfEmployees,estimatedDuration);
		this.completed=false;
		this.project=project;
		this.id=project.getName()+name;
		id=id.replaceAll("\\s","").toUpperCase().trim();
		this.completedPercent = 0 + "/" + emp.size();
		//this.confirmButton = new Button("Confirm Task");
	}
	
	public void confirmActivity(User employee) throws IOException{
		if(employee instanceof Employee) {
			String empUsername = employee.getUsername();
			employees.put(empUsername, true);
	
			int completedTask = 0 ;
			for(String username : employees.keySet()) {
				if(employees.get(username))
					completedTask++;
			}
			
			this.setCompletedPercent(completedTask);
			
			int i = 0 ;
			for(String empUserName : employees.keySet()) {
				if(!empUserName.equals("ProjectManager") && employees.get(empUserName)) 
					i++;
			}
			if(i == employees.size()-1) {
				ProjectManager pm = (ProjectManager) Utils.getSingleEmployeeFromFile("ProjectManager");
				pm.sendConfirmationMessageToPM(this);
				Utils.saveEmployeeChanges(pm);
			}
		
		}else {
			String empUsername = employee.getUsername();
			employees.put(empUsername, true);
	
			if(isCompleted(employees)){
				completed=true;
				actualDuration=calcDuration(startingDate,LocalDate.now());
				actualManMonths=calcManMonths(numberOfEmployees,actualDuration);
			}
		}
	}
		
	
	private double calcManMonths(int numberOfEmployees, int duration){
		double manDays= duration/numberOfEmployees;
		return manDays/30;
	}
	
	private LocalDate calcDeadline(LocalDate startingDate, int estimatedDuration){
		LocalDate deadline = LocalDate.of(startingDate.getYear(), startingDate.getMonth(), startingDate.getDayOfMonth());
		while(estimatedDuration>0){
			if(!(deadline.getDayOfWeek().toString().equalsIgnoreCase("SUNDAY") 
					|| deadline.getDayOfWeek().toString().equalsIgnoreCase("SATURDAY")))
				estimatedDuration--;
			deadline= deadline.plusDays(1);
		}
		return deadline;
	}
	
	public void deleteEmployee(String employeeUsername){
		System.out.println(employeeUsername);
		employees.remove(employeeUsername);
	}
	private int calcDuration(LocalDate startingDate, LocalDate finishDate){
		LocalDate temp = LocalDate.of(startingDate.getYear(), startingDate.getMonth(), startingDate.getDayOfMonth());
		int duration=0;
		while(!(temp.equals(finishDate))){
			temp.plusDays(1);
			if(!(temp.getDayOfWeek().toString().equalsIgnoreCase("SUNDAY") 
					|| temp.getDayOfWeek().toString().equalsIgnoreCase("SATURDAY")))
				duration++;
		}
		return duration;
	}
	
	private boolean isCompleted(HashMap<String,Boolean> emp){
		Iterator<Entry<String, Boolean>> it = emp.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String,Boolean> pair = (Map.Entry<String,Boolean>)it.next();
	        if(!pair.getValue())
	        	return false;
	    }
	    return true;
	}
	
	public static int calcEstimatedTime(int best , int worst, int average){
		average = average*4;
		int x = best + average + worst;
		return x/6;
	}
	
	public void setCompletedPercent(int completedTask) {
		this.completedPercent = completedTask +"/"+ employees.size();
	}
	
	public String getCompletedPercent(){
		return completedPercent;
	}
	/*public Button getConfirmButton() {
		return confirmButton;
	}*/
	
	public String getName(){
		return name;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public LocalDate getDeadline(){
		return deadline;
	}
	
	public ArrayList<Activity> getPrerequisites(){
		return presequisitesActivities;
	}
	
	public LocalDate getStartingDate(){
		return startingDate;
	}
	
	public ArrayList<String> getEmployees(){
		ArrayList<String> emp = new ArrayList<String>();
		for (String empUsername : employees.keySet()) {
		   emp.add(empUsername);
		}
		return emp;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getEstimatedDuration(){
		return estimatedDuration;
	}
	
	public double getEstimatedManMonths(){
		return estimatedManMonths;
	}
	
	public boolean isCompleted(){
		return completed;
	}
	
	public int getActualDuration(){
		return actualDuration;
	}
	
	public double getActualManMonths(){
		return actualManMonths;
	}
	
	public String getId(){
		return id;
	}
}
