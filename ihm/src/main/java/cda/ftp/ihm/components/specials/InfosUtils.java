package cda.ftp.ihm.components.specials;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class InfosUtils {
	
	private final static String FULL_PATH_DOWNLOAD = "resources/icons/down-arrow.png";
	private final static String FULL_PATH_UPLOAD = "resources/icons/up-arrow.png";
	
	public static String getPathImage(boolean download) {
		return (download) ? FULL_PATH_DOWNLOAD : FULL_PATH_UPLOAD;
	}
	
	public static Image getImage(boolean download, int size) {
		FileInputStream input = null;
		Image image = null;
		try {
			input = new FileInputStream(getPathImage(download));
			image = new Image(input, size, size, false, false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return image;
	}

}
