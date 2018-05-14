package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class CMessages implements Initializable{
	
	private Object controller;
	private Window window;
	
	@FXML private BorderPane messagesBorderPane;
	private ListView<String> messagesListView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		messagesListView = new ListView<>();
		for(int i = 0; i<50; i++) {
			String message = "Message "+ i ;
			messagesListView.getItems().add(message);
		}
		
		messagesBorderPane.setCenter(messagesListView);		
	}
	
	public void onWriteMessageClicked() throws Exception{
		controller = new CWriteMessage();
		window = new Window("Write your message","WriteMessage.fxml",controller,true);
		window.createWindow();	
	}
	
	public void onCancelClicked(ActionEvent event) {
		Utils.closeWindow(event);
	}
}
