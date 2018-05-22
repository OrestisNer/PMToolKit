package application.Controllers;

import application.Utils;
import javafx.event.ActionEvent;

public class CInfo {
	
	public void onBackClicked(ActionEvent actionEvent) throws Exception{
		Utils.closeWindow(actionEvent);
	}

}
