package cda.ftp.ihm.components;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class FTPactionView extends HBox {
	
	private final static int MIN_WIDTH = 120;
	public Label downSize;
	public Label upSize;
	
	public FTPactionView() {
		super();
		generateView();
	}

	private void generateView() {
		HBox buttonsBox = new HBox();
		Button downloadButton = new Button("Download");
		Button uploadButton = new Button("Upload");
		
		GridPane labelGrid = new GridPane();

		Label downLabel = new Label("Total down : ");
		Label upLabel = new Label("Total up : ");
		downSize = new Label("0");
		upSize = new Label("0");
		
		labelGrid.add(downLabel, 0, 0);
		labelGrid.add(upLabel, 0, 1);
		labelGrid.add(downSize, 1, 0);
		labelGrid.add(upSize, 1, 1);
		labelGrid.add(buttonsBox, 2, 0, 1, 2);
		
		labelGrid.setPadding(new Insets(4, 8, 4, 8));
		HBox.setHgrow(labelGrid, Priority.ALWAYS);
		GridPane.setHgrow(buttonsBox, Priority.ALWAYS);
		
		
		downloadButton.setMinWidth(MIN_WIDTH);
		uploadButton.setMinWidth(MIN_WIDTH);
		
		GridPane.setHalignment(buttonsBox, HPos.RIGHT);
		buttonsBox.getChildren().add(uploadButton);
		buttonsBox.getChildren().add(downloadButton);
		buttonsBox.setAlignment(Pos.CENTER_RIGHT);
		buttonsBox.setSpacing(16);
		
		this.getChildren().add(labelGrid);
	}
}