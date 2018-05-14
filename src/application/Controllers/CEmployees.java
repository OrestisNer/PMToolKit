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

public class CEmployees implements Initializable{
	
	Object controller;
	Window window;
	
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
		controller= new CCreateEmployee();
		window=new Window("Create Employee","CreateEmployee.fxml",controller,true);
		window.createWindow();
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
