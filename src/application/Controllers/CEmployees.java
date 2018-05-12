package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.CreateEmployee;
import application.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class CEmployees implements Initializable{
	
	@FXML private BorderPane employeesBorderPane;
	private ListView<String> employeeListView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		employeeListView = new ListView<>();
		for(int i = 0; i<50; i++) {
			String employee = "Employee "+ i ;
			employeeListView.getItems().add(employee);
		}
		
		employeesBorderPane.setCenter(employeeListView);		
	}
	
	public void onCreateEmployeeClicked() throws Exception{
		CreateEmployee obj = new CreateEmployee();
		obj.createCreateEmployeeWindow();
	}
		
	public void onCancelClicked(ActionEvent actionEvent){
		Utils.closeWindow(actionEvent);
	}
	
	public void onEditClicked(ActionEvent actionEvent){
		
	}
	
	public void onDeleteClicked(ActionEvent actionEvent){
		employeeListView.getItems().remove(getSelectedEmployee());
	}
	
	
	private String getSelectedEmployee(){
		return employeeListView.getSelectionModel().getSelectedItem();
	}
}
