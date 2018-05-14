package application.Controllers;

import java.util.Optional;

import application.Utils;
import application.Window;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CMainWindow {
	
	private Object controller;
	private Window window;
	
	public void onEvaluationClicked() throws Exception{
		controller = new CEvaluation();
		window= new Window("Evaluation","Evaluation.fxml",controller,true);
		window.createWindow();
	}
	
	public void onMessagesClicked() throws Exception{
		controller= new CMessages();
		window= new Window("Messages","Messages.fxml",controller,true);
		window.createWindow();
	}
	
	public void onCreateTaskClicked() throws Exception{
		controller=new CCreateTask();
		window= new Window("Create Task","CreateTask.fxml",controller,true);
		window.createWindow();
		
	}
	
	public void onCostingClicked() throws Exception{
		controller= new CCosting();
		window= new Window("Costing","Costing.fxml",controller,true);
		window.createWindow();
	}
	
	public void onExitClicked(ActionEvent actionEvent){
		
		Alert alert = Utils.createConfirmationAler("Exit","Exit Confirmation", "Are sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        	Utils.closeWindow(actionEvent);
        }
	}
	
	public void onLogOutClicked(ActionEvent actionEvent){
		
		Alert alert = Utils.createConfirmationAler("Log out", "Log Out Confirmation", "Are sure you want to log out?");
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        	// User will log out and will be shown log in window
        }
    }
	
	public void onEmployeesClicked() throws Exception{
		controller= new CEmployees();
		window= new Window("Employees","Employees.fxml",controller,true);
		window.createWindow();
	}
}
