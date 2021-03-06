package cda.ftp.ihm.components.specials;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class IconsUtils {
	
	public static final Map<String, MimeType> ALL_EXTENSION = GET_EXTENSIONS();
	
	public static final Map<String, MimeType> GET_EXTENSIONS() {
		Map<String, MimeType> map = new HashMap<String, MimeType>();
		
		String[] image_ext   = {"png", "jpg", "jpeg", "raw", "r2w"};
		String[] package_ext = {"zip", "rar", "tar", "deb", "jar"};
		String[] pdf_ext     = {"pdf"};
		String[] text_ext    = {"txt", "odt", "opt"};
		String[] video_ext   = {"avi", "mp4", "mpk"};
		
		for(String ext : Arrays.asList(image_ext)) 	 map.put(ext, MimeType.IMAGE);
		for(String ext : Arrays.asList(package_ext)) map.put(ext, MimeType.PACKAGE);
		for(String ext : Arrays.asList(pdf_ext))	 map.put(ext, MimeType.PDF);
		for(String ext : Arrays.asList(text_ext))	 map.put(ext, MimeType.TEXT);
		for(String ext : Arrays.asList(video_ext))	 map.put(ext, MimeType.VIDEO);
		
		return map;
	}
	
	public static MimeType getMimeType(String filename, boolean isDir) {
		if(isDir) 
			return MimeType.FOLDER;
		else if(!filename.contains("."))
			return MimeType.NO_EXTENSION;
		else
			for(String ext : IconsUtils.ALL_EXTENSION.keySet())
				if(filename.endsWith(ext))
					return IconsUtils.ALL_EXTENSION.get(ext);
		return MimeType.NO_EXTENSION;
	}
	
	public static Image getImage(String filename, boolean isDir, int size) {
		FileInputStream input = null;
		Image image = null;
		try {
			input = new FileInputStream(getMimeType(filename, isDir).getPath());
			image = new Image(input, size, size, false, false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return image;
	}
}
