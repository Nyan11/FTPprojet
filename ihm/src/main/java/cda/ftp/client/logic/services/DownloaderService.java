package cda.ftp.client.logic.services;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class DownloaderService extends Service {

	protected File file;
	protected String filePath;
	protected String host;
	protected int port;
	protected long size;

	public DownloaderService(File local, String filePath, String host, int port, long size) {
		this.file = local;
		this.filePath = filePath;
		this.host = host;
		this.port = port;
		this.size = size;
	}
	
	@Override
	protected Task<Void> createTask() {
		return new Downloader(file, filePath, host, port, size);
	}
}
