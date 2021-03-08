package cda.ftp.ihm.components.specials;

public enum MimeType {
	FOLDER("folder.png"),
	IMAGE("image-x-generic.png"),
	PACKAGE("package-x-generic.png"),
	PDF("application-pdf.png"),
	TEXT("text-x-generic.png"),
	VIDEO("video-x-generic.png"),
	NO_EXTENSION("text-x-generic.png");
	
	private final static String FULL_PATH = "resources/icons/";
	private String path;
	
	private MimeType(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return MimeType.FULL_PATH + this.path;
	}
}
