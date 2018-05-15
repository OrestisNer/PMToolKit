package application.Controllers;

import application.Utils;
import application.Window;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class CCosting {
	
	private CCosting controller;
	private Window window;
	
	
	public void onCostClicked(ActionEvent actionEvent) throws Exception{
		
		controller= new CCosting();
	    Stage stage  = Utils.getStageFromEvent(actionEvent);
	    window= new Window(stage);
	    window.changeScene("CostingResult.fxml", controller);	    
	}
	
	public void onPreviousClicked(ActionEvent actionEvent) throws Exception{
	    controller= new CCosting();
		Stage stage  = Utils.getStageFromEvent(actionEvent);
	    window= new Window(stage);
	    window.changeScene("Costing.fxml", controller);	    
	}
	
	public void onNextClicked(ActionEvent actionEvent) throws Exception{	    
		controller= new CCosting();
		Stage stage  = Utils.getStageFromEvent(actionEvent);
	    window= new Window(stage);
	    window.changeScene("CostingTotalProject.fxml", controller);	
	}

}
