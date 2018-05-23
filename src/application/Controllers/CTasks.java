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
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

public class CTasks implements Initializable{
	
	private Window window;;
	private String buttonText;
	
	//Tasks.fxml
	@FXML private ListView<String> tasksListView;
	@FXML private Button createTaskButton;
	@FXML private Button selectTaskButton;
	//CreateTask.fxml
	@FXML private ListView<String> prerequisitesLV;
	@FXML private ListView<String> employeesLV;
	//TaskInfo.fxml
	@FXML private ListView<String> prerequisitesInfoLV;
	@FXML private ListView<String> employeesInfoLV;
	
	public CTasks(String buttonText) {
		this.buttonText = buttonText;
	}
	
	public void setButtonText(String btnText) {
		this.buttonText = btnText;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//if the button clicked is Tasks from Main Menu
		if(buttonText.equals("Tasks")) {
			for (int i = 0; i < 50; i++) {
				String tasks = "Task " + i;
				tasksListView.getItems().add(tasks);
			}
		//if the button clicked is Create Task from Tasks window
		}else if(buttonText.equals(createTaskButton.getText())) {
			for(int i=0; i <50; i++){
				String tasks = "Task "+i ;
				String employees = "Employee "+i ;
				prerequisitesLV.getItems().add(tasks);
				employeesLV.getItems().add(employees);
			}
			employeesLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			prerequisitesLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//if the button clicked is Select Task from Tasks Window
		}else if(buttonText.equals(selectTaskButton.getText())){
			for(int i = 0; i <50; i++) {
				String tasks = "Task " + i;
				String employees = "Employee " + i;
				prerequisitesInfoLV.getItems().add(tasks);
				employeesInfoLV.getItems().add(employees);
			}
		}
	}
	
	//Tasks window Methods
	
	//Create task button 
	public void onCreateTaskClicked(ActionEvent actionEvent) throws Exception {
		Stage stage = Utils.getStageFromEvent(actionEvent);
		this.setButtonText(createTaskButton.getText());
		window = new Window(stage);
		window.changeScene("CreateTask.fxml", this);
	}
	
	//Select Task button
	public void onSelectTaskClicked(ActionEvent actionEvent) throws Exception {
		Stage stage = Utils.getStageFromEvent(actionEvent);
		this.setButtonText(selectTaskButton.getText());
		window = new Window(stage);
		window.changeScene("TaskInfo.fxml", this);
	}
	
	//Cancel button 
	public void onCancelClicked(ActionEvent actionEvent){
		Utils.closeWindow(actionEvent);
	}
	
	private String getSelectedTask(){
		return tasksListView.getSelectionModel().getSelectedItem();
	}
	
	//Create Task window Methods 
	
	/*Cancel or back button clicked
	 * 
	 * Method for the cancel button on Create Task window and for the back button
	 * on Task Info window.
	 */
	public void onCancel_BackClicked(ActionEvent actionEvent) throws Exception{
		Stage stage = Utils.getStageFromEvent(actionEvent);
		this.setButtonText("Tasks");
		window = new Window(stage);
		window.changeScene("Tasks.fxml", this);
	}
}
