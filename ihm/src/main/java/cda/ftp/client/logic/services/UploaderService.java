package cda.ftp.client.logic.services;

import java.io.File;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class UploaderService extends Service {
	protected File file;
	protected String host;
	protected int port;

	public UploaderService(File file, String host, int port) {
		this.file = file;
		this.host = host;
		this.port = port;
	}

	@Override
	protected Task createTask() {
		return new Uploader(file, host, port);
	}

}
