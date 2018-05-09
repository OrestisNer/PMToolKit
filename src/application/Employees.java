package application;

import application.Controllers.CEmployees;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Employees {
	
	public void createEmployeesWindow() throws Exception {
		
		Stage employeesWindow = new Stage();
		employeesWindow.initModality(Modality.APPLICATION_MODAL);
		employeesWindow.setTitle("Employees");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Employees.fxml"));
		loader.setController(new CEmployees());
		Parent root = loader.load();
		Scene scene = new Scene(root);
		
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		employeesWindow.setScene(scene);
		employeesWindow.show();
		
	}

}
