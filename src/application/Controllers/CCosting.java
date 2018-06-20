package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
import classes.Cost;
import classes.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CCosting implements Initializable{
	
	private Window window;
	private Project project;
	private String textButton;
	
	private double effort;
	private double duration;
	private double amountOfEmployees;
	
	@FXML private Label amountOfEmployeesLabel;
	@FXML private Label estimatedDurationLabel;
	@FXML private Label estimatedEffortLabel;
	
	public CCosting(Project project, String textButton){
		this.project=project;
		this.textButton=textButton;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		project.initializeCost();
		Cost cost = project.getCost();
		effort=cost.getEffort();
		duration=cost.getDuration();
		amountOfEmployees=cost.getAmountOfEmployees();
		
		amountOfEmployeesLabel.setText(Double.toString(amountOfEmployees));
		estimatedDurationLabel.setText(Double.toString(duration));
		estimatedEffortLabel.setText(Double.toString(effort));
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
	
	
	public void onSaveClicked(ActionEvent actionEvent){
		
	}
	
	public void onCalculateTotalCostClicked(ActionEvent actionEvent){
		
	}
	
	public void onCancelClicked(ActionEvent actionEvent){
		
	}

	

}
