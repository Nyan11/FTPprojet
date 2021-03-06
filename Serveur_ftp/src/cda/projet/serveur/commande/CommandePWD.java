package cda.projet.serveur.commande;

import java.io.IOException;
import java.io.PrintStream;

import cda.projet.serveur.WorkerClient;

public class CommandePWD extends Commande {
	
	public CommandePWD(PrintStream ps, String commandeStr, WorkerClient client) {
		super(ps, commandeStr, client);
	}

	public void execute() {
		String s;
		try {
			s = client.getPos().getCanonicalPath().toString();
			s = s.replaceAll(WorkerClient.USERS_DIR.getAbsolutePath(), "");
			ps.println("0 " + s + "/");
		} catch (IOException e) {
			ps.println("2 " + "error occured, please log back");
			e.printStackTrace();
		}
	}

}
