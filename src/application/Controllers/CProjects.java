package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class CProjects implements Initializable{
	
	private Window window;

	@FXML private ListView<String> projectsListView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (int i = 0; i < 50; i++) {
			String projects = "Project " + i;
			projectsListView.getItems().add(projects);
		}
	}
	
	public void onCreateProjectClicked(ActionEvent actionEvent) throws Exception{
		Stage stage= Utils.getStageFromEvent(actionEvent);
		window = new Window(stage);
		window.changeScene("CreateProject.fxml", this);
	}
	
	public void onCancelClicked(ActionEvent actionEvent){
		Utils.closeWindow(actionEvent);
		
	}
	
	private String getSelectedProject(){
		return projectsListView.getSelectionModel().getSelectedItem();
	}
	
	public void onCreateClicked() throws Exception{
		
	}

}
