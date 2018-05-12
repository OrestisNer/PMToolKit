package application.Controllers;

import java.util.Optional;

import application.Costing;
import application.CreateTask;
import application.Employees;
import application.Evaluation;
import application.Messages;
import application.Utils;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CMainWindow {
	
	public void onEvaluationClicked() throws Exception{
		Evaluation obj = new Evaluation();
		obj.createEvaluationWindow();
	}
	
	public void onMessagesClicked() throws Exception{
		Messages obj = new Messages();
		obj.createMessagesWindow();
	}
	
	public void onCreateTaskClicked() throws Exception{
		CreateTask obj= new CreateTask();
		obj.createTaskWindow();
	}
	
	public void onCostingClicked() throws Exception{
		Costing costing= new Costing();
		costing.createCostingWindow();
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
		Employees employees = new Employees();
		employees.createEmployeesWindow();
	}
}
