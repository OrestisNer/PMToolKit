package application.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CProjects implements Initializable{
	
	private Window window;
	private User employee;
	private ArrayList<Project> projects;

	@FXML private ListView<Project> projectsListView;
	@FXML private Button createButton;
	@FXML private TextField nameField;
	
	public CProjects(User employee){
		this.employee=employee;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		projects = Utils.getProjectsFromFile();
		fillProjectListView();
	}
	
	public void onCreateProjectClicked(ActionEvent actionEvent) throws Exception{
		Stage stage= Utils.getStageFromEvent(actionEvent);
		window = new Window(stage);
		window.changeScene("CreateProject.fxml", this);
	}
	
	public void onCancelClicked(ActionEvent actionEvent) throws Exception{
		Alert alert = Utils.createConfirmationAlert("Log out", "Log Out Confirmation", "Are sure you want to log out?");
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        	Utils.closeWindow(actionEvent);
        	Window window = new Window("Log In","LogIn.fxml",new CLogIn(),true);
    		window.createWindow();
        }
	}
	
	public void onCreateClicked(ActionEvent actionEvent) throws Exception{
		String name=nameField.getText().toString().toUpperCase().trim();
		if(!name.isEmpty()){
			for(String project : employee.getProjects()){
				if(project.equalsIgnoreCase(name)){
					Utils.createErrorAlert("Error", "Project Name", "This project name already exists");
					return;
				}
			}
			
			Project project = new Project(name,employee);
			project.createFolder();
			employee.addProject(project.getName());
			
			Utils.saveEmployeeChanges(employee);
			Utils.saveProjectChanges(project);
			fillProjectListView();
			
			Stage stage= Utils.getStageFromEvent(actionEvent);
			window = new Window(stage);
			window.changeScene("Projects.fxml", new CProjects(employee));
			
		}
	}
	
	public void onSelectClicked(ActionEvent actionEvent) throws Exception{
		Project project = getSelectedProject();
		if(project!=null){
			Utils.closeWindow(actionEvent);
			Window window = new Window("Main Window","MainWindow.fxml",new CMainWindow(employee,project),true,true);
			window.createWindow();
		}else{
			Utils.createInfoAlert("Information", "Select project first");
		}
	}
	
	
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
	
	private Project getSelectedProject(){
		return projectsListView.getSelectionModel().getSelectedItem();
	}

}
