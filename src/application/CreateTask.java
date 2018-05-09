package application;

import application.Controllers.CCreateTask;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateTask {
	
	public void createTaskWindow() throws Exception{
		
		Stage windowCreateTask = new Stage();
		windowCreateTask.initModality(Modality.APPLICATION_MODAL);
		windowCreateTask.setTitle("Create Task");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateTask.fxml"));
		loader.setController(new CCreateTask());
		
		Parent root = loader.load();
		
		Scene scene = new Scene(root,630,550);
		windowCreateTask.setScene(scene);
		windowCreateTask.show();
	}

}
