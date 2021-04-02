package cda.ftp.ihm.components;

import cda.ftp.client.logic.FTPinterface;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FTPmenuView extends MenuBar {
	
	public FTPmenuView() {
		super();
		generateView();
	}

	private void generateView() {
		Menu fileMenu = generateFileMenu();
		//Menu editMenu = generateEditMenu();
		//Menu searchMenu = generateSearchMenu();
		Menu helpMenu = generateHelpMenu();

		this.getMenus().add(fileMenu);
		//this.getMenus().add(editMenu);
		//this.getMenus().add(searchMenu);
		this.getMenus().add(helpMenu);
	}
	
	private Menu generateHelpMenu() {
		Menu main = new Menu("Help");
		MenuItem AboutItem = new MenuItem("About");
		
		AboutItem.setOnAction(e -> {
			Scene scene = new Scene(new FTPaboutView());
			Stage secondaryStage = new Stage();
			secondaryStage.setScene(scene);
			secondaryStage.show();
		});
		
		main.getItems().add(AboutItem);
		
		return main;
	}

	/*
	private Menu generateSearchMenu() {
		Menu main = new Menu("Search");
		MenuItem searchItem = new MenuItem("Search");
		SeparatorMenuItem separator = new SeparatorMenuItem();
		MenuItem queueItem = new MenuItem("Queue");
		MenuItem boardItem = new MenuItem("Board");
		MenuItem archiveItem = new MenuItem("Archive");
		
		main.getItems().add(searchItem);
		main.getItems().add(separator);
		main.getItems().add(queueItem);
		main.getItems().add(boardItem);
		main.getItems().add(archiveItem);
		
		return main;
	}

	private Menu generateEditMenu() {
		Menu main = new Menu("Edit");
		MenuItem cutItem = new MenuItem("Cut");
		MenuItem copyItem = new MenuItem("Copy");
		MenuItem pasteItem = new MenuItem("Paste");
		SeparatorMenuItem separator = new SeparatorMenuItem();
		MenuItem deleteItem = new MenuItem("Delete");
		
		main.getItems().add(cutItem);
		main.getItems().add(copyItem);
		main.getItems().add(pasteItem);
		main.getItems().add(separator);
		main.getItems().add(deleteItem);
		
		return main;
	}
	*/

	private Menu generateFileMenu() {
		Menu main = new Menu("File");
		
		MenuItem logoffItem = new MenuItem("Log Off");
		main.getItems().add(logoffItem);
		
		logoffItem.setOnAction(e -> {
			FTPinterface.disconnect();
		});
		
		/*
		MenuItem newItem = new MenuItem("New");
		MenuItem openItem = new MenuItem("Open File...");
		Menu rescentFileSub = new Menu("Rescent Files");
		MenuItem rescent1 = new MenuItem("1 xxxxxxxx");
		MenuItem rescent2 = new MenuItem("2 xxxxxxxx");
		MenuItem rescent3 = new MenuItem("3 xxxxxxxx");
		rescentFileSub.getItems().addAll(rescent1, rescent2, rescent3);
		SeparatorMenuItem sepRescentFile = new SeparatorMenuItem();
		MenuItem clearHistory = new MenuItem("Clear History");
		rescentFileSub.getItems().addAll(sepRescentFile, clearHistory);
		
		SeparatorMenuItem separator0 = new SeparatorMenuItem();
		
		MenuItem newTicketItem = new MenuItem("New Ticket");
		
		SeparatorMenuItem separator1 = new SeparatorMenuItem();

		MenuItem saveItem = new MenuItem("Save");
		MenuItem saveAsItem = new MenuItem("Save As");
		
		SeparatorMenuItem separator2 = new SeparatorMenuItem();
		
		MenuItem renameItem = new MenuItem("Rename");
		MenuItem refreshItem = new MenuItem("Refresh");
		
		SeparatorMenuItem separator3 = new SeparatorMenuItem();
		
		MenuItem propertiesItem = new MenuItem("Properties");
		MenuItem exitItem = new MenuItem("Exit");
		
		main.getItems().add(newItem);
		main.getItems().add(openItem);
		main.getItems().add(rescentFileSub);
		main.getItems().add(separator0);
		main.getItems().add(newTicketItem);
		main.getItems().add(separator1);
		main.getItems().add(saveItem);
		main.getItems().add(saveAsItem);
		main.getItems().add(separator2);
		main.getItems().add(renameItem);
		main.getItems().add(refreshItem);
		main.getItems().add(separator3);
		main.getItems().add(propertiesItem);
		main.getItems().add(exitItem);
		*/
		
		return main;
	}
}
