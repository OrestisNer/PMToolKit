package application.Controllers;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import application.Utils;
import application.Window;
import classes.Calendar;
import classes.Project;
import classes.Task;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CTasks implements Initializable{
	
	private Window window;
	private Project project;
	private String buttonText;
	private User employee;
	private ArrayList<Task> tasks;
	private ArrayList<User> employees;
	private ArrayList<Task> prerequisites;
	
	//Tasks.fxml
	@FXML private ListView<Task> tasksListView;
	@FXML private Button createTaskButton;
	@FXML private Button selectTaskButton;
	//CreateTask.fxml
	@FXML private ListView<Task> prerequisitesListView;
	@FXML private ListView<User> employeesListView;
	@FXML private DatePicker startingDatePicker;
	@FXML private TextField averageTimeField;
	@FXML private TextField bestTimeField;
	@FXML private TextField worstTimeField;
	@FXML private TextField nameField;
	@FXML private CheckBox finalCheckbox;
	@FXML private Label estimatedTimeLabel;
	@FXML private TextArea descriptionArea;
	//TaskInfo.fxml
	@FXML private ListView<String> prerequisitesInfoListView;
	@FXML private ListView<String> employeesInfoListView;
	
	public CTasks(Project project, User employee,  String buttonText) {
		this.project=project;
		this.employee=employee;
		tasks = employee.getUnfinishedTasks();
		this.buttonText = buttonText;
	}
	
	public void setButtonText(String btnText) {
		this.buttonText = btnText;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//if the button clicked is Tasks from Main Menu
		if(buttonText.equals("Tasks")) {
			System.out.println("AOU");
			fillTaskListView();
		//if the button clicked is Create Task from Tasks window
		}else if(buttonText.equals("Create Task")) {
			fillEmployeesListView();
			setTextProperties();
			
			prerequisitesListView.setDisable(true);
			employeesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			prerequisitesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//if the button clicked is Select Task from Tasks Window
		}else if(buttonText.equals("Select Task")){
			
		}
	}
	
	//Tasks window Methods
	
	//Create task button 
	public void onCreateTaskClicked(ActionEvent actionEvent) throws Exception {
		Stage stage = Utils.getStageFromEvent(actionEvent);
		window = new Window(stage);
		this.setButtonText(createTaskButton.getText());
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
		
	//Create Task window Methods 
	
	/*Cancel or back button clicked
	 * 
	 * Method for the cancel button on Create Task window and for the back button
	 * on Task Info window.
	 */
	public void onCancel_BackClicked(ActionEvent actionEvent) throws Exception{
		Stage stage = Utils.getStageFromEvent(actionEvent);
		window = new Window(stage);
		this.setButtonText("Tasks");
		window.changeScene("Tasks.fxml",this);
	}
	
	public void onPickDate(ActionEvent actionEvent){
		System.out.println(startingDatePicker.getValue());
		fillPrerequisitesListView(startingDatePicker.getValue());
	}
	
	public void onCreateClicked(ActionEvent actionEvent) throws IOException{
		if(hasAppropriateArgs()){
			createTask();
			Utils.createInfoAlert("Proccess", "You successfuly create a task");
			Stage stage = Utils.getStageFromEvent(actionEvent);
			this.setButtonText("Tasks");
			window = new Window(stage);
			window.changeScene("Tasks.fxml", this);
		}else{
			Utils.createInfoAlert("Inforamtion", "You have to fill in all the fields");
		}
	}
	
	private void fillTaskListView(){
		ObservableList<Task> taskList = FXCollections.observableArrayList(tasks);
		tasksListView.setItems(taskList);
		tasksListView.setCellFactory(param -> new ListCell<Task>() {
		    @Override
		    protected void updateItem(Task task, boolean empty) {
		        super.updateItem(task, empty);

		        if (task == null || task.getName() == null) {
		            setText(null);
		        } else {
		            setText(task.getName());
		        }
		    }
		});
	}
	
	private void fillEmployeesListView(){
		employees= Utils.getEmployeesFromFile();
		ObservableList<User> list = FXCollections.observableArrayList(employees);
		employeesListView.setItems(list);
		employeesListView.setCellFactory(param -> new ListCell<User>() {
		    @Override
		    protected void updateItem(User employee, boolean empty) {
		        super.updateItem(employee, empty);

		        if (employee == null || employee.getName() == null || employee.getName().equals(" ")) {
		            setText(null);
		        } else {
		            setText(employee.getName()+"-"+employee.getSpeciality());
		        }
		    }
		});
		
	}
	
	private void fillPrerequisitesListView(LocalDate startingDate){
		//ArrayList<Task> tasks = project.getTasks();
		ArrayList<Task> prerequisitesTasks = getPrerequisitesTasks(tasks,startingDate) ;
		this.prerequisitesListView.getItems().clear();
		if(!prerequisitesTasks.isEmpty()){
			this.prerequisitesListView.setDisable(false);
			ObservableList<Task> list = FXCollections.observableArrayList(prerequisitesTasks);
			prerequisitesListView.setItems(list);
			prerequisitesListView.setCellFactory(param -> new ListCell<Task>() {
				@Override
				protected void updateItem(Task task, boolean empty) {
					super.updateItem(task, empty);

					if (task == null || task.getName() == null || task.getName().equals(" ")) {
						setText(null);
					} else {
						setText(task.getName());
					}
				}
			});
		}else{
			this.prerequisitesListView.setDisable(true);
		}
	}
	
	private ArrayList<Task> getPrerequisitesTasks(ArrayList<Task> tasks,LocalDate startingDate){
		ArrayList<Task> prerequisitesTasks= new ArrayList<Task>();
		for(Task task: tasks){
			if(Calendar.isBefore(task.getDeadLine(), startingDate)){
				System.out.println("DeadLine:"+task.getDeadLine());
				System.out.println("StartingDate: "+startingDate);
				prerequisitesTasks.add(task);
			}
		}
		return prerequisitesTasks;
	}
	private void setTextProperties(){
		
		bestTimeField.textProperty().addListener((observable, oldValue, newValue) -> {
			if(!worstTimeField.getText().isEmpty()){
				try{
					Integer average = (Integer.parseInt(bestTimeField.getText()) + Integer.parseInt(worstTimeField.getText())) / 2;
					averageTimeField.setText(average.toString());
				}catch(NumberFormatException e){
					//do nothing
				}
			}
		});
		
		worstTimeField.textProperty().addListener((observable, oldValue, newValue) -> {
			if(!bestTimeField.getText().isEmpty()){
				try{	
					int average = (Integer.parseInt(bestTimeField.getText()) + Integer.parseInt(worstTimeField.getText())) / 2;
				    averageTimeField.setText(average+"");
				}catch(NumberFormatException e){
					//do nothing
				}
			}
		});
		
		averageTimeField.textProperty().addListener((observable, oldValue, newValue) -> {
			estimatedTimeLabel.setText(averageTimeField.getText());
		});
		
	}
	
	private boolean hasAppropriateArgs(){
		ObservableList<User> employees = employeesListView.getSelectionModel().getSelectedItems();
		if(nameField.getText().isEmpty() || startingDatePicker.getValue().equals(null) || employees.isEmpty()
				|| estimatedTimeLabel.getText().equals("") || descriptionArea.getText().equals(""))
			return false;
		return true;
	}
	
	private void createTask() throws IOException{
		ObservableList<User> obsEmployees = employeesListView.getSelectionModel().getSelectedItems();
		String taskName=nameField.getText();
		ObservableList<Task> obsPrerequisites = prerequisitesListView.getSelectionModel().getSelectedItems();
		int estimatedTime = Integer.parseInt(estimatedTimeLabel.getText());
		String description = descriptionArea.getText();
		LocalDate startingDate = startingDatePicker.getValue();
		
		ArrayList<User> employees = (ArrayList<User>) obsEmployees.stream().collect(Collectors.toList());
		ArrayList<Task> prerequisites;
		if(!obsPrerequisites.isEmpty())
			prerequisites = (ArrayList<Task>) obsPrerequisites.stream().collect(Collectors.toList());
		else
			prerequisites=null;
		for(Project p : employee.getProjects()){
			System.out.println(p.getName());
		}
		Task task = new Task(taskName,prerequisites,employees,estimatedTime,description,startingDate,project);
		project.addTask(task);
		System.out.println(project.getTasks().size());
		//Utils.saveProjectChanges(project);
		System.out.println("Project:"+project.getName()+" got task : "+task.getName());
		
		for(User employee : employees){
			//if(employee instanceof Employee){
				employee.addTask(task);
				System.out.println(employee.getName());
				Utils.saveEmployeeChanges(employee);
			//}
		}
		
	}
	
	
}
