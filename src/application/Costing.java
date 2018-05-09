package application;

import java.io.IOException;

import application.Controllers.CCosting;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Costing {
	
	public void createCostingWindow() throws IOException{
		Stage windowCosting = new Stage();
		windowCosting.initModality(Modality.APPLICATION_MODAL);
		windowCosting.setTitle("Costing");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Costing.fxml"));
		loader.setController(new CCosting());
		
		Parent root = loader.load();
		
		Scene scene = new Scene(root,630,430);
		windowCosting.setScene(scene);
		windowCosting.show();
	}

}
