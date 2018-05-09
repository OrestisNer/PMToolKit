package application;

import application.Controllers.CCreateEmployee;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateEmployee {
	
	public void createCreateEmployeeWindow() throws Exception{
		
		Stage createEditEmployeeWindow = new Stage();
		createEditEmployeeWindow.initModality(Modality.APPLICATION_MODAL);
		createEditEmployeeWindow.setTitle("Create Employee");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateEmployee.fxml"));
		loader.setController(new CCreateEmployee());
		Parent root = loader.load();
		Scene scene = new Scene(root);
		
		createEditEmployeeWindow.setScene(scene);
		createEditEmployeeWindow.show();
	}

}
