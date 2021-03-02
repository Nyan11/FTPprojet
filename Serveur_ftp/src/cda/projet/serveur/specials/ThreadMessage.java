package cda.projet.serveur.specials;

import java.io.PrintStream;

public class ThreadMessage implements Runnable {

	private PrintStream ps;
	private int port;
	private String fileName;
	
	public ThreadMessage(PrintStream ps, int port, String fileName) {
		this.ps = ps;
		this.port = port;
		this.fileName = fileName;
	}
	@Override
	public void run() {
		this.ps.println("1 Le fichier " + this.fileName + " est prêt pour la reception");
		this.ps.println("0 Sur le port " + this.port);
	}

}
