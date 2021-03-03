package cda.ftp.client.logic;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Downloader implements Runnable {

<<<<<<< HEAD
	protected File file;
=======
	protected File local;
>>>>>>> dee033c1cdfbdbd4932a88717ae176f9cea8b62a
	protected String filePath;
	protected String host;
	protected int port;

	public Downloader(File local, String filePath, String host, int port) {
<<<<<<< HEAD
		this.file = local;
=======
		this.local = local;
>>>>>>> dee033c1cdfbdbd4932a88717ae176f9cea8b62a
		this.filePath = filePath;
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
<<<<<<< HEAD
=======
		File file;
>>>>>>> dee033c1cdfbdbd4932a88717ae176f9cea8b62a
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
<<<<<<< HEAD
=======

			file = new File(local, filePath);
>>>>>>> dee033c1cdfbdbd4932a88717ae176f9cea8b62a
			
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
}
