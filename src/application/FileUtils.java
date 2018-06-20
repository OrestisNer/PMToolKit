package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import classes.Activity;
import classes.Project;
import classes.User;

public class FileUtils {
	
	public static void saveEmployeeChanges(User employee) throws IOException{
		ArrayList<User> employees = getEmployeesFromFile();
		
		for (Iterator<User> iterator = employees.iterator(); iterator.hasNext(); ) {
		    User emp = iterator.next();
		    if (emp.getUsername().equalsIgnoreCase(employee.getUsername())) {
		        iterator.remove();
		    }
		}
		
		employees.add(employee);
		writeToEmployeeFile(employees);
		
	}
	
	public static void saveProjectChanges(Project project) throws IOException{
		ArrayList<Project> projects = getProjectsFromFile();
		
		for (Iterator<Project> iterator = projects.iterator(); iterator.hasNext(); ) {
		    Project pr = iterator.next();
		    if (pr.getName().equalsIgnoreCase(project.getName())) {
		        iterator.remove();
		    }
		}
		
		projects.add(project);
		writeToProjectFile(projects);
	}
	
	public static void saveActivityChanges(Activity task) throws IOException{
		ArrayList<Activity> activities= getActivitiesFromFile();
		
		for(Iterator<Activity> iterator = activities.iterator(); iterator.hasNext();){
			Activity t = iterator.next();
			if(t.getId().equalsIgnoreCase(task.getId())){
				iterator.remove();
			}
		}
		
		activities.add(task);
		writeToActivitiesFile(activities);
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<User> getEmployeesFromFile(){
		ArrayList<User> employees=null;
		String filename="Employees";
		ObjectInputStream inputStream=null;
		try{
			inputStream=new ObjectInputStream(new FileInputStream(filename));
			employees= (ArrayList<User>) inputStream.readObject();
			inputStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return employees;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Project> getProjectsFromFile(){
		ArrayList<Project> projects=null;
		String filename="Projects";
		ObjectInputStream inputStream=null;
		try{
			inputStream=new ObjectInputStream(new FileInputStream(filename));
			projects= (ArrayList<Project>) inputStream.readObject();
			inputStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return projects;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Activity> getActivitiesFromFile(){
		ArrayList<Activity> activities=null;
		String filename="Activities";
		ObjectInputStream inputStream= null;
		try{
			inputStream=new ObjectInputStream(new FileInputStream(filename));
			activities= (ArrayList<Activity>) inputStream.readObject();
		}catch(Exception e){
			e.printStackTrace();
		}
		return activities;
	}
	
	public static void writeToEmployeeFile(ArrayList<User> employees) throws IOException{
		ObjectOutputStream  outStream=null;
		String filename="Employees";
	    try{
	    	outStream=new ObjectOutputStream(new FileOutputStream(filename));
	    	outStream.writeObject(employees);
	    	outStream.close();
	    }catch(IOException e){
	    	System.out.println("Error writing to file "+filename);
	    	e.printStackTrace();
	    }
		
	}
	
	public static void writeToProjectFile(ArrayList<Project> projects) throws IOException{
		ObjectOutputStream  outStream=null;
		String filename="Projects";
	    try{
	    	outStream=new ObjectOutputStream(new FileOutputStream(filename));
	    	outStream.writeObject(projects);
	    	outStream.close();
	    }catch(IOException e){
	    	System.out.println("Error writing to file "+filename);
	    	e.printStackTrace();
	    }
		
	}
	
	public static void writeToActivitiesFile(ArrayList<Activity> activities) throws IOException{
		ObjectOutputStream  outStream=null;
		String filename="Activities";
		try{
			outStream=new ObjectOutputStream(new FileOutputStream(filename));
	    	outStream.writeObject(activities);
	    	outStream.close();
		}catch(IOException e){
			System.out.println("Error writing to file "+filename);
	    	e.printStackTrace();
		}
		
	}
	
	public static User getSingleEmployeeFromFile(String empUsername){
		ArrayList<User> allEmployees = getEmployeesFromFile();
		for(Iterator<User> iterator = allEmployees.iterator(); iterator.hasNext();){
			User employee= iterator.next();
			if(employee.getUsername().equalsIgnoreCase(empUsername))
				return employee;
		}
		return null;
	}
	
	public static ArrayList<User> getEmployeesFromUsername(ArrayList<String> empUsernames){
		ArrayList<User> allEmployees= getEmployeesFromFile();
		ArrayList<User> returnedEmployees= new ArrayList<User>();
		for(Iterator<User> iterator= allEmployees.iterator(); iterator.hasNext();){
			User e = iterator.next();
			if(empUsernames.contains(e.getUsername()))
				returnedEmployees.add(e);
		}
		return returnedEmployees;
	}
	
	public static ArrayList<Activity> getActivitiesFromID(ArrayList<String> actId){
		ArrayList<Activity> allTasks = getActivitiesFromFile();
		ArrayList<Activity> returnedTasks= new ArrayList<Activity>();
		for(Iterator<Activity> iterator = allTasks.iterator(); iterator.hasNext();){
			Activity t=iterator.next();
			if(actId.contains(t.getId()))
				returnedTasks.add(t);
		}
		return returnedTasks;
	}
	
	public static Activity getSingleActivityFromFile(String actId){
		ArrayList<Activity> allTasks = getActivitiesFromFile();
		for(Iterator<Activity> iterator = allTasks.iterator(); iterator.hasNext();){
			Activity t= iterator.next();
			if(t.getId().equalsIgnoreCase(actId))
				return t;
		}
		return null;	
	}
}