package application;

import application.Controllers.CEvaluation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Evaluation {
	
	public void createEvaluationWindow() throws Exception{
		Stage evaluationWindow = new Stage();
		evaluationWindow.initModality(Modality.APPLICATION_MODAL);
		evaluationWindow.setTitle("Evaluation");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Evaluation.fxml"));
		loader.setController(new CEvaluation());
		Parent root = loader.load();
		Scene scene = new Scene(root);
		
		evaluationWindow.setScene(scene);
		evaluationWindow.show();
	}

}
