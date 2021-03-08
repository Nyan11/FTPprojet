package cda.ftp.ihm.components;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class FTPstatusView extends TitledPane {
	VBox list;

	public FTPstatusView() {
		super();
		generateView();
	}
	
	private void generateView() {
		ScrollPane scrollPane = new ScrollPane();
		list = new VBox();
		
		scrollPane.fitToWidthProperty().set(true);
		scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		VBox.setVgrow(scrollPane, Priority.ALWAYS);
		
		this.setText("status");
		this.setContent(scrollPane);
		scrollPane.setContent(list);
	}

	public void addInfo(boolean download, String name, ReadOnlyDoubleProperty progress) {
		list.getChildren().add(0, new FTPstatusInfoView(download, name, progress));
		this.setContent(list);
		
	}
}
