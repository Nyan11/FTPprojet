package cda.ftp.ihm.components;

import cda.ftp.ihm.components.icons.IconsUtils;
<<<<<<< HEAD
=======
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
>>>>>>> dee033c1cdfbdbd4932a88717ae176f9cea8b62a
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FTPiconView extends VBox {
	
	public static final int ICON_SIZE = 96;
	private String fileName;
	private boolean dir;
<<<<<<< HEAD
=======
	//public BooleanProperty hover;
>>>>>>> dee033c1cdfbdbd4932a88717ae176f9cea8b62a
	
	public FTPiconView(String name, boolean dir){
		super();
		this.fileName = name;
		this.dir = dir;
		generateView();
	}
	
	private void generateView() {
		Text textName = new Text(this.fileName);
		textName.setWrappingWidth(ICON_SIZE);
		textName.setTextAlignment(TextAlignment.CENTER);
		Image image = IconsUtils.getImage(this.fileName, this.dir, ICON_SIZE);
		ImageView imageView = new ImageView(image);
		
		this.getChildren().add(imageView);
		this.getChildren().add(textName);
		
		this.setPadding(new Insets(4));
	}
	
	public String getName() {
		return this.fileName;
	}
	
	public boolean isDir() {
		return this.dir;
	}
}
