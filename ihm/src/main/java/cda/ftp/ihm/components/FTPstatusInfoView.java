package cda.ftp.ihm.components;

import cda.ftp.ihm.components.specials.IconsUtils;
import cda.ftp.ihm.components.specials.InfosUtils;
import javafx.beans.binding.Bindings;
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
	public static final int CLOSE_SIZE = 12;
	
	public FTPstatusInfoView(boolean download, String name, ReadOnlyDoubleProperty progress, HBox list) {
		super();
		generateView(download, name, progress, list);
	}
	
	private void generateView(boolean download, String name, ReadOnlyDoubleProperty progress, HBox list) {
		Label labelName = new Label(name);
		Image image = InfosUtils.getImage(download, ICON_SIZE);
		Image closeImageBlack = InfosUtils.getClose(true, CLOSE_SIZE);
		Image closeImageRed = InfosUtils.getClose(false, CLOSE_SIZE);
		ImageView imageView = new ImageView(image);
		ImageView closeImageView = new ImageView(closeImageBlack);
		VBox infos = new VBox();
		HBox topBox = new HBox();
		ProgressBar progressBar = new ProgressBar(0);
		progressBar.progressProperty().bind(progress);

        progressBar.setPrefSize(4000, 12);
        
        
        HBox.setHgrow(infos, Priority.ALWAYS);
        HBox.setHgrow(progressBar, Priority.ALWAYS);
        labelName.setPrefWidth(4000);
        this.setPadding(new Insets(0,4,0,4));
        this.setSpacing(4);
        
        topBox.getChildren().add(labelName);
        topBox.getChildren().add(closeImageView);
		
		infos.getChildren().add(topBox);
		infos.getChildren().add(progressBar);
		
		this.getChildren().add(imageView);
		this.getChildren().add(infos);
		this.setMinWidth(200);
		
		closeImageView.imageProperty().bind(Bindings
				.when((closeImageView.hoverProperty()))
				.then(closeImageRed)
				.otherwise(closeImageBlack)
		);
		
		closeImageView.setOnMouseClicked(e -> {
			list.getChildren().remove(this);
			if(list.getChildren().size() <= 1) {
				list.getChildren().clear();
			}
		});
	}

}
