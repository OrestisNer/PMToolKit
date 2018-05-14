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

public class CEvaluation implements Initializable{
	
	Object controller;
	Window window;
	
	@FXML private BorderPane evaluationBorderPane;
	private ListView<String> employeeListView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		employeeListView = new ListView<>();
		for(int i = 0; i<50; i++) {
			String employee = "Employee "+ i ;
			employeeListView.getItems().add(employee);
		}
		
		evaluationBorderPane.setCenter(employeeListView);		
	}
	
	public void onNextClicked() throws Exception{
		controller= new CEvaluateEmployee();
		window = new Window("Evaluate Employee","EvaluateEmployee.fxml",controller,true);
		window.createWindow();
	}
	
	public void onCancelClicked(ActionEvent event) {
		Utils.closeWindow(event);
	}

}
