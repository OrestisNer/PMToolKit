package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import application.Controllers.CLogIn;
import classes.Project;
import classes.ProjectManager;
import classes.User;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		Window window = new Window("Log In","LogIn.fxml",new CLogIn(),true);	
		window.createWindow();
		/*createEmpFile();
		createProjectFile();
		createActivitiesFile();
		System.exit(0);*/
	}

	//Creates Employees file
	private void createEmpFile() throws IOException{
		ArrayList<User> emp= new ArrayList<User>();
		User pm = new ProjectManager("ProjectManager","123","Stefanos","Mpitzenis",50000,"Project Manager");
		emp.add(pm);
		String filename="Employees";
		ObjectOutputStream  outStream=null;
	    try{
	    	outStream=new ObjectOutputStream(new FileOutputStream(filename));
	    	outStream.writeObject(emp);
	    	outStream.close();
	    }catch(IOException e){
	    	System.out.println("Error writing to file "+filename);
	    	System.exit(0);
	    }
	}
	
	//Creates Projects File
	private void createProjectFile() throws IOException{
		ArrayList<Project> projects = new ArrayList<>();
		String filename = "Projects";
		ObjectOutputStream  outStream=null;
		
	    try{
	    	outStream=new ObjectOutputStream(new FileOutputStream(filename));
	    	outStream.writeObject(projects);
	    	outStream.close();
	    }catch(IOException e){
	    	System.out.println("Error writing to file "+filename);
	    	System.exit(0);
	    }
	}
	
	//Creates Activities file
	private void createActivitiesFile() throws IOException{
		ArrayList<Project> tasks = new ArrayList<>();
		String filename = "Activities";
		ObjectOutputStream  outStream=null;
		
	    try{
	    	outStream=new ObjectOutputStream(new FileOutputStream(filename));
	    	outStream.writeObject(tasks);
	    	outStream.close();
	    }catch(IOException e){
	    	System.out.println("Error writing to file "+filename);
	    	System.exit(0);
	    }
	}

}
