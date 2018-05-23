package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class CEvaluation implements Initializable{

	private Window window;
	private String buttonText;
	
	//Evaluation.fxml
	@FXML private ListView<String> employeeListView;
	@FXML private Button nextButton;
	//EvaluateEmployee.fxml
	@FXML private RadioButton poorRadioButton;
	@FXML private RadioButton fairRadioButton;
	@FXML private RadioButton averageRadioButton;
	@FXML private RadioButton goodRadioButton;
	@FXML private RadioButton excellentRadioButton;
	private ToggleGroup evaluationToggleGroup;
	
	public CEvaluation(String buttonText) {
		this.buttonText = buttonText;
	}
	
	public void setButtonText(String btnText) {
		this.buttonText = btnText;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//if the button clicked is Evaluation from Main Menu
		if(buttonText.equals("Evaluation")) {
			for(int i = 0; i<50; i++) {
				String employee = "Employee "+ i ;
				employeeListView.getItems().add(employee);
			}
		//if the button clicked is Next from Evaluation window
		}else if(buttonText.equals(nextButton.getText())) {
			evaluationToggleGroup = new ToggleGroup();
			this.poorRadioButton.setToggleGroup(evaluationToggleGroup);
			this.fairRadioButton.setToggleGroup(evaluationToggleGroup);
			this.averageRadioButton.setToggleGroup(evaluationToggleGroup);
			this.goodRadioButton.setToggleGroup(evaluationToggleGroup);
			this.excellentRadioButton.setToggleGroup(evaluationToggleGroup);
		}
	}
	
	// Evaluation window methods
	
	//Next button
	public void onNextClicked(ActionEvent actionEvent) throws Exception{
		Stage stage = Utils.getStageFromEvent(actionEvent);
		this.setButtonText(nextButton.getText());
		window = new Window(stage);
		window.changeScene("EvaluateEmployee.fxml", this);
	}
	
	//Cancel button
	public void onCancelClicked(ActionEvent event) {
		Utils.closeWindow(event);
	}
	
	//Evaluate Employee window methods
	
	//Submit button
	public void onSubmitClicked() throws Exception{
		
	}

}
