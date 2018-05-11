package application;

import application.Controllers.CMessages;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Messages {
	
	public void createMessagesWindow() throws Exception{
		
		Stage messagesWindow = new Stage();
		messagesWindow.initModality(Modality.APPLICATION_MODAL);
		messagesWindow.setTitle("Messages");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Messages.fxml"));
		loader.setController(new CMessages());
		Parent root = loader.load();
		Scene scene = new Scene(root);
		
		messagesWindow.setScene(scene);
		messagesWindow.show();
	}
}
