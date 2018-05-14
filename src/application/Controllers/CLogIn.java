package application.Controllers;

import application.Utils;
import application.Window;
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
			CMainWindow controller= new CMainWindow();
			Window mainWindow= new Window("MainWindow","MainWindow.fxml",controller,true,true);
			mainWindow.createWindow();
			
		}else{
			
		}
			
	}
	
}