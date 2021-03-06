package cda.ftp.ihm;

import cda.ftp.ihm.components.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
	
	public static final int HEIGHT_WIDTH = 640;
	public static final int WINDOWS_WIDTH = 480;
	
	@SuppressWarnings("exports")
	@Override
    public void start(Stage stage) {
        FTPloginView login = new FTPloginView(stage);
        
        Scene mainScene = new Scene(login, HEIGHT_WIDTH, WINDOWS_WIDTH);
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}