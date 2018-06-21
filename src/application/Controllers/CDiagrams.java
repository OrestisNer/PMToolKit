package application.Controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.jfree.chart.JFreeChart;

import application.AlertUtils;
import classes.Diagram;
import classes.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class CDiagrams implements Initializable{
	
	@FXML private ComboBox<String> diagramsComboBox;
	@FXML private Button generateButton;
	@FXML private VBox diagramVbox;
	
	private Diagram diagram;
	private Project project;
	
	public CDiagrams(Project project){
		this.project= project;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		diagramsComboBox.getItems().addAll("Gantt","Pert");
	}
	
	public void enableButton() {
		if(diagramsComboBox.getValue().equals("Gantt")) {
			generateButton.setDisable(false);
			generateButton.setText("Generate Gantt");
		}else {
			generateButton.setDisable(false);
			generateButton.setText("Generate Pert");
		}
	}
	
	public void onGenerateClicked(ActionEvent actionEvent) throws Exception {
		if(diagramsComboBox.getValue().equals("Gantt")) {
			diagram = new Diagram(project);
			JFreeChart gantt=diagram.createGanttDiagram();
			if(gantt!=null){
				Diagram.openGanttWindow(gantt);
				AlertUtils.closeWindow(actionEvent);
				Alert alert=AlertUtils.createCustomConfirmationAlert("Save as" , null, "Do you want to save diagram as .jpg?");
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get().getText().equals("Yes")){
					String filename = AlertUtils.createInputDialog("Filename", null , "Please enter the filename");
					filename+=".jpg";
					if(!filename.equals(""))
						diagram.saveChart(gantt,filename);
				}	
			}
		}else {
			AlertUtils.createErrorAlert("Error", null, "Oops... Something went wrong.");
		}
	}
	
	public void onCancelClicked(ActionEvent actionEvent){
		AlertUtils.closeWindow(actionEvent);
	}
}
