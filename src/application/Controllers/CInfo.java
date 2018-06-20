package application.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Utils;
import classes.Activity;
import classes.Project;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CInfo implements Initializable {
	
	//ProjectInfo.fxml
	private @FXML Label nameLabel;
	private @FXML Label categoryLabel;
	private @FXML Label klocLabel; // lines of code 
	private @FXML Label estimatedDurationLabel;
	private @FXML Label estimatedManmonthsLabel;
	private @FXML Label currentManmonthsLabel;
	private @FXML Label completedPercentLabel;
	private @FXML Label totalCostLabel;
	private @FXML Label currentCostLabel;
	private @FXML Button showTaskButton;
	
	private Project project;
	
	public CInfo(Project project){
		this.project=project;
	}
	
	public void onBackClicked(ActionEvent actionEvent) throws Exception{
		Utils.closeWindow(actionEvent);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameLabel.setText(project.getName());
		categoryLabel.setText(project.getCategory());
		klocLabel.setText(Integer.toString(project.getKloc()));
		//estimatedDurationLabel.setText(project.getEstimatedDuration());
		//estimatedManmonthsLabel.setText(project.getEstimatedManmonths());
		//currentManmonthsLabel.setText(project.getEstimatedManmonths());
		//completedPercentLabel.setText(project.getCompletedPercent());
		//totalCostLabel.setText(project.getTotalCost());
		//currentCostLabel.setText(project.getCurrnetCost());
	}
	
	//Show Tasks button
	public void onShowTaskClicked(){
		ListView<String> taskListView= new ListView<String>();
		Button backButton = new Button("  Back  ");
		ArrayList<String> activitiesids  = project.getActivitiesIds();
		ArrayList<Activity> activities= Utils.getActivitiesFromID(activitiesids);
		activitiesids.clear();
		for(Activity act : activities){
			activitiesids.add(act.getName());
		}

		ObservableList<String> list =  FXCollections.observableArrayList(activitiesids);
		
		taskListView.setItems(list);
		BorderPane.setAlignment(taskListView,Pos.CENTER);
		BorderPane.setAlignment(backButton, Pos.BOTTOM_CENTER);
		BorderPane layout = new BorderPane(taskListView,null,null,backButton,null);
		BorderPane.setMargin(taskListView, new Insets(15,15,15,15));

        Scene scene = new Scene(layout, 400, 200);
        
        Stage window = new Stage();
        window.setTitle("Tasks");
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
        
        backButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				window.close();
			}
        });
	}
	
	//Show Employees button
	public void onShowEmployeeClicked(){
		ListView<String> employeeListView= new ListView<String>();
		Button backButton = new Button("  Back  ");
		ArrayList<User> employees  = Utils.getEmployeesFromUsername(project.getEmployees());
		ArrayList<String> empInfo = new ArrayList<String>();
		for(User emp : employees){
			empInfo.add(emp.getName()+"-"+emp.getSpecialty());
		}

		ObservableList<String> list =  FXCollections.observableArrayList(empInfo);
		
		employeeListView.setItems(list);
		
		BorderPane.setAlignment(employeeListView,Pos.CENTER);
		BorderPane.setAlignment(backButton, Pos.BOTTOM_CENTER);
		BorderPane layout = new BorderPane(employeeListView,null,null,backButton,null);
		BorderPane.setMargin(employeeListView, new Insets(15,15,15,15));
        Scene scene = new Scene(layout, 400, 200);
        
        
        Stage window = new Stage();
        
       
        window.setTitle("Employees");
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);

        window.show();
        
        backButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				window.close();
			}
        });
	}
}
