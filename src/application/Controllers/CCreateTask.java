package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CCreateTask implements Initializable  {

	private Object controller;
	private Window window;
	
	@FXML private TextField textTaskName;
	@FXML private Button buttonCancel;
	private ListView<String> listView;
	private ListView<String> listView2;
	@FXML private HBox hboxPrerequisites;
	@FXML private HBox hboxAssign;
	@FXML private VBox parentID;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	    listView= new ListView<>();
	    listView2= new ListView<>();
		for(int i=0; i <50; i++){
			String item = "Item "+i ;
            listView.getItems().add(item);
            listView2.getItems().add(item);
		}
		
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		hboxPrerequisites.getChildren().add(listView);
		hboxAssign.getChildren().add(listView2);
		
	}
	
	public void onCancelClicked(ActionEvent actionEvent) throws Exception{
		Utils.closeWindow(actionEvent);
		controller = new CTasks();
		window = new Window("Tasks", "Tasks.fxml", controller, true);
		window.createWindow();
	}
	
}
