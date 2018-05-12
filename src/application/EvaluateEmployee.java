package application;

import application.Controllers.CEvaluateEmployee;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EvaluateEmployee {
	
	public void createEvaluateEmployeeWindow() throws Exception{
		Stage evaluateEmployeeWindow = new Stage();
		evaluateEmployeeWindow.initModality(Modality.APPLICATION_MODAL);
		evaluateEmployeeWindow.setTitle("Evaluate Employee");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EvaluateEmployee.fxml"));
		loader.setController(new CEvaluateEmployee());
		Parent root = loader.load();
		Scene scene = new Scene(root);
		
		evaluateEmployeeWindow.setScene(scene);
		evaluateEmployeeWindow.show();
	}

}
