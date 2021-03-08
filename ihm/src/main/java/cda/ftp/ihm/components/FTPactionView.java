package cda.ftp.ihm.components;

import cda.ftp.client.logic.FTPinterface;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class FTPactionView extends HBox {
	
	private final static int MIN_WIDTH = 120;
	private static Label name;
	private static Label info;
	private static FTPstatusView statusInfos;
	
	public FTPactionView() {
		super();
		generateView();
	}

	private void generateView() {
		HBox buttonsBox = new HBox();
		Button downloadButton = new Button("Download");
		Button uploadButton = new Button("Upload");
		
		GridPane labelGrid = new GridPane();
		
		statusInfos = new FTPstatusView();
		

		name = new Label("");
		info = new Label("");
		
		labelGrid.add(name, 0, 0);
		labelGrid.add(info, 0, 1);
		labelGrid.add(buttonsBox, 1, 0, 1, 2);
		labelGrid.add(statusInfos, 0, 2, 2, 1);
		
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
		
		downloadButton.setOnAction(e -> {
			FTPinterface.download();
		});
		
		uploadButton.setOnAction(e -> {
			FTPinterface.upload();
		});
	}
	
	public static void setName(String name) {
		FTPactionView.name.setText(name);
	}
	
	public static void setFolder() {
		FTPactionView.info.setText("Dossier");
	}
	
	public static void setSize(long size) {
		double returnSize = size;
		String[] unit = {"o", "ko", "Mo", "Go", "To"};
		int i = 0;
		String number;
		while(returnSize/1000 > 1) {
			i++;
			returnSize = returnSize/1000;
		}
		number = String.valueOf(returnSize);
		number = (number.length() > 7) ? number.substring(0, 7): number;
		FTPactionView.info.setText(number + " " + unit[i]);
	}
	
	public static void clearText() {
		FTPactionView.name.setText("");
		FTPactionView.info.setText("");
	}

	public void updateView() {
		clearText();
	}
	
	public void addStatusInfos(boolean download, String name, ReadOnlyDoubleProperty progress) {
		statusInfos.addInfo(download, name, progress);
	}
	
	
}
