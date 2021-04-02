package cda.ftp.ihm.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import cda.ftp.ihm.components.specials.InfosUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FTPaboutView extends VBox {
	
	private final static String FULL_PATH_ICON = "resources/icons/ftp.png";
	
	public FTPaboutView() {
		super();
		generateView();
	}
	
	private void generateView() {
		
		Image image = getImage(128);
		ImageView imageView = new ImageView(image);
		
		this.getChildren().add(imageView);
		
		Label name = new Label("ProjetFTP");
		this.getChildren().add(name);
		Label description = new Label("Description:");
		this.getChildren().add(description);
		Label dis= new Label("Ce programme permet de télécharger et enregistrer des fichiers dans un ihm");
		this.getChildren().add(dis);
		
		Label authors = new Label("Créé par:");
		this.getChildren().add(authors);
		Text meshari= new Text("ALHAWAS MESHARI");
		this.getChildren().add(meshari);
		Text yann= new Text("LE GOFF YANN");
		this.getChildren().add(yann);
		
		Label files = new Label("© PixelPerfect - Freepik - ArcIcons");
		this.getChildren().add(files);
		
		this.setPadding(new Insets(16));
		dis.setMaxWidth(180);
		dis.setWrapText(true);
		VBox.setVgrow(dis, Priority.ALWAYS);
		
		description.setPadding(new Insets(30));
		authors.setPadding(new Insets(30,0,10,0));
		name.setPadding(new Insets(12));
		
		name.setFont(Font.font(null,FontWeight.EXTRA_BOLD,25));
		description.setFont(Font.font(null,FontWeight.EXTRA_BOLD,13));
		authors.setFont(Font.font(null,FontWeight.EXTRA_BOLD,13));
		
		name.setTextAlignment(TextAlignment.CENTER);
		description.setTextAlignment(TextAlignment.CENTER);
		dis.setTextAlignment(TextAlignment.CENTER);
		authors.setTextAlignment(TextAlignment.CENTER);
		files.setTextAlignment(TextAlignment.CENTER);
		
		this.setAlignment(Pos.TOP_CENTER);
	}
	
	private static Image getImage(int size) {
		FileInputStream input = null;
		Image image = null;
		try {
			input = new FileInputStream(FULL_PATH_ICON);
			image = new Image(input, size, size, false, false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return image;
	}
}