package cda.projet.serveur.commande;

import java.io.PrintStream;
import java.io.File;
import java.util.Arrays;

import cda.projet.serveur.WorkerClient;

public class CommandeUSER extends Commande {


	public CommandeUSER(PrintStream ps, String commandeStr, WorkerClient client) {
		super(ps, commandeStr, client);
	}

	public void execute() {
		if(Arrays.asList(WorkerClient.USERS_DIR.list()).contains(commandeArgs[0].toLowerCase())) {
			File dir = new File(WorkerClient.USERS_DIR, commandeArgs[0].toLowerCase());
			client.logUser();
			client.setDir(dir);
			client.setPos(dir);
			ps.println("0 Commande user OK");
		}
		else {
			ps.println("2 Le user " + commandeArgs[0] + " n'existe pas");
		}
	}
}
