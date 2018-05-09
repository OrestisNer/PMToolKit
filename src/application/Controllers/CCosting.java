package application.Controllers;

import java.io.IOException;

import application.CostingResult;
import application.Utils;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class CCosting {
	
	Button button;
	
	
	public void onCostClicked(ActionEvent actionEvent) throws IOException{
		
		Utils.closeWindow(actionEvent);
	    
		CostingResult costingResult= new CostingResult();
		costingResult.createCostingResultWindow();
	}

}
