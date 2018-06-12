package application.Controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.jfree.chart.JFreeChart;

import application.Utils;
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
	
	public void onGenerateClicked() throws Exception {
		if(diagramsComboBox.getValue().equals("Gantt")) {
			System.out.println("Gantt");
			diagram = new Diagram(project);
			JFreeChart gantt=diagram.createGanttDiagram();
			Diagram.openGanttWindow(gantt);
			Alert alert=Utils.createCustomConfirmationAlert("Save as" , null, "Do you want to save diagram as .jpg?");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get().getText().equals("Yes")){
				String filename = Utils.createInputDialog("Filename", null , "Please enter the filename");
				filename+=".jpg";
				if(!filename.equals(""))
					diagram.saveChart(gantt,filename);
			}	
		}else {
			
		}
	}
	
	public void onCancelClicked(ActionEvent actionEvent){
		Utils.closeWindow(actionEvent);
	}
}
