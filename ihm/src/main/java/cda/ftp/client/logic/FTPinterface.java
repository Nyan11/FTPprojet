package cda.ftp.client.logic;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cda.ftp.ihm.components.FTPactionView;
import cda.ftp.ihm.components.FTPappView;
import cda.ftp.ihm.components.FTPdirView;
import cda.ftp.ihm.components.FTPiconView;
import cda.ftp.ihm.components.FTPselectView;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

public class FTPinterface {

	@SuppressWarnings("exports")
	public static CommFTP communicator = null;
	public static Set<Pair<String, Pair<Boolean, Long>>> lsValue = null;
	public static String pwdValue = null;
	public static String currentFile;
	public static long currentSize;
	public static String host;
	
	private static FTPselectView selectView;
	private static FTPactionView actionView;
	private static FTPdirView dirView;

	public static boolean connect(String stringHost, String stringPort) throws UnknownHostException, IOException {
		int port;
		try {
			communicator.closeAll();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (NullPointerException e) {
			// do nothing
		}
		port = Integer.parseInt(stringPort);
		if(port < 0) {
			return false;
		}
		communicator = new CommFTP(stringHost, port);
		FTPinterface.host = stringHost;
		return true;
	}

	public static boolean login(String user, String pass) {
		try {
			if(!FTPinterface.communicator.sendMessage("user " + user)) {
				return false;
			} else if(!FTPinterface.communicator.sendMessage("pass " + pass)) {
				return false;
			} else {
				return true;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("exports")
	public static void goToApp(Stage stage) {
		Scene mainScene;
		FTPappView app;
		double height;
		double width;

		height = stage.getScene().getHeight();
		width = stage.getScene().getWidth();
		app = new FTPappView();
		mainScene = new Scene(app, width, height);
		
		FTPinterface.selectView = app.select;
		FTPinterface.dirView = app.dir;
		FTPinterface.actionView = app.action;

		stage.setScene(mainScene);
		stage.show();
	}

	@SuppressWarnings("exports")
	public static List<FTPiconView> ls(){
		Set<FTPiconView> result = new HashSet<FTPiconView>();
		List<FTPiconView> sortedResult = null;
		try {
			FTPinterface.communicator.sendMessage("ls");
			lsValue.forEach(pair -> {
				result.add(new FTPiconView(pair.getKey(), pair.getValue().getKey(), pair.getValue().getValue()));
			});
			sortedResult = result.stream().sorted((f1, f2) -> {
				if(f1.isDir() && ! f2.isDir()) {
					return -1;
				} else if(!f1.isDir() && f2.isDir()) {
					return 1;
				} else {
					return f1.getName().compareTo(f2.getName());
				}
			}).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sortedResult;
	}

	public static String pwd() {
		try {
			FTPinterface.communicator.sendMessage("pwd");
		} catch (IOException e) {
			pwdValue = "Error: Cannot find the path";
			e.printStackTrace();
		}
		return pwdValue;
	}

	public static void cd(String path) {
		try {
			FTPinterface.communicator.sendMessage("cd " + path);
			updateApp();
		} catch (IOException e) {
			pwdValue = "Error: Cannot find the path";
			e.printStackTrace();
		}
	}
	
	public static void updateApp() {
		selectView.updateView();
		dirView.updateView();
		actionView.updateView();
		FTPinterface.currentFile = "";
	}
	
	public static void updateAppAfterUpload() {
		System.out.println("AAAAAAAAAAAAAAAAAA");
		selectView.updateView();
		System.out.println("BBBBBBBBBBBBBBBBBB");
	}

	public static void download() {
		if(FTPinterface.currentFile.equals("")) {
			System.out.println("No file selected");
			return;
		}
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showSaveDialog(new Stage());
		if(selectedFile != null) {
			try {
				FTPinterface.communicator.downloadBis("get " + FTPinterface.currentFile, selectedFile, FTPinterface.host, FTPinterface.currentSize);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void upload() {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(new Stage());
		if(selectedFile != null) {
			try {
				FTPinterface.communicator.uploadBis("stor " + selectedFile.getName(), selectedFile, FTPinterface.host);
				//selectView.updateView();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void createStatusInfo(boolean download, String name, ReadOnlyDoubleProperty progress) {
		FTPinterface.actionView.addStatusInfos(download, name, progress);
	}
}
