package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.EvaluateEmployee;
import application.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class CEvaluation implements Initializable{
	
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
		EvaluateEmployee obj = new EvaluateEmployee();
		obj.createEvaluateEmployeeWindow();
	}
	
	public void onCancelClicked(ActionEvent event) {
		Utils.closeWindow(event);
	}

}
