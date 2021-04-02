package cda.ftp.ihm.components.specials;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class InfosUtils {
	
	private final static String FULL_PATH_DOWNLOAD = "resources/icons/down-arrow.png";
	private final static String FULL_PATH_UPLOAD = "resources/icons/up-arrow.png";
	private final static String FULL_PATH_CLOSE = "resources/icons/close-black.png";
	private final static String FULL_PATH_CLOSE_HOVER = "resources/icons/close-red.png";
	
	public static String getPathImage(boolean download) {
		return (download) ? FULL_PATH_DOWNLOAD : FULL_PATH_UPLOAD;
	}
	
	public static String getPathClose(boolean hover) {
		return (hover) ? FULL_PATH_CLOSE : FULL_PATH_CLOSE_HOVER;
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
	
	public static Image getClose(boolean hover, int size) {
		FileInputStream input = null;
		Image image = null;
		try {
			input = new FileInputStream(getPathClose(hover));
			image = new Image(input, size, size, false, false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return image;
	}

}
