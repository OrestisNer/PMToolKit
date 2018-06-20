package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import classes.Activity;
import classes.Project;
import classes.User;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class AlertUtils {
	
	public static void closeWindow(ActionEvent actionEvent){
		Stage stage=getStageFromEvent(actionEvent);
	    stage.close();
	}
	
	public static Alert createConfirmationAlert(String title, String headerText, String contentText){ 
		Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
	}
	
	public static Alert createCustomConfirmationAlert(String title, String headerText, String contentText){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);

		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonNo = new ButtonType("No");
		

		alert.getButtonTypes().setAll(buttonYes,buttonNo);
		
		return alert;
	}
	
	public static void createErrorAlert(String title, String headerText, String contentText){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
        alert.showAndWait();
	}
	
	public static void createInfoAlert(String title, String contentText){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	public static String createInputDialog(String title,String headerText, String contentText){
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.setContentText(contentText);

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    return result.get();
		}else{
			return "";
		}
	}
	
	public static Window createProgressBar() throws Exception{
		Window window = new Window("Progress","ProgressBar.fxml",null,true);
		window.createWindow();
		return window;
	}
	
	public static void closeProgram(Stage stage){
		/*Alert alert = createConfirmationAlert("Exit","Exit Confirmation", "Are sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        	stage.close();
        }*/
		stage.close();
	}
	
	public static Stage getStageFromEvent(ActionEvent actionEvent){
		Node  source = (Node)  actionEvent.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    return stage;
	}
}
