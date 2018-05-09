package application;

import java.io.IOException;

import application.Controllers.CCostingTotalProject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CostingTotalProject {
	
	public void createCostingTotalProjectWindow() throws IOException{
		
		Stage windowCostingTotalProject = new Stage();
		windowCostingTotalProject.initModality(Modality.APPLICATION_MODAL);
		windowCostingTotalProject.setTitle("Costing Total Project");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CostingTotalProject.fxml"));
		loader.setController(new CCostingTotalProject());
	
		Parent root = loader.load();
	
		Scene scene = new Scene(root,630,430);
		windowCostingTotalProject.setScene(scene);
		windowCostingTotalProject.show();
	}

}
