package application;

import application.Controllers.CWriteMessage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WriteMessage {
	
	public void createWriteMessageWindow() throws Exception {
		Stage writeMessageWindow = new Stage();
		writeMessageWindow.initModality(Modality.APPLICATION_MODAL);
		writeMessageWindow.setTitle("Write your message");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("WriteMessage.fxml"));
		loader.setController(new CWriteMessage());
		Parent root = loader.load();
		Scene scene = new Scene(root);

		writeMessageWindow.setScene(scene);
		writeMessageWindow.show();
	}
}
