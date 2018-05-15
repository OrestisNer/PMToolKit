package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CMessages implements Initializable{
	
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
	
	public void onSyntaxClicked(ActionEvent actionEvent) throws Exception{	
		Stage stage  = Utils.getStageFromEvent(actionEvent);
	    window= new Window(stage);
	    window.changeScene("WriteMessage.fxml", this);
	}
	
	public void onCancelClicked(ActionEvent actionEvent) {
		Utils.closeWindow(actionEvent);
	}
	
	public void onSendMessageClicked() {
		
	}
	
	public void onCancelSyntaxClicked(ActionEvent actionEvent) throws IOException {
		Stage stage  = Utils.getStageFromEvent(actionEvent);
	    window= new Window(stage);
	    window.changeScene("Messages.fxml", this);
	}
}
