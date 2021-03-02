package cda.projet.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LauncherServerFTP {

	protected int 			serverPort   = -1;
	protected ServerSocket 	serverSocket = null;
	
	public LauncherServerFTP(int port) {
		this.serverPort = port;
	}

	
	public void start() {
		try {
			Socket clientSocket 		= null;
			WorkerClient worker 		= null;
			
			serverSocket = new ServerSocket(serverPort);
			
			while(true) {
				clientSocket = this.serverSocket.accept();
				worker = new WorkerClient(clientSocket);
				new Thread(worker).start();
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
