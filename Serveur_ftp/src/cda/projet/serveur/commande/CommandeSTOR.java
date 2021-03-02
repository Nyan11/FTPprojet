package cda.projet.serveur.commande;

import java.io.File;
import java.io.PrintStream;

import cda.projet.serveur.WorkerClient;
import cda.projet.serveur.specials.Downloader;

public class CommandeSTOR extends Commande {
	
	public static int port = 4000;
	
	public CommandeSTOR(PrintStream ps, String commandeStr, WorkerClient client) {
		super(ps, commandeStr, client);
	}

	public void execute() {
		File file = new File(client.getPos(), commandeArgs[0]);
		if(file.exists() && file.isFile()) {
			ps.println("2 le fichier existe");
			return;
		}
		new Thread(new Downloader(ps, file)).start();
	}
}
