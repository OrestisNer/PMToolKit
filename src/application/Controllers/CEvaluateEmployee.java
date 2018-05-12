package application.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class CEvaluateEmployee implements Initializable{
	
	@FXML private RadioButton poorRadioButton;
	@FXML private RadioButton fairRadioButton;
	@FXML private RadioButton averageRadioButton;
	@FXML private RadioButton goodRadioButton;
	@FXML private RadioButton excellentRadioButton;
	private ToggleGroup evaluationToggleGroup;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		evaluationToggleGroup = new ToggleGroup();
		this.poorRadioButton.setToggleGroup(evaluationToggleGroup);
		this.fairRadioButton.setToggleGroup(evaluationToggleGroup);
		this.averageRadioButton.setToggleGroup(evaluationToggleGroup);
		this.goodRadioButton.setToggleGroup(evaluationToggleGroup);
		this.excellentRadioButton.setToggleGroup(evaluationToggleGroup);
	}
	
	public void onSubmitClicked() throws Exception{
		
	}
}
