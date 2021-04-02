package cda.ftp.ihm.components;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class FTPstatusView extends ScrollPane {
	HBox list;
	Button closeButton;

	public FTPstatusView() {
		super();
		generateView();
	}
	
	private void generateView() {
		closeButton = new Button("X");
		closeButton.setAlignment(Pos.CENTER_LEFT);
		
		list = new HBox();
		
		this.fitToWidthProperty().set(true);
		this.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		this.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		HBox.setHgrow(this, Priority.ALWAYS);
		

		this.setContent(list);
		
		closeButton.setOnAction(e -> {
			list.getChildren().clear();
		});
	}

	public void addInfo(boolean download, String name, ReadOnlyDoubleProperty progress) {
		if(list.getChildren().size() == 0) {
			list.getChildren().add(closeButton);
		}
		list.getChildren().add(1, new FTPstatusInfoView(download, name, progress, list));
		this.setContent(list);
		
	}
	
	/* to desactivate the focus on the app, really hacky */
	public void requestFocus() { }
}
