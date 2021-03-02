package cda.projet.serveur.commande;

import java.io.PrintStream;

import cda.projet.serveur.WorkerClient;

public class CommandExecutor {

	public static void executeCommande(PrintStream ps, String commande, WorkerClient client) {
		if(commande.startsWith("user "))
			(new CommandeUSER(ps, commande, client)).execute();

		else if(commande.startsWith("pass "))
			(new CommandePASS(ps, commande, client)).execute();
		
		else if(commande.startsWith("get ") && client.isConnected()) 	
			(new CommandeGET(ps, commande, client)).execute();
		
		else if(commande.startsWith("stor ") && client.isConnected()) 	
			(new CommandeSTOR(ps, commande, client)).execute();
		
		else if(commande.startsWith("cd ") && client.isConnected()) 	
			(new CommandeCD(ps, commande, client)).execute();
		
		else if(commande.equals("ls") && client.isConnected()) 			
			(new CommandeLS(ps, commande, client)).execute();
		
		else if(commande.equals("pwd") && client.isConnected()) 		
			(new CommandePWD(ps, commande, client)).execute();
		
		else 															
			ps.println("2 Vous n'êtes pas connecté !");
	}
}
