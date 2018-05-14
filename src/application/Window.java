package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Window {
	
	private String title;
	private String fxmlFilename;
	private Object controller;
	private boolean modality;
	private boolean maximized;
	private boolean closeProgramOnCloseRequest;
	
	
	public Window(String title,String fxmlFilename,Object controller,boolean modality){
		this.title= title;
		this.fxmlFilename=fxmlFilename;
		this.controller=controller;
		this.modality=modality;
		maximized=false;
	}
	
	public Window(String title,String fxmlFile,Object controller,boolean maximized,boolean closeProgramOnCloseRequest){
		this.title=title;
		this.fxmlFilename=fxmlFile;
		this.controller=controller;
		this.modality=false;
		this.maximized=maximized;
		this.closeProgramOnCloseRequest=closeProgramOnCloseRequest;
	}
	
	public void createWindow() throws Exception{
		Stage window = new Stage();
		
	    if(modality)
			window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);

		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilename));
		
	    window.setMaximized(maximized);
		
		if(closeProgramOnCloseRequest){
			window.setOnCloseRequest(e -> {
				e.consume();
				Utils.closeProgram(window);
			});
		}
		
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root);

		window.setScene(scene);
		window.show();
	}

}
