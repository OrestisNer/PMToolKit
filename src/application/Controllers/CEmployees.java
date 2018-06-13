package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
import classes.Employee;
import classes.Project;
import classes.ProjectManager;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CEmployees implements Initializable{
	
	private Window window;
	private Project project;
	private String buttonText;
	private User employee;

	//Employees.fxml
	@FXML private ListView<User> employeeListView;
	@FXML private Button editButton;
	@FXML private Button createEmployeeButton;
	
	//CreateEmployee.fxml
	@FXML private TextField userNameField;
	@FXML private TextField passwordField;
	@FXML private TextField specialtyField;
	@FXML private TextField firstNameField;
	@FXML private TextField lastNameField;
	@FXML private TextField salaryField;
	@FXML private Button createButton;
	@FXML private Button cancelButton;
	
	
	
	public CEmployees(Project project, String buttonText){
		this.project=project;
		this.buttonText=buttonText;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (buttonText.equalsIgnoreCase(editButton.getText())){
			createButton.setText("Edit");
			fillEmployeeInfo();
		}else if(buttonText.equalsIgnoreCase(createEmployeeButton.getText())){
			createButton.setText("Create");
			setProperties();
		}else
			fillEmployeesList();
	}
	
	public void onCreateClicked(ActionEvent actionEvent) throws Exception{
		Stage stage= Utils.getStageFromEvent(actionEvent);
		this.buttonText= this.createEmployeeButton.getText();
		window = new Window(stage);
		window.changeScene("CreateEmployee.fxml", this);
	}
		
	public void onCancelClicked(ActionEvent actionEvent){
		Utils.closeWindow(actionEvent);
	}
	
	public void onEditClicked(ActionEvent actionEvent) throws IOException{
		Stage stage= Utils.getStageFromEvent(actionEvent);
		this.buttonText=editButton.getText();
		employee=this.getSelectedEmployee();
		window = new Window(stage);
		window.changeScene("CreateEmployee.fxml", this);
	}
	
	public void onDeleteClicked(ActionEvent actionEvent){
		employeeListView.getItems().remove(getSelectedEmployee());
	}
	
	
	private User getSelectedEmployee(){
		return employeeListView.getSelectionModel().getSelectedItem();
	}
	
	public void onCreateEmployeeClicked(ActionEvent actionEvent) throws IOException {
		if(hasAppropriateArgs()){
			createEmployee();
			Utils.createInfoAlert("Proccess", "You successfully create employee "+userNameField.getText());
			Stage stage = Utils.getStageFromEvent(actionEvent);
			this.buttonText=createButton.getText();
			window = new Window(stage);
			window.changeScene("Employees.fxml", this);
		}else
			Utils.createInfoAlert("Information", "You have to fill in all the fields");
		
	}
	
	public void onSaveChangesClicked(ActionEvent event) {
		
	}
	
	public void onCancelCreateClicked(ActionEvent actionEvent) throws IOException {
		Stage stage= Utils.getStageFromEvent(actionEvent);
		window = new Window(stage);
		window.changeScene("Employees.fxml",this);
	}
	
	public void fillEmployeesList(){
		ArrayList<User> employees = project.getEmployees();
		employeeListView.getItems().clear();
		ObservableList<User> list = FXCollections.observableArrayList(employees);
		employeeListView.setItems(list);
		employeeListView.setCellFactory(param -> new ListCell<User>(){
			@Override
			protected void updateItem(User employee, boolean empty) {
				super.updateItem(employee, empty);
				if (employee == null )
					setText(null);
				else
					setText(employee.getName()+"-"+employee.getSpecialty());
			}
		});
	}
	
	public void fillEmployeeInfo(){
		userNameField.setText(employee.getUsername());
		passwordField.setText(employee.getPassword());
		specialtyField.setText(employee.getSpecialty());
		firstNameField.setText(employee.getFirstname());
		lastNameField.setText(employee.getLastname());
		salaryField.setText(Double.toString(employee.getSalary()));
		
		
		
	}
	
	public void createEmployee() throws IOException{
		String username = userNameField.getText();
		String password = passwordField.getText();
		String speciality = specialtyField.getText();
		String firstname= firstNameField.getText();
		String lastname = lastNameField.getText();
		double salary = Double.parseDouble(salaryField.getText());
		User employee = new Employee(username,password,firstname,lastname,salary,speciality);
		Alert alert=Utils.createCustomConfirmationAlert("Add Employee", null, "Do you want to add employee "+username+" to this project?");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get().getText().equals("Yes")){
			project.addEmployee(employee);
        	employee.addProject(project.getName());
        	Utils.saveProjectChanges(project);
		}
		
        Utils.saveEmployeeChanges(employee);
        
	}
	
	private boolean hasAppropriateArgs(){
		if(userNameField.getText().equals("") || passwordField.getText().equals("") || specialtyField.getText().equals("")
				|| firstNameField.getText().equals("") || lastNameField.getText().equals("") || salaryField.getText().equals(""))
			return false;
		return true;
	}
	
	private void setProperties(){
		salaryField.textProperty().addListener((observable, oldValue, newValue) -> {
			try{
				Double.parseDouble(salaryField.getText());
			}catch(NumberFormatException e){
				salaryField.setText("");
			}
		});
	}
}
