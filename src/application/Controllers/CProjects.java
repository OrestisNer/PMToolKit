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
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CProjects implements Initializable{
	
	private Window window;
	private String buttonText;
	private User employee;
	private ArrayList<Project> projects;

	//Project.fxml
	@FXML private ListView<Project> projectsListView;
	@FXML private Button createProjectButton;
	
	//CreateProject.fxml
	@FXML private Button createButton;
	@FXML private Button cancelButton;
	@FXML private TextField projectNameField;
	@FXML private TextField klocField;
	@FXML private ComboBox<String> categoryCombobox;
	
	public CProjects(User employee, String buttonText){
		this.employee=employee;
		this.buttonText = buttonText;
	}
	
	private void setButtonText(String btnText) {
		this.buttonText = btnText;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(buttonText.equals(createProjectButton.getText())) {
			categoryCombobox.getItems().addAll("Organic", "Semi-detached","Embedded");
			setProperties();
		}else {
			projects = Utils.getProjectsFromFile();
			fillProjectListView();
			if(employee instanceof Employee)
				createProjectButton.setVisible(false);
		}
	}
	
	//Projects.fxml
	
	//Create Project button
	public void onCreateProjectClicked(ActionEvent actionEvent) throws Exception{
		Stage stage= Utils.getStageFromEvent(actionEvent);
		this.setButtonText(createProjectButton.getText());
		window = new Window(stage);
		window.changeScene("CreateProject.fxml", this);
	}
	
	//Select Project button
	public void onSelectClicked(ActionEvent actionEvent) throws Exception{
		Project project = getSelectedProject();
		if(project!=null){
			Utils.closeWindow(actionEvent);
			Window window = new Window("Main Window","MainWindow.fxml",new CMainWindow(employee,project),true,true);
			window.createWindow();
		}else{
			Utils.createInfoAlert("Information", "Select a project first!");
		}
	}
	
	private Project getSelectedProject(){
		return projectsListView.getSelectionModel().getSelectedItem();
	}
	
	//Cancel button
	public void onCancelClicked(ActionEvent actionEvent) throws Exception{
		Alert alert = Utils.createConfirmationAlert("Log out", "Log Out Confirmation", "Are sure you want to log out?");
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        	Utils.closeWindow(actionEvent);
        	Window window = new Window("Log In","LogIn.fxml",new CLogIn(),true);
    		window.createWindow();
        }
	}
	
	//Fills the projectListView depending on the employee
	private void fillProjectListView(){
		ArrayList<String> employeeProjects = employee.getProjects();
		ArrayList<Project> employeePJ = new ArrayList<>();
				
		for(Project project:projects) {
			for(String employeePj: employeeProjects) {
				if(project.getName().equalsIgnoreCase(employeePj)) 
					employeePJ.add(project);
			}
		}
		
		ObservableList<Project> projectList = FXCollections.observableArrayList(employeePJ);
		projectsListView.setItems(projectList);
		projectsListView.setCellFactory(param -> new ListCell<Project>() {
		    @Override
		    protected void updateItem(Project project, boolean empty) {
		        super.updateItem(project, empty);

		        if (project == null || project.getName() == null) {
		            setText(null);
		        } else {
		            setText(project.getName());
		        }
		    }
		});
	}
	
	//CreateProject.fxml
	
	//Create button
	public void onCreateClicked(ActionEvent actionEvent) throws Exception{
		String projectName=projectNameField.getText().toString().toUpperCase().trim();
		int kloc = Integer.parseInt(klocField.getText());
		String category = categoryCombobox.getValue();
		
		if(!projectName.isEmpty() || !category.isEmpty()){
			for(String project : employee.getProjects()){
				if(project.equalsIgnoreCase(projectName)){
					Utils.createErrorAlert("Error", "Project Name", "This project name already exists");
					return;
				}
			}
			
			Project project = new Project(projectName, category, kloc, employee);
			project.createFolder();
			employee.addProject(project.getName());
			
			Utils.saveEmployeeChanges(employee);
			Utils.saveProjectChanges(project);
			//fillProjectListView();
			
			this.setButtonText(createButton.getText());
			Stage stage= Utils.getStageFromEvent(actionEvent);
			window = new Window(stage);
			window.changeScene("Projects.fxml", this);			
		}else
			Utils.createInfoAlert("Information", "Fill all the fields!");
	}
	
	private void setProperties(){
		klocField.textProperty().addListener((observable, oldValue, newValue) -> {
			try{
				Integer.parseInt(klocField.getText());
			}catch(NumberFormatException e){
				klocField.setText("");
			}
		});
	}
	
	//Cancel button
	public void onCancelCreateClicked(ActionEvent actionEvent) throws IOException {
		Stage stage =Utils.getStageFromEvent(actionEvent);
		window = new Window(stage);
		this.setButtonText(cancelButton.getText());
		window.changeScene("Projects.fxml", this);
	}
}
