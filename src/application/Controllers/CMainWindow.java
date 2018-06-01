package application.Controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
import classes.Project;
import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class CMainWindow implements Initializable {
	
	private Object controller;
	private Window window;
	private User employee;
	private Project project;
	
	@FXML private Button tasksButton;
	@FXML private Button messagesButton;
	@FXML private Button evaluationButton;
	@FXML private Button employeesButton;
	@FXML private Label headerLabel;
	
	public CMainWindow(User employee, Project project){
		this.employee=employee;
		this.project=project;
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		headerLabel.setText(project.getName());
	}
	
	//Tasks button clicked
	public void onTasksClicked() throws Exception{
		controller=new CTasks(project,employee,tasksButton.getText());
		window= new Window("Tasks","Tasks.fxml",controller,true);
		window.createWindow();		
	}
	
	//Calendar button clicked
	public void onCalendarClicked() throws Exception{
		
	}
	
	//Message button clicked
	public void onMessagesClicked() throws Exception{
		controller= new CMessages(employee,project,messagesButton.getText());
		window= new Window("Messages","Messages.fxml",controller,true);
		window.createWindow();
	}
	
	//Employees button clicked
	public void onEmployeesClicked() throws Exception{
		controller= new CEmployees(project,employeesButton.getText());
		window= new Window("Employees","Employees.fxml",controller,true);
		window.createWindow();
	}
	
	//Evaluation button clicked
	public void onEvaluationClicked() throws Exception{
		controller = new CEvaluation(project, employee, evaluationButton.getText());
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
		Alert alert = Utils.createConfirmationAlert("Exit","Exit Confirmation", "Are sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        	Utils.closeWindow(actionEvent);
        }
	}
	
	//Log out button clicked
	public void onLogOutClicked(ActionEvent actionEvent) throws Exception{
		Alert alert = Utils.createConfirmationAlert("Log out", "Log Out Confirmation", "Are sure you want to log out?");
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        	Utils.closeWindow(actionEvent);
        	window = new Window("Log In","LogIn.fxml",new CLogIn(),true);
    		window.createWindow();
        }
    }



	
}
