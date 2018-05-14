package application.Controllers;

import java.io.IOException;

import application.Utils;
import application.Window;
import javafx.event.ActionEvent;

public class CCosting {
	
	private Object controller;
	private Window window;
	
	public void onCostClicked(ActionEvent actionEvent) throws Exception{
		
		Utils.closeWindow(actionEvent);
	    
		controller= new CCostingResult();
		window= new Window("Costing Results","CostingResult.fxml",controller,true);
		window.createWindow();
	}

}
