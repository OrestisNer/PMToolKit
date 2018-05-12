package application.Controllers;

import application.MainWindow;
import application.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CLogIn {
	
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	
	public void onClickedLogin(ActionEvent actionEvent) throws Exception{
		
		if(usernameField.getText().equals("") && passwordField.getText().equals("")) {
			Utils.closeWindow(actionEvent);
			MainWindow mainWindow= new MainWindow();
			mainWindow.createProjectWindow();
			
		}else{
			
		}
			
	}
	
}