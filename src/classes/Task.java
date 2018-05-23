package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Task {
	
	private String name;
	private ArrayList<Task> presequisitesTasks;
	private String description;
	private LocalDate startingDate;
	private LocalDate deadline;
	private HashMap<Employee, Boolean> employees;
	private boolean completed;
	private int estimatedDuration;
	private double estimatedManMonths;
	private int actualDuration;
	private double actualManMonths;
	private int numberOfEmployees;
	private Project project;
	
	
	public Task(String name, ArrayList<Task> presequisitesTasks, ArrayList<Employee> emp, int estimatedDuration, String description
			, LocalDate startingDate, Project project ){
		
		this.name=name;
		this.presequisitesTasks=presequisitesTasks;
		
		this.numberOfEmployees=emp.size();
		
		employees= new HashMap<Employee, Boolean>();
		for(Employee employee: emp){
			employees.put(employee, false);			
		}
		
		this.estimatedDuration=estimatedDuration;
		this.description=description;
		
		this.startingDate= startingDate;
		this.deadline=getDeadline(startingDate,estimatedDuration);
		this.estimatedManMonths= calcManMonths(numberOfEmployees,estimatedDuration);
		this.completed=false;
		this.project=project;
	}
	
	public void confirmTask(Employee employee){
		employees.put(employee, true);
		
		if(isCompleted(employees)){
			completed=true;
			actualDuration=getDuration(startingDate,LocalDate.now());
			actualManMonths=calcManMonths(numberOfEmployees,actualDuration);
		}
		//Πρέπει να στέλνω στον PM ένα message ολοκλήρωσης του task.
	}
	
	
	public int getActualDuration(){
		return actualDuration;
	}
	
	
	private double calcManMonths(int numberOfEmployees, int duration){
		double manDays= estimatedDuration/numberOfEmployees;
		return manDays/30;
	}
	
	private LocalDate getDeadline(LocalDate startingDate, int estimatedDuration){
		LocalDate deadline = LocalDate.of(startingDate.getYear(), startingDate.getMonth(), startingDate.getDayOfMonth());
		while(estimatedDuration>0){
			if(!(deadline.getDayOfWeek().toString().equalsIgnoreCase("SUNDAY") 
					|| deadline.getDayOfWeek().toString().equalsIgnoreCase("SATURDAY")))
				estimatedDuration--;
			deadline= deadline.plusDays(1);
		}
		return deadline;
	}
	
	private int getDuration(LocalDate startingDate, LocalDate finishDate){
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
	
	private boolean isCompleted(HashMap<Employee,Boolean> emp){
		Iterator<Entry<Employee, Boolean>> it = emp.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<Employee,Boolean> pair = (Map.Entry<Employee,Boolean>)it.next();
	        if(!pair.getValue())
	        	return false;
	    }
	    return true;
	}
	
	
}
