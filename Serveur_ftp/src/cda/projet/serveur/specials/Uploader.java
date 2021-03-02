package cda.projet.serveur.specials;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Uploader implements Runnable {
	
	protected PrintStream ps;
	protected File file;
	protected static int PORT = 4000;
	
	public Uploader(PrintStream ps, File file) {
		this.ps = ps;
		this.file = file;
	}
	
	@Override
	public void run() {
		ServerSocket 			serveurDownloadFile;
		Socket 					socketFile;
		BufferedOutputStream 	bos;
		BufferedInputStream 	bis;
		
		byte[] barray = new byte[(int) file.length()];
		int currentPort = this.getPort();
		
		try {
			serveurDownloadFile = new ServerSocket(currentPort);
			new Thread(new ThreadMessage(ps, currentPort, file.getName())).start();
			socketFile = serveurDownloadFile.accept();
			
			bos = new BufferedOutputStream(socketFile.getOutputStream());
			bis = new BufferedInputStream(new FileInputStream(file));
			
			bis.read(barray, 0, barray.length);
			bos.write(barray);
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
		return Uploader.PORT++;
	}
}
