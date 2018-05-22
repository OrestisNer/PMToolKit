package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class CTaskInfo implements Initializable{
	
	private Object controller;
	private Window window;
	
	@FXML private ListView<String> prerequisitesListView;
	@FXML private ListView<String> employeesListView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(int i = 0; i <50; i++) {
			String tasks = "Task " + i;
			String employees = "Employee " + i;
			prerequisitesListView.getItems().add(tasks);
			employeesListView.getItems().add(employees);
		}
	}
	
	public void onBackClicked(ActionEvent actionEvent) throws Exception{
		Utils.closeWindow(actionEvent);
		controller = new CTasks();
		window = new Window("Tasks", "Tasks.fxml", controller, true);
		window.createWindow();
	}
}
