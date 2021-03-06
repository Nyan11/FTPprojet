package cda.projet.serveur.commande;

import java.io.File;
import java.io.PrintStream;

import cda.projet.serveur.WorkerClient;

public class CommandeLS extends Commande {
	
	public CommandeLS(PrintStream ps, String commandeStr, WorkerClient client) {
		super(ps, commandeStr, client);
	}

	public void execute() {
            for(File file : client.getPos().listFiles()) {
                if(file.isDirectory()) {
                	ps.println("1 " + file.getName() + " dir");
                }
                else {
                	ps.println("1 " + file.getName() + " " + file.length());
                }
            }
		ps.println("0");
	}

}
