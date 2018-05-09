package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Costing;
import application.CostingTotalProject;
import application.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class CCostingResult implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
				
	}
	
	public void onPreviousClicked(ActionEvent actionEvent) throws IOException{
		Utils.closeWindow(actionEvent);
	    
	    Costing costing= new Costing();
		costing.createCostingWindow();
	}
	
	public void onNextClicked(ActionEvent actionEvent) throws IOException{
		Utils.closeWindow(actionEvent);
	    
	    CostingTotalProject costingTotalProject = new CostingTotalProject();
	    costingTotalProject.createCostingTotalProjectWindow();
	}

}
