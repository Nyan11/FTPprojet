package cda.ftp.ihm.components;


import cda.ftp.ihm.FTPinterface;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class FTPdirView extends HBox implements Runnable {
	
	TextField currentPath;
	Button previousButton;
	String path;
	
	public FTPdirView() {
		super();
		generateView();
		previousAction();
		updateView();
	}
	
	private void generateView() {
		currentPath = new TextField();
		previousButton = new Button("←");
		
		this.getChildren().add(previousButton);
		this.getChildren().add(currentPath);
		this.setPadding(new Insets(4, 16, 4, 16));
		this.setSpacing(16);
		HBox.setHgrow(currentPath, Priority.ALWAYS);
	}
	
	public void updateView() {
		//currentPath.textProperty().bind(observable);
		this.path = FTPinterface.pwd();
		this.currentPath.setText(this.path);
	}
	
	private void previousAction() {
		previousButton.setOnMouseClicked(e -> {
			FTPinterface.cd("..");
		});
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
