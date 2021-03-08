package cda.ftp.client.logic;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javafx.concurrent.Task;

public class Downloader extends Task {

	protected File file;
	protected String filePath;
	protected String host;
	protected int port;
	protected long size;

	public Downloader(File local, String filePath, String host, int port, long size) {
		this.file = local;
		this.filePath = filePath;
		this.host = host;
		this.port = port;
		this.size = size;
	}

	/*
	@Override
	public void run() {
		Socket socketFile;
		InputStream is;
		ByteArrayOutputStream baos;
		BufferedOutputStream bos;

		byte[] barray;
		int bytesRead;

		try {
			barray = new byte[1];
			socketFile = new Socket(this.host, port);

			is = socketFile.getInputStream();

			baos = new ByteArrayOutputStream();
			
			bos = new BufferedOutputStream(new FileOutputStream(file));
			
			bytesRead = is.read(barray, 0, barray.length);

			do {
				baos.write(barray);
				bytesRead = is.read(barray);
			} while (bytesRead != -1);

			bos.write(baos.toByteArray());
			bos.flush();
			bos.close();
			baos.close();
			socketFile.close();
			System.out.println("Telechargement de " + this.filePath + " a termine");
		} catch (IOException e) {
			System.out.println("Une erreur a arrete le telechargement");
			e.printStackTrace();
		}
	}
	*/
	

	@Override
	protected Object call() throws Exception {
		Socket socketFile;
		InputStream is;
		ByteArrayOutputStream baos;
		BufferedOutputStream bos;

		byte[] barray;
		int bytesRead;
		long iterations = 0;

		try {
			barray = new byte[1];
			socketFile = new Socket(this.host, port);

			is = socketFile.getInputStream();

			baos = new ByteArrayOutputStream();
			
			bos = new BufferedOutputStream(new FileOutputStream(file));
			
			bytesRead = is.read(barray, 0, barray.length);

			do {
				baos.write(barray);
				bytesRead = is.read(barray);
				updateProgress(iterations, size);
				iterations++;
			} while (bytesRead != -1);

			bos.write(baos.toByteArray());
			bos.flush();
			bos.close();
			baos.close();
			socketFile.close();
			System.out.println("Telechargement de " + this.filePath + " a termine");
		} catch (IOException e) {
			System.out.println("Une erreur a arrete le telechargement");
			e.printStackTrace();
		}
		return null;
	}
}
