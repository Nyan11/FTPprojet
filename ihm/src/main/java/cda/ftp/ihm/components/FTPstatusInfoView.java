package cda.ftp.ihm.components;

import cda.ftp.ihm.components.specials.IconsUtils;
import cda.ftp.ihm.components.specials.InfosUtils;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FTPstatusInfoView extends HBox {
	
	public static final int ICON_SIZE = 32;
	
	public FTPstatusInfoView(boolean download, String name, ReadOnlyDoubleProperty progress) {
		super();
		generateView(download, name, progress);
	}
	
	private void generateView(boolean download, String name, ReadOnlyDoubleProperty progress) {
		Label labelName = new Label(name);
		Image image = InfosUtils.getImage(download, ICON_SIZE);
		ImageView imageView = new ImageView(image);
		VBox infos = new VBox();
		ProgressBar progressBar = new ProgressBar(0);
		progressBar.progressProperty().bind(progress);

        progressBar.setPrefSize(4000, 12);
        
        
        HBox.setHgrow(infos, Priority.ALWAYS);
        HBox.setHgrow(progressBar, Priority.ALWAYS);
        this.setPadding(new Insets(2,16,2,16));
        this.setSpacing(20);
        
        
		
		infos.getChildren().add(labelName);
		infos.getChildren().add(progressBar);
		
		this.getChildren().add(imageView);
		this.getChildren().add(infos);
	}

}
