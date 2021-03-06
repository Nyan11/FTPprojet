package cda.projet.serveur.commande;

import java.io.PrintStream;

import cda.projet.serveur.WorkerClient;

public abstract class Commande {

	protected WorkerClient client;
	protected PrintStream ps;
	protected String commandeNom = "";
	protected String [] commandeArgs ;

	public Commande(PrintStream ps, String commandeStr, WorkerClient client) {
		this.client = client;
		this.ps = ps ;
		String [] args = commandeStr.split(" ");
		commandeNom = args[0];
		commandeArgs = new String[args.length-1];

		for(int i=0; i<commandeArgs.length; i++) {
			commandeArgs[i] = args[i+1];
		}
	}

	public abstract void execute();

}
