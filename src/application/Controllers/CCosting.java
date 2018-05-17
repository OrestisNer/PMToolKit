package application.Controllers;

import application.Utils;
import application.Window;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class CCosting {
	
	private Window window;
		
	public void onCostClicked(ActionEvent actionEvent) throws Exception{
	    Stage stage  = Utils.getStageFromEvent(actionEvent);
	    window= new Window(stage);
	    window.changeScene("CostingResult.fxml", this);	    
	}
	
	public void onPreviousClicked(ActionEvent actionEvent) throws Exception{
		Stage stage  = Utils.getStageFromEvent(actionEvent);
	    window= new Window(stage);
	    window.changeScene("Costing.fxml", this);	    
	}
	
	public void onNextClicked(ActionEvent actionEvent) throws Exception{
		Stage stage  = Utils.getStageFromEvent(actionEvent);
	    window= new Window(stage);
	    window.changeScene("CostingTotalProject.fxml", this);
	}
	
	public void onCalculateTotalCostClicked(){
		
	}
	
	public void onSaveClicked(ActionEvent actionEvent){
		
	}

}
