package application.Controllers;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
import classes.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class CLogIn implements Initializable {
	
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private Button loginButton;
	
	private ArrayList<User> employees;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		readEmployeeFile();
		
		loginButton.setDisable(true);
		usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});
		
		Platform.runLater(() -> usernameField.requestFocus());
	}
	
	public void onClickedLogin(ActionEvent actionEvent) throws Exception{
		/*String inputUsername = usernameField.getText();
		String inputPassword = passwordField.getText();
		if(isEmployee(inputUsername,inputPassword)){*/
			Utils.closeWindow(actionEvent);
			CMainWindow controller= new CMainWindow();
			Window mainWindow= new Window("MainWindow","MainWindow.fxml",controller,true,true);
			mainWindow.createWindow();
		/*}else{
			Utils.createErrorAlert("Error", "Log in Failed", "Username or Password is wrong");
		}*/
	}
	
	
	@SuppressWarnings("unchecked")
	private void readEmployeeFile(){
		String filename="Employees";
		ObjectInputStream inputStream=null;
		try{
			inputStream=new ObjectInputStream(new FileInputStream(filename));
			employees= (ArrayList<User>) inputStream.readObject();
		}catch(Exception e){
			
		}
	}
	
	private boolean isEmployee(String username,String password){
		for(User employee: employees){
			if(username.equalsIgnoreCase(employee.getUsername()) && password.equals(employee.getPassword())){
				return true;
			}
		}
		return false;
	}


	
	
}