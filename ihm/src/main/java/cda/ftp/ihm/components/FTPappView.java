package cda.ftp.ihm.components;

import javafx.scene.layout.VBox;

public class FTPappView extends VBox {
	
	public FTPdirView dir;
	public FTPmenuView menu;
	public FTPactionView action;
	public FTPselectView select;
	
	public FTPappView() {
		super();
		generateView();
	}

	private void generateView() {
        dir = new FTPdirView();
        menu = new FTPmenuView();
        select = new FTPselectView();
        action = new FTPactionView();
        
        this.getChildren().add(menu);
        this.getChildren().add(dir);
        this.getChildren().add(select);
        this.getChildren().add(action);
	}
}
