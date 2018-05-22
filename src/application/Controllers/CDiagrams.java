package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class CDiagrams implements Initializable{
	
	@FXML private ComboBox<String> diagramsComboBox;
	@FXML private Button generateButton;
	@FXML private VBox diagramVbox;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		diagramsComboBox.getItems().addAll("Gantt","Pert");
	}
	
	public void enableButton() {
		if(diagramsComboBox.getValue().equals("Gantt")) {
			generateButton.setDisable(false);
			generateButton.setText("Generate Gantt");
		}else {
			generateButton.setDisable(false);
			generateButton.setText("Generate Pert");
		}
	}
	
	public void onGenerateClicked() {
		if(diagramsComboBox.getValue().equals("Gantt")) {
			
		}else {
			
		}
	}
	
	public void onCancelClicked(ActionEvent actionEvent){
		Utils.closeWindow(actionEvent);
	}
}
