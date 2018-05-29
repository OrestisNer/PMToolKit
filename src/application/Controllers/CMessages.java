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
	
	//WriteMessage.fxml
	@FXML private TextField subjectField;
	@FXML private TextArea messageTextArea;
	@FXML private ComboBox<User> employeesComboBox;
	@FXML private CheckBox selectAllCheckBox;
	@FXML private Button sendMessageButton;
	
	public CMessages(User employee,Project project,String buttonText){
		this.employee=employee;
		this.project=project;
		this.buttonText=buttonText;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(buttonText.equalsIgnoreCase("Messages") || buttonText.equalsIgnoreCase("Send Message") 
				|| buttonText.equalsIgnoreCase("Cancel")) 
			this.fillMessageList(employee);
		else{
			this.setProperties();
			this.fillEmployeeComboBox(project);
		}
	}
	
	public void onComposeClicked(ActionEvent actionEvent) throws Exception{	
		Stage stage  = Utils.getStageFromEvent(actionEvent);
	    window= new Window(stage);
	    this.buttonText="Compose";
	    window.changeScene("WriteMessage.fxml", this);
	}
	
	public void onCancelClicked(ActionEvent actionEvent) {
		Utils.closeWindow(actionEvent);
	}
	
	public void onSendMessageClicked(ActionEvent actionEvent) throws IOException {
		ArrayList<User> recievers= new ArrayList<User>();
		if(selectAllCheckBox.isSelected()){
			recievers=project.getEmployees();
		}else{
			recievers.add(employeesComboBox.getValue());
		}
		String message = messageTextArea.getText();
		String subject = subjectField.getText();
		Message messageObj = new Message(employee,recievers,subject,message);
		employee.addMessage(messageObj);
		Utils.saveEmployeeChanges(employee);
		for(User reciever: recievers){
			reciever.addMessage(messageObj);
			Utils.saveEmployeeChanges(reciever);
		}
		Utils.createInfoAlert("Message", "You successfully send the message");
		Stage stage  = Utils.getStageFromEvent(actionEvent);
		window= new Window(stage);
		this.buttonText="Send Message";
		window.changeScene("Messages.fxml", this);
		
	}
	
	public void onCancelSyntaxClicked(ActionEvent actionEvent) throws IOException {
		Stage stage  = Utils.getStageFromEvent(actionEvent);
	    window= new Window(stage);
	    this.buttonText="Cancel";
	    window.changeScene("Messages.fxml", this);
	}
	
	public void onSelectClicked(ActionEvent actionEvent){
		
	}
	
	public void onSelectAllClicked(ActionEvent actionEvent){
		if(selectAllCheckBox.isSelected()){
			employeesComboBox.setDisable(true);
		}else{
			employeesComboBox.setDisable(false);
		}
	}
	
	
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
		            	if(message.getRecievers().size()==1){
		            		User reciever=message.getRecievers().get(0);
		            		setText("To: "+reciever.getName()+" Subject: "+message.getSubject());
		            	}else{
		            		int numberOfRecievers= message.getNumberOfRecievers();
		            		setText("To: "+numberOfRecievers+" users Subject: "+message.getSubject());
		            	}
		            }else{
		            	User sender=message.getSender();
		            	setText("From: "+sender.getName()+" Subject: "+message.getSubject());
		            }
		        }
		    }
		});
	}
	
	private void fillEmployeeComboBox(Project project){
		ArrayList<User> employees = project.getEmployees();
		
		Iterator<User> iter = employees.iterator();

		while (iter.hasNext()) {
		    User emp = iter.next();

		    if (emp.getUsername().equalsIgnoreCase(employee.getUsername()))
		        iter.remove();
		}
		
		ObservableList<User> list = FXCollections.observableArrayList(employees);
		employeesComboBox.getItems().clear();
		employeesComboBox.setItems(list);
		Callback<ListView<User>, ListCell<User>> factory = lv -> new ListCell<User>() {

		    @Override
		    protected void updateItem(User employee, boolean empty) {
		        super.updateItem(employee, empty);
		        setText(empty ? null : employee.getUsername());
		    }

		};

		employeesComboBox.setCellFactory(factory);
		//employeesComboBox.setButtonCell(factory.call(null));
	}
	
	
	private void setProperties(){
		sendMessageButton.setDisable(true);
		messageTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
			sendMessageButton.setDisable(false);
		});
	}
}
