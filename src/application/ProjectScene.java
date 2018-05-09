package application;

import application.Controllers.CProjectScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProjectScene {

	public void createProjectWindow() throws Exception{
		
		Stage windowCreateTask = new Stage();
		windowCreateTask.initModality(Modality.APPLICATION_MODAL);
		windowCreateTask.setTitle("Project");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectScene.fxml"));
		loader.setController(new CProjectScene());
		
		Parent root = loader.load();
		
		Scene scene = new Scene(root,300,100);
		windowCreateTask.setScene(scene);
		windowCreateTask.show();
	}
}
