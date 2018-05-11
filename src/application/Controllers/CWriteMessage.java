package application.Controllers;

import application.Utils;
import javafx.event.ActionEvent;

public class CWriteMessage {
	
	public void onSendMessageClicked() {
		System.out.println("Message Sent");
	}
	
	public void onCancelClicked(ActionEvent event) {
		Utils.closeWindow(event);
	}
}
