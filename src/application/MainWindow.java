package application;
	
import application.Controllers.CMainWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainWindow {
	
	
	public void createProjectWindow() throws Exception{
		
		Stage windowMainWindow = new Stage();
		windowMainWindow.setTitle("Main Menu");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
		loader.setController(new CMainWindow());
		
		windowMainWindow.setMaximized(true);
		windowMainWindow.setMinHeight(650);
		windowMainWindow.setMinWidth(900);
		
		windowMainWindow.setOnCloseRequest(e -> {
			e.consume();
			Utils.closeProgram(windowMainWindow);
		});
		
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		windowMainWindow.setScene(scene);
		windowMainWindow.show();
	}
	
	
}
