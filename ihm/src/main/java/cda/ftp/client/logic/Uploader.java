package cda.ftp.client.logic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import cda.ftp.ihm.FTPinterface;
import javafx.concurrent.Task;

public class Uploader extends Task implements Runnable {

	protected File file;
	protected String host;
	protected int port;

	public Uploader(File file, String host, int port) {
		this.file = file;
		this.host = host;
		this.port = port;
	}

	/*
	@Override
	public void run() {
		Socket socketFile;
		BufferedOutputStream bos;
		BufferedInputStream bis;
		byte[] barray;

		try {
			barray = new byte[(int) file.length()];

			socketFile = new Socket(this.host, port);

			bos = new BufferedOutputStream(socketFile.getOutputStream());
			bis = new BufferedInputStream(new FileInputStream(file));

			bis.read(barray, 0, barray.length);

			bos.write(barray);
			
			bos.flush();
			bos.close();
			socketFile.close();
			System.out.println("Upload de " + this.file.getName() + " a termine");
		} catch (IOException e) {
			System.out.println("Une erreur a arrete l'upload");
			e.printStackTrace();
		}
	}
	*/

	@Override
	protected Object call() throws Exception {
		Socket socketFile;
		BufferedOutputStream bos;
		BufferedInputStream bis;
		byte[] barray;

		try {
			barray = new byte[(int) file.length()];

			socketFile = new Socket(this.host, port);

			bos = new BufferedOutputStream(socketFile.getOutputStream());
			bis = new BufferedInputStream(new FileInputStream(file));

			bis.read(barray, 0, barray.length);

			bos.write(barray);
			
			bos.flush();
			bos.close();
			updateProgress(1, 1);
			socketFile.close();
			System.out.println("Upload de " + this.file.getName() + " a termine");
			FTPinterface.updateAppAfterUpload();
		} catch (IOException e) {
			System.out.println("Une erreur a arrete l'upload");
			e.printStackTrace();
		}
		return null;
	}
}
