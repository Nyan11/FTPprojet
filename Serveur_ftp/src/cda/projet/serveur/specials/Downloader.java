package cda.projet.serveur.specials;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Downloader implements Runnable {
	
	protected PrintStream ps;
	protected File file;
	protected static int PORT = 5000;
	
	public Downloader(PrintStream ps, File file) {
		this.ps = ps;
		this.file = file;
	}
	
	@Override
	public void run() {
		ServerSocket serveurDownloadFile;
		Socket socketFile;
		InputStream is;
		ByteArrayOutputStream baos;
		BufferedOutputStream bos;
		
		byte[] aByte = new byte[1];
		int bytesRead;
		int currentPort = this.getPort();
		try {
			serveurDownloadFile = new ServerSocket(currentPort);
			new Thread(new ThreadMessage(ps, currentPort, file.getName())).start();
			socketFile = serveurDownloadFile.accept();
			
			is = socketFile.getInputStream();
			baos = new ByteArrayOutputStream();
			bos = new BufferedOutputStream(new FileOutputStream(file));

			bytesRead = is.read(aByte, 0, aByte.length);

            do {
                    baos.write(aByte);
                    bytesRead = is.read(aByte);
            } while (bytesRead != -1);

            bos.write(baos.toByteArray());
            bos.flush();
            bos.close();
            socketFile.close();
            serveurDownloadFile.close();
		} catch (IOException e) {
			ps.println("2 une erreur a eu lieu");
			e.printStackTrace();
		}
	}
	
	public synchronized int getPort() {
		return Downloader.PORT++;
	}
}
