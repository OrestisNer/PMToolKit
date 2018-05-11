package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Utils;
import application.WriteMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class CMessages implements Initializable{
	
	@FXML public BorderPane messagesBorderPane;
	public ListView<String> messagesListView;
	
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
		WriteMessage obj = new WriteMessage();
		obj.createWriteMessageWindow();
	}
	
	public void onCancelClicked(ActionEvent event) {
		Utils.closeWindow(event);
	}
}
