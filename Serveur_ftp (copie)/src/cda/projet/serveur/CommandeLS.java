package cda.projet.serveur;

import java.io.PrintStream;

public class CommandeLS extends Commande {
	
	public CommandeLS(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
            for(String pathname : CommandExecutor.userPos.list()) {
                ps.println("1 " + pathname);
            }
		ps.println("0");
	}

}