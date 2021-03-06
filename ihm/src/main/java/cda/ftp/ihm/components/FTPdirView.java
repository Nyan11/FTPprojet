package cda.ftp.ihm.components;


import cda.ftp.client.logic.FTPinterface;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FTPdirView extends HBox {
	
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
		
		currentPath.setEditable(false);
	}
	
	public void updateView() {
		this.path = FTPinterface.pwd();
		this.currentPath.setText(this.path);
	}
	
	private void previousAction() {
		previousButton.setOnMouseClicked(e -> {
			FTPinterface.cd("..");
		});
	}
}
