package application.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.AlertUtils;
import application.FileUtils;
import application.Window;
import classes.Employee;
import classes.ProjectManager;
import classes.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CLogIn implements Initializable {
	
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private Button loginButton;
	
	private ArrayList<User> employees;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		employees=FileUtils.getEmployeesFromFile();
		
		//loginButton.setDisable(true);
		usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});
		
		Platform.runLater(() -> usernameField.requestFocus());
	}
	
	public void onClickedLogin(ActionEvent actionEvent) throws Exception{
		String inputUsername = usernameField.getText();
		String inputPassword = passwordField.getText();
		User employee= isEmployee(inputUsername,inputPassword);
		if(employee instanceof ProjectManager){
			AlertUtils.closeWindow(actionEvent);
			Window projectWindow= new Window("Project","Projects.fxml",new CProjects(employee,loginButton.getText()),false);
			projectWindow.createWindow();
		}else if(employee instanceof Employee){
			AlertUtils.closeWindow(actionEvent); //��� �� ������� �� �������� ��� ��� ���� ������
			Window projectWindow= new Window("Project","Projects.fxml",new CProjects(employee,loginButton.getText()),false);
			projectWindow.createWindow();

		}else{
			AlertUtils.createErrorAlert("Error", "Log in Failed", "Username or Password is wrong");
		}
	}
	
	
	private User isEmployee(String username,String password){
		for(User employee: employees){
			if(username.equalsIgnoreCase(employee.getUsername()) && password.equals(employee.getPassword())){
				return employee;
			}
		}
		return null;
	}
}