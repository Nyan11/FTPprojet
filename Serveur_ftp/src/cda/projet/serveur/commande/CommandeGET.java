package cda.projet.serveur.commande;

import java.io.File;
import java.io.PrintStream;

import cda.projet.serveur.WorkerClient;
import cda.projet.serveur.specials.Uploader;

public class CommandeGET extends Commande {
	
	public static int port = 6969;
	
	public CommandeGET(PrintStream ps, String commandeStr, WorkerClient client) {
		super(ps, commandeStr, client);
	}

	public void execute() {
		File file = new File(client.getPos(), commandeArgs[0]);
		if(!file.exists() || !file.isFile()) {
			ps.println("2 le fichier n'existe pas");
			return;
		}
		new Thread(new Uploader(ps, file)).start();
	}
}
