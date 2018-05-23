package application.Controllers;

import java.util.Optional;

import application.Utils;
import application.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class CMainWindow {
	
	private Object controller;
	private Window window;
	
	@FXML private Button tasksButton;
	@FXML private Button evaluationButton;
	
	//Projects button clicked
	public void onProjectsClicked() throws Exception{
		controller=new CProjects();
		window= new Window("Projects","Projects.fxml",controller,true);
		window.createWindow();
	}
	
	//Tasks button clicked
	public void onTasksClicked() throws Exception{
		controller=new CTasks(tasksButton.getText());
		window= new Window("Tasks","Tasks.fxml",controller,true);
		window.createWindow();		
	}
	
	//Calendar button clicked
	public void onCalendarClicke() throws Exception{
		
	}
	
	//Message button clicked
	public void onMessagesClicked() throws Exception{
		controller= new CMessages();
		window= new Window("Messages","Messages.fxml",controller,true);
		window.createWindow();
	}
	
	//Employees button clicked
	public void onEmployeesClicked() throws Exception{
		controller= new CEmployees();
		window= new Window("Employees","Employees.fxml",controller,true);
		window.createWindow();
	}
	
	//Evaluation button clicked
	public void onEvaluationClicked() throws Exception{
		controller = new CEvaluation(evaluationButton.getText());
		window= new Window("Evaluation","Evaluation.fxml",controller,true);
		window.createWindow();
	}
	
	//Diagrams button clicked
	public void onDiagramsClicked() throws Exception{
		controller = new CDiagrams();
		window= new Window("Diagrams","Diagrams.fxml",controller,true);
		window.createWindow();
	}
	
	//Costing button clicked
	public void onCostingClicked() throws Exception{
		controller= new CCosting();
		window= new Window("Costing","Costing.fxml",controller,true);
		window.createWindow();
	}
	
	//Info button clicked
	public void onInfoClicked() throws Exception{
		controller = new CInfo();
		window = new Window("Project Info","ProjectInfo.fxml",controller,true);
		window.createWindow();
	}
	
	//Exit button Clicked
	public void onExitClicked(ActionEvent actionEvent){
		
		Alert alert = Utils.createConfirmationAler("Exit","Exit Confirmation", "Are sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        	Utils.closeWindow(actionEvent);
        }
	}
	
	//Log out button clicked
	public void onLogOutClicked(ActionEvent actionEvent){
		
		Alert alert = Utils.createConfirmationAler("Log out", "Log Out Confirmation", "Are sure you want to log out?");
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        	// User will log out and will be shown log in window
        }
    }
}
