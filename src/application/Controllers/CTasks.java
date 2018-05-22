package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class CTasks implements Initializable{
	
	private Window window;
	private Object controller;

	@FXML private ListView<String> tasksListView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (int i = 0; i < 50; i++) {
			String tasks = "Task " + i;
			tasksListView.getItems().add(tasks);
		}
	}
	
	public void onCreateTaskClicked(ActionEvent actionEvent) throws Exception {
		Utils.closeWindow(actionEvent);
		controller = new CCreateTask();
		window = new Window("Create Task", "CreateTask.fxml", controller, true);
		window.createWindow();
	}
	
	public void onSelectTaskClicked(ActionEvent actionEvent) throws Exception {
		Utils.closeWindow(actionEvent);
		controller = new CTaskInfo();
		window = new Window("Task Info", "TaskInfo.fxml", controller, true);
		window.createWindow();
	}
	
	public void onCancelClicked(ActionEvent actionEvent){
		Utils.closeWindow(actionEvent);
	}
	
	private String getSelectedTask(){
		return tasksListView.getSelectionModel().getSelectedItem();
	}
}
