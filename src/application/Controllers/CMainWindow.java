package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
import classes.Activity;
import classes.Employee;
import classes.Project;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CMainWindow implements Initializable {
	
	private Object controller;
	private Window window;
	private User employee;
	private Project project;
	
	
	@FXML private Label headerLabel;
	@FXML private Label usernameLabel;
	
	@FXML private Button tasksButton;
	@FXML private Button messagesButton;
	@FXML private Button employeesButton;
	@FXML private Button evaluationButton;
	@FXML private Button diagramsButton;
	@FXML private Button costingButton;
	@FXML private Button infoButton;
	
	@FXML private TableView<Activity> taskTable;
	@FXML private TableColumn<Activity, String> taskNameCol;
	@FXML private TableColumn<Activity, String> subjectCol;
	@FXML private TableColumn<Activity, LocalDate> startingDateCol;
	@FXML private TableColumn<Activity, LocalDate> deadlineCol;
	@FXML private TableColumn<Activity, String> completedCol;
	//@FXML private TableColumn<Activity, Button> actionCol;
	
	
	public CMainWindow(User employee, Project project){
		this.employee=employee;
		this.project=project;
	}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		headerLabel.setText(project.getName());
		usernameLabel.setText(employee.getName());
		
		refreshTaskTable();
		if(employee instanceof Employee ){
			evaluationButton.setVisible(false);
			diagramsButton.setVisible(false);
			costingButton.setVisible(false);
			infoButton.setVisible(false);
			employeesButton.setVisible(false);
		}
	}
	
	//Initializes the data in the task table
	public void refreshTaskTable() {
		taskNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		subjectCol.setCellValueFactory(new PropertyValueFactory<>("subject"));
		startingDateCol.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
		deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
		completedCol.setCellValueFactory(new PropertyValueFactory<>("completedPercent"));
		
		ArrayList<String> userActID = employee.getUnfinishedActivities(project);
		ArrayList<Activity> userActs = Utils.getActivitiesFromID(userActID);
		ObservableList<Activity> activityList = FXCollections.observableArrayList(userActs);
		taskTable.setItems(activityList);
	}
	
	private Activity getSelectedAct() {
		return taskTable.getSelectionModel().getSelectedItem();
	}
	
	//Tasks button clicked
	public void onTasksClicked() throws Exception{
		controller=new CActivities(project,employee,tasksButton.getText());
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
	
	//Confirm Task Button
	public void onConfirmTaskClicked() throws IOException {
		Activity selectedActivity = getSelectedAct();
		selectedActivity.confirmActivity(employee);
		employee.confirmActivity(project, selectedActivity);
		Utils.saveActivityChanges(selectedActivity);
		Utils.saveEmployeeChanges(employee);
		Utils.createInfoAlert("Confiramtion", "You successfuly confirm task "+selectedActivity.getName());
		refreshTaskTable();
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
		controller = new CDiagrams(project);
		window= new Window("Diagrams","Diagrams.fxml",controller,true);
		window.createWindow();
	}
	
	//Costing button clicked
	public void onCostingClicked() throws Exception{
		boolean startCosting=false;
		if(!project.isCosted()){
			startCosting=true;
		}else{
			Alert alert=Utils.createCustomConfirmationAlert("Cost", "This project has already benn costed.", "Do you want to cost this project again?");
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get().getText().equals("Yes")){
				startCosting=true;
			}
		}
		if(startCosting){
			controller= new CCosting(project,costingButton.getText());
			window= new Window("Costing","CostingResult.fxml",controller,true);
			window.createWindow();
		}
	}
	
	//Info button clicked
	public void onInfoClicked() throws Exception{
		controller = new CInfo(project);
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
