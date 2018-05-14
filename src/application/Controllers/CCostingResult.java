package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class CCostingResult implements Initializable{
	Object controller;
	Window window;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
				
	}
	
	public void onPreviousClicked(ActionEvent actionEvent) throws Exception{
		Utils.closeWindow(actionEvent);
	    
		controller= new CCosting();
		window= new Window("Costing","Costing.fxml",controller,true);
		window.createWindow();
	}
	
	public void onNextClicked(ActionEvent actionEvent) throws Exception{
		Utils.closeWindow(actionEvent);
	    
		controller= new CCostingTotalProject();
		window= new Window("Costing Total Project","CostingTotalProject.fxml",controller,true);
		window.createWindow();
	}

}
