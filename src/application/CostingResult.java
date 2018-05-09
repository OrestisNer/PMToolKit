package application;

import java.io.IOException;

import application.Controllers.CCostingResult;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CostingResult {
	
	public void createCostingResultWindow() throws IOException{
		Stage windowCostingResult = new Stage();
		windowCostingResult.initModality(Modality.APPLICATION_MODAL);
		windowCostingResult.setTitle("Costing Results");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CostingResult.fxml"));
		loader.setController(new CCostingResult());
		
		Parent root = loader.load();
		
		Scene scene = new Scene(root,630,430);
		windowCostingResult.setScene(scene);
		windowCostingResult.show();
	}

}
