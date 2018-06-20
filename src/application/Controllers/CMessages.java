package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.Utils;
import application.Window;
import classes.Message;
import classes.Project;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CMessages implements Initializable{
	
	private Window window;
	private User employee;
	private String buttonText;
	private Project project;
	
    //Messages.fxml
	@FXML private ListView<Message> messagesListView;
	@FXML private Button composeButton;
	@FXML private Button selectButton;
	@FXML private Button cancelButton;
	
	//WriteMessage.fxml
	@FXML private TextField subjectField;
	@FXML private TextArea messageTextArea;
	@FXML private ComboBox<User> employeesComboBox;
	@FXML private CheckBox selectAllCheckBox;
	@FXML private Button sendMessageButton;
	@FXML private Button cancelComposeButton;
	
	//ShowMessage.fxml
	@FXML private Label senderLabel;
	@FXML private Label subjectLabel;
	@FXML private ComboBox<String> receiversComboBox;
	@FXML private TextArea descriptionArea;
	@FXML private Button cancelSelectButton;
	
	public CMessages(User employee,Project project,String buttonText){
		this.employee=employee;
		this.project=project;
		this.buttonText=buttonText;
	}

	private void setButtonText(String btnText) {
		this.buttonText = btnText;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//if compose button is clicked
		if(buttonText.equals(composeButton.getText())){
			this.setProperties();
			this.fillEmployeeComboBox();
		//if select button is clicked
		}else if(buttonText.equals(selectButton.getText())){
			Message message = messagesListView.getSelectionModel().getSelectedItem();
			this.fillReceiversComboBox(message);
			this.fillMessageInfo(message);			
		}else
			this.fillMessageList(employee);
	}
	
	//Messages.fxml methods
	
	//Compose button
	public void onComposeClicked(ActionEvent actionEvent) throws Exception{	
		Stage stage  = Utils.getStageFromEvent(actionEvent);
	    window= new Window(stage);
	    this.setButtonText(composeButton.getText());
	    window.changeScene("WriteMessage.fxml", this);
	}
	
	//Select button
	public void onSelectClicked(ActionEvent actionEvent) throws IOException{
		Message message=messagesListView.getSelectionModel().getSelectedItem();
		if(message!=null){
			Stage stage  = Utils.getStageFromEvent(actionEvent);
			window= new Window(stage);
			this.setButtonText(selectButton.getText());
			window.changeScene("ShowMessage.fxml", this);
		}else{
			Utils.createInfoAlert("Information", "Select message first");
		}
	}
	
	//Cancel button
	public void onCancelClicked(ActionEvent actionEvent) {
		Utils.closeWindow(actionEvent);
	}
	
	//Fills messagesListView 
	private void fillMessageList(User employee){
		ArrayList<Message> messages = employee.getMessages();
		messagesListView.getItems().clear();
		ObservableList<Message> list = FXCollections.observableArrayList(messages);
		messagesListView.setItems(list);
		messagesListView.setCellFactory(param -> new ListCell<Message>() {
		    @Override
		    protected void updateItem(Message message, boolean empty) {
		        super.updateItem(message, empty);

		        if (message == null) {
		            setText(null);
		        } else {
		            if(message.isSender(employee)){
		            	if(message.getReceivers().size()==1){
		            		User receiver=message.getReceivers().get(0);
		            		setText("To: "+receiver.getName()+" Subject: "+message.getSubject());
		            	}else{
		            		int numberOfReceivers= message.getNumberOfReceivers();
		            		setText("To: "+numberOfReceivers+" users Subject: "+message.getSubject());
		            	}
		            }else{
		            	User sender=message.getSender();
		            	setText("From: "+sender.getName()+" Subject: "+message.getSubject());
		            }
		        }
		    }
		});
	}
	
	//WriteMessage.fxml methods
	
	//Send message button
	public void onSendMessageClicked(ActionEvent actionEvent) throws IOException {
		createMessage();
		Utils.createInfoAlert("Message", "You successfully send the message");
		
		Stage stage = Utils.getStageFromEvent(actionEvent);
		window = new Window(stage);
		this.setButtonText(sendMessageButton.getText());
		window.changeScene("Messages.fxml", this);
	}
	
	//Creates a message Object
	private void createMessage() throws IOException {
		ArrayList<User> receivers= new ArrayList<User>();
		
		if(selectAllCheckBox.isSelected()){
			ArrayList<String> usersnames=project.getEmployees();
			receivers = Utils.getEmployeesFromUsername(usersnames);
		}else{
			receivers.add(employeesComboBox.getValue());
		}
		
		String message = messageTextArea.getText();
		String subject = subjectField.getText();
		Message messageObj = new Message(employee,receivers,subject,message);
		
		employee.addMessage(messageObj);
		Utils.saveEmployeeChanges(employee);
		for(User receiver: receivers){
			receiver.addMessage(messageObj);
			Utils.saveEmployeeChanges(receiver);
		}
	}
	
	//Cancel button 
	public void onCancelComposeClicked(ActionEvent actionEvent) throws IOException {
		Stage stage  = Utils.getStageFromEvent(actionEvent);
	    window= new Window(stage);
	    this.setButtonText(cancelComposeButton.getText());
	    window.changeScene("Messages.fxml", this);
	}
	
	//SelectAll CheckBox
	public void onSelectAllClicked(ActionEvent actionEvent){
		if(selectAllCheckBox.isSelected())
			employeesComboBox.setDisable(true);
		else
			employeesComboBox.setDisable(false);
	}
	
	//Fills employeesComboBox
	private void fillEmployeeComboBox(){
		ArrayList<User> employees = Utils.getEmployeesFromUsername(project.getEmployees());
		
		ObservableList<User> list = FXCollections.observableArrayList(employees);
		employeesComboBox.getItems().clear();
		employeesComboBox.setItems(list);
		Callback<ListView<User>, ListCell<User>> factory = lv -> new ListCell<User>() {
		    @Override
		    protected void updateItem(User employee, boolean empty) {
		        super.updateItem(employee, empty);
		        setText(empty ? null : employee.getName());
		    }
		};
		employeesComboBox.setCellFactory(factory);
		//employeesComboBox.setButtonCell(factory.call(null));
	}
	
	//Enables sendMessageButton if the user has typed a message
	private void setProperties(){
		sendMessageButton.setDisable(true);
		messageTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
			sendMessageButton.setDisable(false);
		});
	}
	
	//ShowMessage.fxml methods
	
	//Cancel Button
	public void onCancelSelectClicked(ActionEvent actionEvent) throws IOException {
		Stage stage  = Utils.getStageFromEvent(actionEvent);
	    window= new Window(stage);
	    this.setButtonText(cancelSelectButton.getText());
	    window.changeScene("Messages.fxml", this);
	}
	
	//Fills the info of the message
	private void fillMessageInfo(Message message) {
		this.senderLabel.setText(message.getSender().getName()); 
		this.subjectLabel.setText(message.getSubject());;
		this.descriptionArea.setText(message.getMessage());
	}
	
	//Fills receiversComboBox
	private void fillReceiversComboBox(Message message) {
		ArrayList<User> receivers = message.getReceivers();
		ArrayList<String> receiversNames = new ArrayList<>();
		
		Iterator<User> iter = receivers.iterator();
		
		while(iter.hasNext()) {
			User emp = iter.next();
			
			if (!emp.getUsername().equalsIgnoreCase(employee.getUsername()))
				receiversNames.add(emp.getName());
			else
				receiversNames.add("Me");
		}
		
		ObservableList<String> list = FXCollections.observableArrayList(receiversNames);
		receiversComboBox.getItems().clear();
		receiversComboBox.setItems(list);
		Callback<ListView<String>, ListCell<String>> factory = lv -> new ListCell<String>() {
			@Override
			protected void updateItem(String receiverName , boolean empty) {
				super.updateItem(receiverName, empty);
				setText(empty ? null : receiverName);
			}
		};
		receiversComboBox.setCellFactory(factory);
	}
}
