package cda.ftp.ihm.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

import cda.ftp.client.logic.FTPinterface;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FTPloginView extends VBox {
	
	private final static String FULL_PATH_ICON = "resources/icons/ftp.png";

	private TextField hostField;
	private TextField portField;
	private TextField userField;
	private PasswordField passField;
	private Button loginButton;
	private Label incorectConnectLabel;
	private Label incorectPassLabel;
	private Stage stage;


	public FTPloginView(Stage stage) {
		super();
		this.stage = stage;
		generateView();
		loginAction();
	}

	private void loginAction() {
		loginButton.setOnMouseClicked(e -> {
			if(checkLoginEmptyField()) {
				System.out.println("Error: empty field for login");
			}
			else {
				try {
					FTPinterface.connect(hostField.getText(), portField.getText());
				} catch (UnknownHostException e1) {
					incorectConnectLabel.setText("Error unknown host");
					return;
				} catch (IOException e1) {
					incorectConnectLabel.setText("Error connection refused");
					return;
				}
				incorectConnectLabel.setText("");
				if(!FTPinterface.login(userField.getText(), passField.getText())) {
					incorectPassLabel.setText("Unknow user / password");
				}
				else {
					FTPinterface.goToApp(stage);
				}
				
			}
		});
	}

	private boolean checkLoginEmptyField() {
		boolean ret = false;
		if(hostField.getText().equals("")) {
			incorectConnectLabel.setText("Host empty");
			ret = true;
		} else if(portField.getText().equals("")) {
			incorectConnectLabel.setText("Port empty");
			ret = true;
		}
		else {
			incorectConnectLabel.setText("");
		}

		if(userField.getText().equals("")) {
			incorectPassLabel.setText("User empty");
			ret = true;
		} else if(passField.getText().equals("")) {
			incorectPassLabel.setText("Pass empty");
			ret = true;
		}
		else {
			incorectPassLabel.setText("");
		}
		return ret;
	}

	private void generateView() {
		HBox titleBox = new HBox();

		VBox credentialBox = new VBox();

		HBox connectBox = new HBox();
		VBox hostBox = new VBox();
		VBox portBox = new VBox();

		VBox userBox = new VBox();
		VBox passBox = new VBox();
		VBox buttonBox = new VBox();

		Label titleLabel = new Label("ProjetFTP");
		Label hostLabel = new Label("Host");
		Label portLabel = new Label("Port");
		Label userLabel = new Label("Login");
		Label passLabel = new Label("Password");
		incorectConnectLabel = new Label("");
		incorectPassLabel = new Label("");

		/*
		hostField = new TextField("127.0.0.1");
		portField = new TextField("2121");
		userField = new TextField("yann");
		*/
		hostField = new TextField();
		portField = new TextField();
		userField = new TextField();
		passField = new PasswordField();

		loginButton = new Button("Log In");
		
		Image image = getImage(64);
		ImageView imageView = new ImageView(image);

		//portField.setTextFormatter(TextFormatter.);

		this.getChildren().addAll(titleBox, credentialBox);
		credentialBox.getChildren().addAll(connectBox, userBox, passBox, buttonBox);
		connectBox.getChildren().addAll(hostBox, portBox);
		titleBox.getChildren().addAll(imageView, titleLabel);
		hostBox.getChildren().addAll(hostLabel, hostField, incorectConnectLabel);
		portBox.getChildren().addAll(portLabel, portField);
		userBox.getChildren().addAll(userLabel, userField);
		passBox.getChildren().addAll(passLabel, passField, incorectPassLabel);
		buttonBox.getChildren().addAll(loginButton);

		this.setPadding(new Insets(16));
		this.setSpacing(16);

		titleLabel.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 64));
		hostField.setPromptText("Ex: 127.0.0.1");
		portField.setPromptText("Ex: 2121");
		incorectPassLabel.setTextFill(Color.rgb(210, 39, 30));
		incorectConnectLabel.setTextFill(Color.rgb(210, 39, 30));


		credentialBox.setPadding(new Insets(16));
		credentialBox.setSpacing(16);
		connectBox.setSpacing(16);
		titleBox.setSpacing(32);

		HBox.setHgrow(hostBox, Priority.ALWAYS);

		hostField.setMinWidth(170);
		portField.setMinWidth(80);
		portField.setPrefWidth(80);

		titleBox.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		VBox.setVgrow(buttonBox, Priority.ALWAYS);
		VBox.setVgrow(credentialBox, Priority.ALWAYS);
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
