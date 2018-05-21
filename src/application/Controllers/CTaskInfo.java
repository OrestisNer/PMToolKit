package application.Controllers;

import application.Utils;
import application.Window;
import javafx.event.ActionEvent;

public class CTaskInfo {
	
	private Object controller;
	private Window window;
	
	public void onBackClicked(ActionEvent actionEvent) throws Exception{
		Utils.closeWindow(actionEvent);
		controller = new CTasks();
		window = new Window("Tasks", "Tasks.fxml", controller, true);
		window.createWindow();
	}

}
